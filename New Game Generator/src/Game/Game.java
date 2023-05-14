package Game;

import java.util.*;
import java.util.ArrayList;

import Main.EnumClass;
import Tiles.Tile;
import UserInterface.*;
import IO.GameSaver;

public class Game
{
    private final int diceAmount;
    private boolean isLoaded;
    private final Players players;
    private final Board board;
    private final UIResponse uiResponse = new UIResponse();
    private final UserInputScreen userInputScreen = new UserInputScreen();

    // Constructor for new game
    public Game(int playerAm, int tileAm, int diceAm, int enTiles, int maxPoints, String boardType, int lapsToWin)
    {
        this(playerAm, tileAm, diceAm, enTiles, maxPoints, 0, boardType, lapsToWin,
                null, null, null, null);
    }

    // Final constructor.
    public Game(int playerAm, int tileAm, int diceAm, int enTiles, int maxPoints, int loadedPlayerIndex,
                String boardType, int lapsToWin, ArrayList<String> loadedNames,
                ArrayList<String> loadedPositions, ArrayList<String> loadedLaps, ArrayList<String> loadedPoints)
    {
        this.diceAmount = diceAm;
        this.board = new Board(tileAm, enTiles , maxPoints, boardType, lapsToWin);
        this.isLoaded = (loadedNames != null && loadedPositions != null && loadedLaps !=null && loadedPoints != null);

        if(!isLoaded)
        {
            this.players = new Players(playerAm, maxPoints);
        }
        else
        {
            this.players = new Players(loadedNames, loadedPlayerIndex, loadedPositions, loadedLaps, loadedPoints);
        }
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

    public void decidePlayerPriority()
    {
        System.out.println("\nLet's see who's starting first!");
        for (Player player : players.getPlayers())
        {
            System.out.print(player.getName() + ". ");
            int roll = getDiceRoll();
            player.setQueuePosition(roll);
        }
        players.shufflePlayers();
    }

    public void startGame()
    {
        GameSaver saveGame = new GameSaver();
        Player currentPlayer = players.getCurrentPlayer();
        Response descriptiveMap, inGameMenu, playerTurn, saveResponse;
        boolean endGame = false;
        int userInput;

        showTileObj();

        if(!isLoaded)
        {
            decidePlayerPriority();
            isLoaded = false;
        }
        while (true)
        {
            playerTurn = uiResponse.createPlayerTurnResponse(board.getBoardType(), board.getLapsToWin(), currentPlayer, players.getCurrentPlayerIndex());
            descriptiveMap = uiResponse.createDescriptiveMapResponse(currentPlayer.getCurrentPosition(), board.getTiles().size());
            inGameMenu = uiResponse.createInGameResponse();

            System.out.print(playerTurn.getMessage() + descriptiveMap.getMessage() + inGameMenu.getMessage());
            do
            {
                userInput = userInputScreen.checkUserInput(EnumClass.InputRestriction.GAME_MENU.getMin(), EnumClass.InputRestriction.GAME_MENU.getMax());

                EnumClass.InGameMenuOption userOption = EnumClass.InGameMenuOption.values()[userInput - 1];
                switch(userOption)
                {
                    case ROLL:
                        List<Response> responses = board.movePlayer(currentPlayer, getDiceRoll());
                        for (Response response:responses)
                        {
                            System.out.print(response.getMessage());
                        }
                        Response endTurn = uiResponse.createEndTurnResponse(players.getPlayers());
                        System.out.print("\n" + endTurn.getMessage());
                        break;
                    case SAVE:
                        userInput = 0;
                        saveResponse = saveGame.saveProgress(players.getPlayers(), board.getBoardType(), board.getTiles().size(), board.getMaxPoints(), board.getLapsToWin(), diceAmount, board.getEnhancedTiles(), players.getCurrentPlayerIndex());
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
            else if(board.isWinner(currentPlayer, board))
            {
                System.out.print(uiResponse.createWinnerResponse(players.getCurrentPlayerIndex(),currentPlayer.getName()).getMessage());
                break;
            }
            currentPlayer = players.getNextPlayer();
        }
    }

    // Debugging
    public void showTileObj()
    {
        for(Tile tile : board.getTiles())
        {
            System.out.println(tile);
        }
    }
}
