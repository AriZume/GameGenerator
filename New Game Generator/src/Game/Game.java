package Game;

import java.util.*;

import Main.EnumClass;
import Tiles.Tile;
import UserInterface.*;
import IO.GameSaver;

public class Game
{
    private final int diceAmount;
    private boolean isLoaded;
    private final ArrayList<Integer> diceRolls;
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
        this.diceRolls = new ArrayList<>();
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
        int  totalRoll=0,diceRoll;
        Random roll = new Random();
        for(int i = 0; i < diceAmount; i++)
        {
            diceRoll = roll.nextInt(6)+1;
            diceRolls.add(diceRoll);
            totalRoll += diceRolls.get(i);
        }
        return totalRoll;
    }

    public void decidePlayerPriority()
    {
        for (Player player : players.getPlayers())
        {
            player.setQueuePosition(getDiceRoll());
            diceRolls.clear();
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

        //showTileObj(); //Debug

        if(!isLoaded)
        {
            userInputScreen.declarePlayerNames(players, players.getPlayers().size());
            decidePlayerPriority();
            currentPlayer = players.getCurrentPlayer();
            System.out.print( uiResponse.createPlayerPriorityResponse(players,diceAmount).message());
            isLoaded = false;
        }
        while (true)
        {
            playerTurn = uiResponse.createPlayerTurnResponse(board.getBoardType(), board.getLapsToWin(), currentPlayer, players.getCurrentPlayerIndex());
            descriptiveMap = uiResponse.createDescriptiveMapResponse(currentPlayer.getCurrentPosition(), board.getTiles().size());
            inGameMenu = uiResponse.createInGameResponse();

            System.out.print(playerTurn.message() + descriptiveMap.message() + inGameMenu.message());
            do
            {
                userInput = userInputScreen.checkUserInput(EnumClass.InputRestriction.GAME_MENU.getMin(), EnumClass.InputRestriction.GAME_MENU.getMax());

                EnumClass.InGameMenuOption userOption = EnumClass.InGameMenuOption.values()[userInput - 1];
                switch(userOption)
                {
                    case ROLL:
                        List<Response> responses = board.movePlayer(currentPlayer, getDiceRoll());
                        responses.set(0,uiResponse.createDiceRollsResponse(diceRolls));
                        for (Response response:responses)
                        {
                            System.out.print(response.message());
                        }
                        Response endTurn = uiResponse.createEndTurnResponse(players.getPlayers(),board.getBoardType());
                        System.out.print("\n" + endTurn.message());
                        break;
                    case SAVE:
                        userInput = 0;
                        saveResponse = saveGame.saveProgress(players.getPlayers(), board.getBoardType(), board.getTiles().size(), board.getMaxPoints(), board.getLapsToWin(), diceAmount, board.getEnhancedTiles(), players.getCurrentPlayerIndex());
                        System.out.print(saveResponse.message() + inGameMenu.message());
                        break;
                    case EXIT:
                        endGame = true;
                        break;
                    default:
                        System.out.print(uiResponse.createInvalidOptionResponse().message());
                        break;
                }
            }while(userInput <=0 || userInput >3);

            if (endGame)
            {
                break;
            }
            else if(board.isWinner(currentPlayer, board))
            {
                System.out.print(uiResponse.createWinnerResponse(players.getCurrentPlayerIndex(),currentPlayer.getName()).message());
                break;
            }
            diceRolls.clear();
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
