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

        setPlayerPriority();

        inGame.PlayerStartScreen();

        int playerRoll;
        boolean endGame = false;

        while (!endGame)
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
                        endGame = exitGame();
                        break;

                    default:
                        inGame.optionNotAvailable();
                        break;
                }
                        //EXIT GAME OR WIN CHECK
                        if (endGame)
                        {
                            break;
                        } else if (weHaveAWinner(player))
                        {
                            endGame = inGame.winnerScreen(game.indexOf(player), player.getName());
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

    // WINNING CONDITION
    private boolean weHaveAWinner(Player player)
    {
        return player.getCurrentPosition() >= game.getBoardSize();
    }

    // --------------

    private void setPlayerPriority()
    {
        inGame.whosFirstScreen();

        for (Player player : game.getTotalPlayers()) {
            inGame.priorityActionScreen(player.getName());
            game.setPriorityRoll(player);
        }

        game.sortPlayers();

        inGame.priorityResults(game.getTotalPlayers());
    }


    // --------------
    private boolean exitGame ()
    {
        inGame.ExitGameScreen();
        return true;
    }

}