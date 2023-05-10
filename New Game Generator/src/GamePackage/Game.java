package GamePackage;

import java.util.*;
import java.util.ArrayList;

import MainPackage.EnumClass;
import UIPackage.*;
import IOPackage.GameSaver;

public class Game
{
    private final int diceAmount;
    private boolean isLoaded;
    private final ArrayList<Player> players;
    private final Board board;
    private final UIResponse uiResponse = new UIResponse();
    private final UserInputScreen userInputScreen = new UserInputScreen();
    private  int loadPlayerIndex = 0;

    // Constructor for new game
    public Game(int playerAm, int tileAm, int diceAm, int enTiles, int maxPoints)
    {
        this(playerAm,tileAm,diceAm,enTiles,maxPoints,false);
    }

    // Final constructor.
    public Game(int playerAm, int tileAm, int diceAm, int enTiles, int maxPoints, boolean isLoaded)
    {
        this.diceAmount = diceAm;
        this.board = new Board(tileAm, enTiles , maxPoints);
        this.players = new ArrayList<>();
        this.isLoaded = isLoaded;
        if(!isLoaded)
        {
            createPlayers(playerAm, maxPoints);
        }
    }

    public Board getBoard()
    {
        return this.board;
    }

    public ArrayList<Player> getPlayer()
    {
        return this.players;
    }

    public void setLoadPlayerIndex(int loadPlayerIndex)
    {
        this.loadPlayerIndex = loadPlayerIndex;
    }

    public int getPlayerIndex(Player player)
    {
        return players.indexOf(player);
    }

    private int getTurnIndex()
    {
        int turnIndex;
        if(isLoaded)
        {
            turnIndex = loadPlayerIndex;
        }
        else
        {
            turnIndex  = 0;
            decidePlayerTurn();
        }
        return turnIndex;
    }

    public void createPlayers(ArrayList<String> loadPlayers)
    {
        for (String loadPlayer : loadPlayers)
        {
            players.add(new Player(loadPlayer));
        }
    }

    public void createPlayers(int playerAmount, int maxPoints)
    {
        Scanner scanner = new Scanner(System.in);

        for(int i = 0; i < playerAmount; i++)
        {
            System.out.print("Player : ");
            String name;
            do
            {
                name = scanner.nextLine();
                if(name.isBlank())
                {
                    System.out.print("Invalid name. Please try again: ");
                }
            }while(name.isBlank());

            players.add(new Player(name));
            players.get(i).setPoints(maxPoints / 10);
        }
    }

    private int getNextPlayer(int previousPlayer, int i)
    {
        if (players.get(previousPlayer).hasPlayAgainCard())
        {
            players.get(previousPlayer).setHasPlayAgainCard(false);
            i = previousPlayer;
        }
        return i;
    }

    public void decidePlayerTurn()
    {
        System.out.println("\nLet's see who's starting first!");
        for (Player player : players)
        {
            System.out.print(player.getName() + ": ");
            int roll = getDiceRoll();
            player.setQueuePosition(roll);
        }
        players.sort(Comparator.comparing(Player::getQueuePosition).reversed());
    }

    private int getDiceRoll()
    {
        int diceRoll, totalRoll = 0;
        Random roll = new Random();

        System.out.print("You rolled: ");

        for(int i = 0; i < diceAmount; i++)
        {
            diceRoll = roll.nextInt(6)+1;
            System.out.print(diceRoll);

            if(!((i+1) >= diceAmount))
            {
                System.out.print(" and ");
            }
            totalRoll += diceRoll;
        }

        System.out.print("\n");
        return totalRoll;
    }

    public void startGame()
    {
        GameSaver saveGame = new GameSaver();
        Response descriptiveMap, inGameMenu, playerTurn, movePlayer, saveResponse;
        boolean endGame = false;
        int userInput, previousPlayer = 0, turnIndex;

        turnIndex = getTurnIndex();

        while (!endGame)
        {
            for(int i = turnIndex; i < players.size(); i++)
            {
                i = getNextPlayer(previousPlayer, i);

                playerTurn = uiResponse.createPlayerTurnResponse(board.getBoardType(), board.getLapsToWin(), players.get(i), getPlayerIndex(players.get(i)));
                descriptiveMap = uiResponse.createDescriptiveMapResponse(players.get(i).getCurrentPosition(), board.getTiles().size());
                inGameMenu = uiResponse.createInGameResponse();

                System.out.print(playerTurn.getMessage() + descriptiveMap.getMessage() + inGameMenu.getMessage());
                do
                {
                    userInput = userInputScreen.checkUserInput(EnumClass.InputRestriction.GAME_MENU.getMin(), EnumClass.InputRestriction.GAME_MENU.getMax());

                    EnumClass.InGameMenuOption userOption = EnumClass.InGameMenuOption.values()[userInput - 1];
                    switch (userOption)
                    {
                        case ROLL:
                            movePlayer = board.movePlayer(players.get(i), getDiceRoll());
                            System.out.print(movePlayer.getMessage());

                            Response endTurn = uiResponse.createEndTurnResponse(players);
                            System.out.print(endTurn.getMessage());
                            break;
                        case SAVE:
                            userInput = 0;
                            saveResponse = saveGame.saveProgress(players, board.getBoardType(), board.getTiles().size(), board.getMaxPoints(), board.getLapsToWin(), diceAmount, board.getEnhancedTiles(),getPlayerIndex(players.get(i)));
                            System.out.print(saveResponse.getMessage() + inGameMenu.getMessage());
                            break;
                        case EXIT:
                            endGame = true;
                            break;
                        default:
                            System.out.print(uiResponse.createInvalidOptionResponse().getMessage());
                            break;
                    }
                }while(userInput <=0 || userInput >3);

                if (endGame)
                {
                    break;
                }
                else if(board.isWinner(players.get(i), board))
                {
                    endGame = true;
                    System.out.print(uiResponse.createWinnerResponse(getPlayerIndex(players.get(i)),players.get(i).getName()).getMessage());
                    break;
                }
                previousPlayer = i;
            }

            if(isLoaded)
            {
                turnIndex = 0;
                isLoaded = false;
            }
        }
    }


    // Debug only
    /*
    public void printTilesPower()
    {
        for(GamePackage.Tile t : board.getTiles())
        {
            System.out.println(t);
        }
    }
    */
}
