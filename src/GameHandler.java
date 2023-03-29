package MainPackage;

import java.util.*;



public class GameHandler {
    private final ArrayList<Player> totalPlayers;
    private final ArrayList<Tile> totalTiles;
    private final int dice;

    // CONSTRUCTOR
    public GameHandler(int diceAmount, Board tileList)
    {
        totalPlayers = new ArrayList<>();
        totalTiles = tileList.getTileList();
        dice = diceAmount;
    }

    // METHODS
    public void startGame(int positiveNumber, int negativeNumber)
    {
        setRandomEnhancedTilerPower(totalTiles.size(), positiveNumber, negativeNumber);

        setPlayerPriority();

        System.out.println("\n\nAll players are placed on tile " + totalTiles.get(0).getTileNumber());

        int playerRoll;
        boolean endGame = false;

        while (!endGame)
        {
            // PLAYER TURNS
            for (Player player : totalPlayers)
            {
                int option = getOption(player.getName(), player.getCurrentPosition(), totalTiles.size(), totalPlayers.indexOf(player));

                //IN-GAME MENU
                switch (option)
                {
                    case 1:
                        playerRoll = getDiceRoll(dice);
                        player.setCurrentPosition(playerRoll);

                        if (player.getCurrentPosition() >=  totalTiles.size())
                        {
                            player.setCurrentPosition(totalTiles.size() - player.getCurrentPosition());
                        }

                        showRollScreen(playerRoll);

                        checkEnhancedTile(player, totalTiles);

                        if (player.getCurrentPosition() >=  totalTiles.size())
                        {
                            player.setCurrentPosition(totalTiles.size() - player.getCurrentPosition());
                        }
                        else if(player.getCurrentPosition() < 0)
                        {
                            player.resetPosition();
                        }

                        showPlayerPositionEndTurn(totalPlayers);
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
                            endGame = winnerScreen(totalPlayers.indexOf(player), player.getName());
                            break;
                        }
            }
        }
    }

    // --------------
    public void addPlayer(Player newPlayer)
    {
        totalPlayers.add(newPlayer);
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
    private void checkEnhancedTile(Player player, ArrayList < Tile > tiles)
    {
        if (totalTiles.get(player.getTileIndex()).getTilePower() != 0)
        {
            if (tiles.get(player.getCurrentPosition()).getTilePower() > 0)
            {
                System.out.println("You landed on an enhanced tile and made " + tiles.get(player.getCurrentPosition()).getTilePower()+ " forward.");
            } else if (tiles.get(player.getCurrentPosition()).getTilePower() < 0)
            {
                System.out.println("You landed on an enhanced tile and made " + (-1 * tiles.get(player.getCurrentPosition()).getTilePower())+" backwards.");
            }

            player.setCurrentPosition(totalTiles.get(player.getCurrentPosition()).getTilePower());
        }
    }

    // --------------
    public void setRandomEnhancedTilerPower(int tileAmount, int posNumber, int negNumber)
    {
        ArrayList<Integer> tempList= new ArrayList<>();

        for (int i = 0; i < totalTiles.size(); i++)
        {
            tempList.add(i);
            Collections.shuffle(tempList);
        }

        for (int i = 1; i < tileAmount; i++)
        {
            for (Tile tile : totalTiles) {
                if (tile.getTileNumber() == tempList.get(i)) {
                    totalTiles.get(tempList.get(i) - 1).setTilePower(posNumber, negNumber);
                }
            }
        }
    }

    // --------------
    private void setPlayerPriority()
    {
        System.out.print("\nTime to see who's playing first.");

        for (Player player : totalPlayers) {
            priorityActionScreen(player.getName());

            int roll = getDiceRoll(dice);

            player.setPriorityRoll(roll);
        }

        totalPlayers.sort(Comparator.comparing(Player::getPriorityRoll).reversed());

        priorityResults(totalPlayers);
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

            if (!(userInput.equals("R") || userInput.equals("r"))) {
                System.out.print("Please try again: ");
            }
        } while (!(userInput.equals("R") || userInput.equals("r")));
    }

    //  --------------
    private void priorityResults(ArrayList < Player > players)
    {
        System.out.print("\nPlayer 1 (" + players.get(0).getName() + ") is starting first");
        for (int i = 1; i < players.size(); i++)
        {
            System.out.print("\nPlayer " + (i + 1) + " (" + players.get(i).getName() + ")");
        }
    }

    // IN-GAME MENU OPTION
    private int getOption(String playerName,int tileNumber, int totalNumberOfTiles, int playerIndex){
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

            if (option == 2) {
                System.out.print("Saving is not available. Try a different option: ");
                option = -1; // Probably wrong way to do it, but works
            } else if (option > 3 || option <= 0)
            {
                System.out.print("\nInvalid option.\nPlease select one of the other options: ");
            }
        } while (option > 3 || option <= 0);

        return option;
    }

    // --------------
    private boolean winnerScreen ( int playerNumber, String playerName)
    {
        System.out.println("-----------------------------------------------------------------------\n" +
        "Player " + (playerNumber + 1) + ": " + playerName + " won the game!\n\n\n");

        return true;
    }

    // --------------
    private void showPlayerPositionEndTurn (ArrayList < Player > players)
    {
        System.out.println("End of turn.\n");

        for (Player player : players)
        {
            System.out.println(player.getName() + " is on tile " + player.getCurrentPosition());
        }
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