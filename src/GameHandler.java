package MainPackage;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class GameHandler
{
    private ArrayList<Player> totalPlayers;
    private ArrayList<Tile> totalTiles;
    private final int dice;
    private Map<Integer, Runnable> options;

    // CONSTRUCTOR
    public GameHandler(int diceAmount, Board tileList)
    {
        totalPlayers = new ArrayList<>();
        totalTiles = tileList.getTileList();
        dice = diceAmount;
        options = new HashMap<>();
    }

    // METHODS
    public void addPlayer(Player newPlayer)
    {
        totalPlayers.add(newPlayer);
    }

    // --------------
    private void setPlayerPriority()
    {
        System.out.print("\nTime to see who's playing first.");

        for(Player player : totalPlayers)
        {
            priorityActionScreen(player.getName());

            int roll = getDiceRoll(dice);

            player.setPriorityRoll(roll);
        }

        totalPlayers.sort(Comparator.comparing(Player::getPriorityRoll).reversed());

        priorityResults(totalPlayers);
    }

    // --------------
    public void startGame()
    {
        setPlayerPriority();

        System.out.println("\n\nAll players are placed on tile " + totalTiles.get(0).getTileNumber());

        AtomicBoolean endGame = new AtomicBoolean(false);

        while(!endGame.get())
        {
            // PLAYER TURNS
            for (Player player : totalPlayers)
            {
                int option = getOption(player.getName(),
                        player.getCurrentPosition(), totalTiles.size(),totalPlayers.indexOf(player));

                //IN-GAME MENU
                options.put(1, () ->
                {
                    int playerRoll = getDiceRoll(dice);

                    player.setCurrentPosition(playerRoll);

                    if (player.getCurrentPosition() >=  totalTiles.size())
                    {
                        player.setCurrentPosition(totalTiles.size() - player.getCurrentPosition());
                    }

                    /*
                    System.out.println(player.getCurrentPosition());
                    System.out.println(totalTiles.get(player.getTileIndex()).getTileNumber());
                     */
                    showRollScreen(playerRoll);

                    checkPower(player, totalTiles);

                    if (player.getCurrentPosition() >=  totalTiles.size())
                    {
                        player.setCurrentPosition(totalTiles.size() - player.getCurrentPosition());
                    }
                    else if(player.getCurrentPosition() < 0)
                    {
                        player.resetPosition();
                    }

                    showPlayerPositionEndTurn(totalPlayers);
                });
                options.put(3, () ->
                {
                    endGame.getAndSet(exitGame());
                });

                options.getOrDefault(option, () ->
                {
                    System.out.println("Option not available.");
                }).run();

                //EXIT GAME OR WIN CHECK
                if(endGame.get())
                {
                    break;
                }
                else if(weHaveAWinner(player))
                {
                    endGame.set(winnerScreen(totalPlayers.indexOf(player), player.getName()));
                    break;
                }
            }
        }
    }

    // WINNING CONDITION
    private boolean weHaveAWinner(Player player)
    {
        return player.getCurrentPosition() >= totalTiles.size();
    }

    // --------------
    private int getDiceRoll(int diceAmount)
    {
        int totalRoll = 0;
        int diceRoll;
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

    // --------------
    private void checkPower(Player player, ArrayList<Tile> tiles)
    {
        if(totalTiles.get(player.getTileIndex()).getTilePower() !=0)
        {
            if (tiles.get(player.getCurrentPosition()).getTilePower() > 0 ) {
                System.out.println("You hit a power on this tile and you go forward " + tiles.get(player.getCurrentPosition()).getTilePower());
            }
            else if (tiles.get(player.getCurrentPosition()).getTilePower() < 0)
            {
                System.out.println("You hit a power on this tile and you go backwards " + tiles.get(player.getCurrentPosition()).getTilePower());
            }

            player.setCurrentPosition(totalTiles.get(player.getCurrentPosition()).getTilePower());
        }
    }

    // -------------- GAME-HANDLER SCREEN --------------

    // PLAYER PRIORITY SET
    private void priorityActionScreen(String playerName)
    {
        Scanner input = new Scanner(System.in);
        String userInput;

        System.out.print("\n" + playerName + ", time to roll the dice.\n");
        System.out.print("Type 'R' to roll the dice: ");

        do
        {
            userInput = input.nextLine();

            if (!userInput.equals("R"))
            {
                System.out.print("Please try again: ");
            }
        } while(!userInput.equals("R"));
    }

    //  --------------
    private void priorityResults(ArrayList<Player> players)
    {
        System.out.print("\nPlayer 1 (" + players.get(0).getName() + ") is starting first");
        for(int i = 1; i < players.size(); i++)
        {
            System.out.print("\nPlayer " + (i+1) + " (" + players.get(i).getName() + ")");
        }
    }

    // IN-GAME MENU OPTION
    private int getOption(String playerName, int tileNumber, int totalNumberOfTiles, int playerIndex) {
        Scanner input = new Scanner(System.in);

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("It's " + playerName + "'s turn\n(Player " + (playerIndex + 1) + ")");

        //"DESCRIPTIVE MAP"
        System.out.println("\nYou are placed on tile " + tileNumber + " of " + totalNumberOfTiles);

        //MENU
        System.out.println("\n1. Roll Dice\n2. Save (Unavailable)\n3. Exit");

        int option;
        do
        {
            while (!input.hasNextInt())
            {
                System.out.print("Please select one of the other options: ");
                input.next();
            }
            option = input.nextInt();

            if (option == 2)
            {
                System.out.print("Saving is not available. Try a different option: ");
                option = -1; // Probably wrong way to do it, but works
            }
            else if (option > 3 || option <= 0)
            {
                System.out.print("\nInvalid option.\nPlease select one of the other options: ");
            }
        } while (option > 3 || option <= 0) ;

        return option;
    }

    // --------------
    private boolean winnerScreen(int playerNumber, String playerName)
    {
        System.out.println("-----------------------------------------------------------------------\n" +
                "Player " + playerNumber + ": " + playerName + " won the game!\n\n\n");
        return true;
    }

    // --------------
    private void showPlayerPositionEndTurn(ArrayList<Player> players)
    {
        System.out.println("End of turn.\n");

        for(Player player : players)
        {
            System.out.println(player.getName() + " is on tile " + player.getCurrentPosition());
        }
    }

    // --------------
    private boolean exitGame()
    {
        System.out.println("Exiting game.");
        return true;
    }

    // --------------
    private void showRollScreen(int playerRoll)
    {
        System.out.println("You made " + playerRoll + " moves forward.");

    }


    /* //FOR DEBUGGING
    public void showPlayer()
    {
        int count =0;
        for(Player currentPlayer : totalPlayers)
        {
            count++;
            String name = currentPlayer.getName();
            int pos = currentPlayer.getCurrentPosition();
            System.out.println("Player" + count +": " +name );
            System.out.println("Position: " +pos );
        }
    }
    */

    //Tiles-----------------


    /* //FOR DEBUGGING
    public void showTileNumber()
    {
        for(Tile currentTile : totalTiles)
        {
            int number = currentTile.getTileNumber();
            System.out.println("Number: " + number);
        }
    }
    */

}
