package MainPackage;

import java.util.ArrayList;
import java.util.Scanner;

public class Board
{
    private ArrayList<Tile> totalTiles;
    private ArrayList<Player> totalPlayers;
    private ArrayList<Dice> boardDice;

    public Board(int diceAmount)
    {
        totalPlayers = new ArrayList<>();
        totalTiles = new ArrayList<>();
        boardDice = new ArrayList<>();
        for(int i = 0; i < diceAmount; i++)
        {
            boardDice.add(new Dice());
        }
    }

    public void startGame()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\n\nAll players are placed on tile " + totalTiles.get(0).getTileNumber());
        int playerRoll;
        boolean endGame = false;
        while(!endGame)
        {
            for (int i = 0; i < totalPlayers.size(); i++)
            {
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("It's " + totalPlayers.get(i).getName() + "'s turn\n(Player " + (i+1) +")");
                System.out.println("You are placed on tile " + totalTiles.get(totalPlayers.get(i).getCurrentPosition()-1).getTileNumber() + " of " + totalTiles.size());
                System.out.println("\n1. Roll Dice\n2. Save (Unavailable)\n3. Exit");

                int option;
                do {
                    while (!input.hasNextInt()) {
                        System.out.print("Please select one of the following options: ");
                        input.next();
                        System.out.println();
                    }
                    option = input.nextInt();

                    if (option == 2) {
                        System.out.print("Saving is not available. Try a different option: ");
                        option = 4; // Probably wrong but works
                    }else if(option > 3 || option <= 0)
                    {
                        System.out.print("\nInvalid option.\nPlease select one of the following options: ");
                    }
                } while (option > 3 || option <= 0);

                switch(option)
                {
                    case 1:
                        playerRoll = getDiceRoll();

                        totalPlayers.get(i).setCurrentPosition(totalPlayers.get(i).getCurrentPosition(), playerRoll);
                        System.out.println("You made " + playerRoll + " moves forward.");
                        System.out.println("End of turn.\n");
                        for(Player p : totalPlayers)
                        {
                            System.out.println(p.getName() + " is on tile " + p.getCurrentPosition());
                        }
                        break;
                    case 3:
                        System.out.println("Exiting game.");
                        endGame = true;
                        break;
                    default:
                        System.out.println("Option not available.");
                        break;
                }

                if(endGame == true)
                {
                    break;
                }else if(totalPlayers.get(i).getCurrentPosition() >= totalTiles.size())
                {
                    System.out.println("\nWOWOWOWOOWOWOWOWOWOOWOWOWOWOW");
                    System.out.println("Player " + (i+1) + " " + totalPlayers.get(i).getName() + " won the game!\n\n\n");
                    endGame = true;
                    break;
                }
            }
        }
    }

    // Players------------
    public void addPlayer(Player newPlayer)
    {
        totalPlayers.add(newPlayer);
    }
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

    //Tiles-----------------
    public void addTileNumber(Tile newTile)
    {
        totalTiles.add(newTile);
    }
    public void showTileNumber()
    {
        for(Tile currentTile : totalTiles)
        {
            int number = currentTile.getTileNumber();
            System.out.println("Number: " + number);
        }
    }

    public int getDiceRoll()
    {
        int totalRoll = 0;

        System.out.print("You rolled: ");
        for(int i = 0; i < boardDice.size(); i++)
        {
            totalRoll += boardDice.get(i).getNumberRolled();
            System.out.print(boardDice.get(i).getNumberRolled());
            if(!((i+1) >= boardDice.size()))
            {
                System.out.print(" and ");
            }else
            {
                System.out.println();
            }

            boardDice.get(i).rollDice();
        }
        return totalRoll;
    }
}
