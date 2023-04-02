package MainPackage;

import java.util.*;



public class GameHandler {


    // CONSTRUCTOR
    Game game;
    InGameScreen inGame=new InGameScreen();
    public GameHandler(int diceAmount, Board tileList)
    {
        game = new Game(new ArrayList<>(),tileList.getTileList(),diceAmount);
    }

    // METHODS
    public void startGame(int positiveNumber, int negativeNumber)
    {
        game.setRandomEnhancedTilerPower(positiveNumber, negativeNumber);

        setPlayerPriority();

        System.out.println("\n\nAll players are placed on tile " + 1);

        int playerRoll;
        boolean endGame = false;

        while (!endGame)
        {
            // PLAYER TURNS
            for (Player player : game.getTotalPlayers())
            {
                int option =
                        inGame.getOption(player.getName(), player.getCurrentPosition(), game.getBoardSize(), game.indexOf(player));

                //IN-GAME MENU
                switch (option)
                {
                    case 1:
                         playerRoll = game.movePlayer(player);

                        showRollScreen(playerRoll);

                        String message = game.checkPosition(player);
                        System.out.println(message);

                        inGame.showPlayerPositionEndTurn(game.getTotalPlayers());
                        break;

                    case 3:
                        endGame = exitGame();
                        break;

                    default:
                        System.out.println("Option not available.");
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
        System.out.print("\nTime to see who's playing first.");

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
        System.out.println("Exiting game.");
        return true;
    }

    // --------------
    private void showRollScreen ( int playerRoll)
    {
        System.out.println("You made " + playerRoll + " moves forward.");

    }


}