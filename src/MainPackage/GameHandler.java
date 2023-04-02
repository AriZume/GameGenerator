package MainPackage;
import java.util.*;

public class GameHandler {


    // CONSTRUCTOR
    Game game;
    InGameScreen inGame;
    public GameHandler(int diceAmount, Board tileList)
    {
        game = new Game(new ArrayList<>(),tileList.getTileList(),diceAmount);
        inGame = new InGameScreen();
    }

    // METHODS
    public void startGame(String powerOption , MainMenuScreen myMenu)
    {
        if(powerOption.matches("[Yy]"))
        {
        game.setRandomEnhancedTilerPower(myMenu.getEnhancedTileAmount(), myMenu.getPositiveNumber(), myMenu.getNegativeNumber());
        }

        game.setPlayerPriority();

        inGame.PlayerStartScreen();

        int playerRoll;

        while (!game.isGameOver())
        {
            // PLAYER TURNS
            for (Player player : game.getTotalPlayers())
            {
                int option = inGame.getOption(player.getName(), player.getCurrentPosition(), game.getBoardSize(), game.indexOf(player));

                //IN-GAME MENU
                switch (option)
                {
                    case 1:
                         playerRoll = game.movePlayer(player);
                        inGame.showRollScreen(playerRoll);
                        String message = game.checkPosition(player);
                        System.out.println(message);
                        inGame.showPlayerPositionEndTurn(game.getTotalPlayers());
                        break;

                    case 3:
                        inGame.ExitGameScreen();
                        game.setGameOver(true);
                        break;

                    default:
                        inGame.optionNotAvailable();
                        break;
                }
                if (game.isGameOver())
                {
                    break;
                }
                 if (game.isWinner(player))
                {
                    game.setWinner(player);
                    inGame.winnerScreen(game.indexOf(player), game.getWinner().getName());
                    game.setGameOver(true);
                    break;
                }

            }
        }
    }

    // --------------
    public void addPlayer(Player newPlayer)
    {
        game.getTotalPlayers().add(newPlayer);
    }

}