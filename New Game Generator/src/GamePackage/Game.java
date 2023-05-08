package GamePackage;

import java.util.*;
import UIPackage.*;
import IOPackage.GameSaver;
import java.util.ArrayList;

public class Game
{
    enum Options
    {
        OPT_ROLL(1),
        OPT_SAVE(2),
        OPT_EXIT(3);
        public final int option;

        Options(int option)
        {
            this.option = option;
        }
    }
    enum StringEnum
    {
        SQUARE_BOARD("Square"),
        CIRCULAR_BOARD("Circular");
        public final String option;
        StringEnum(String option)
        {
            this.option = option;
        }
        public String getOption()
        {
            return option;
        }

    }
    private final int diceAmount;
    private boolean isLoaded;
    private final ArrayList<Player> players;
    private final Board board;
    private final Screen screen = new Screen();
    private final UIScreen uiScreen = new UIScreen();

    private  int loadPlayerIndex = 0;

    // Simple constructor.
    public Game(int playerAm, int tileAm, int diceAm)
    {
        this(playerAm,tileAm,diceAm,0,"0", false);

    }

    // Constructor for game with enhanced tiles only.
    public Game(int playerAm, int tileAm, int diceAm, int enTiles)
    {
        this(playerAm,tileAm,diceAm,enTiles, "0", false);
    }

    // Constructor for game with cards only.
    public Game(int playerAm, int tileAm, int diceAm, String maxPoints)
    {
        this(playerAm,tileAm,diceAm,0,maxPoints,false);
    }

    // Constructor with both cards and enhanced tiles.
    public Game(int playerAm, int tileAm, int diceAm, int enTiles, String maxPoints)
    {
        this(playerAm,tileAm,diceAm,enTiles,maxPoints,false);
    }

    // Final constructor.
    public Game(int playerAm, int tileAm, int diceAm, int enTiles, String maxPoints, boolean isLoaded)
    {
        this.diceAmount = diceAm;
        this.board = new Board(tileAm, enTiles , maxPoints);
        this.players = new ArrayList<>();
        this.isLoaded = isLoaded;
        if(!isLoaded)
        {
            createPlayers(playerAm);
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

    public void createPlayers(ArrayList<String> loadPlayers)
    {
        for (String loadPlayer : loadPlayers)
        {
            players.add(new Player(loadPlayer));
        }
    }
    public void createPlayers(int playerAmount)
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
        }
    }

    public int getPlayerIndex(Player player)
    {
        return players.indexOf(player);
    }

    public void setLoadPlayerIndex(int loadPlayerIndex)
    {
        this.loadPlayerIndex = loadPlayerIndex;
    }

    public void decidePlayerTurn()
    {
        System.out.println("Let's see who's starting first!");
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
        int userInput, previousPlayer = 0, playerIndex;

        if(isLoaded)
        {
            playerIndex = loadPlayerIndex;
        }
        else
        {
            playerIndex  = 0;
            decidePlayerTurn();
        }

        while (!endGame)
        {
            for(int i = playerIndex; i < players.size(); i++)
            {
                if (players.get(previousPlayer).getHasPlayAgainCard())
                {
                    players.get(previousPlayer).setHasPlayAgainCard(false);
                    i = previousPlayer;
                }

                if(board.getBoardType().equals(StringEnum.SQUARE_BOARD.getOption()))
                {
                    playerTurn = screen.printPlayerTurn(players.get(i).getName(), getPlayerIndex(players.get(i)));
                }
                else
                {
                    if(board.getLapsToWin() != 0)
                    {
                         playerTurn = screen.printPlayerTurnLap(players.get(i).getName(), getPlayerIndex(players.get(i)), players.get(i).getLap());
                    }
                    else
                    {
                        playerTurn = screen.printPlayerTurnPoints(players.get(i).getName(), players.get(i).getPoints(), getPlayerIndex(players.get(i)));
                    }
                }
                descriptiveMap = screen.printDescriptiveMap(players.get(i).getCurrentPosition(), board.getTiles().size());
                inGameMenu = screen.printInGameMenu();
                System.out.print(playerTurn.getMessage() + descriptiveMap.getMessage() + inGameMenu.getMessage());


                do {
                    userInput = uiScreen.checkUserInput(1, 3);
                    Options userOption = Options.values()[userInput - 1];

                    switch (userOption)
                    {
                        case OPT_ROLL:
                            movePlayer = board.movePlayer(players.get(i), getDiceRoll());
                            System.out.print(movePlayer.getMessage());

                            Response endTurn = screen.printEndTurn(players);
                            System.out.print(endTurn.getMessage());
                            break;
                        case OPT_SAVE:
                            userInput = 0;
                            saveResponse = saveGame.saveProgress(players, board.getBoardType(), board.getTiles().size(), board.getMaxPoints(), board.getLapsToWin(), diceAmount, board.getEnhancedTiles(),getPlayerIndex(players.get(i)));
                            System.out.print(saveResponse.getMessage());
                            break;
                        case OPT_EXIT:
                            endGame = true;
                            break;
                        default:
                            System.out.print(screen.printInvalidOption().getMessage());
                            break;
                    }

                    if (userInput == 0) {
                        System.out.print(inGameMenu.getMessage());
                    }
                }while(userInput <=0 || userInput >3);

                if (endGame)
                {
                    break;
                }
                else if(board.isWinner(players.get(i), board))
                {
                    endGame = true;
                    System.out.print(screen.printWinner(getPlayerIndex(players.get(i)),players.get(i).getName()));
                    break;
                }
                previousPlayer = i;
            }

            if(isLoaded)
            {
                playerIndex = 0;
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
