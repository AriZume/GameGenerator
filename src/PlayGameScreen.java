package MainPackage;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayGameScreen {
    public static void priorityScreen(String playerName)
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

    public  static  void priorityResults(ArrayList<Player> players)
    {
        System.out.print("\nPlayer 1 (" + players.get(0).getName() + ") is starting first");
        for(int i = 1; i < players.size(); i++)
        {
            System.out.print("\nPlayer " + (i+1) + " (" + players.get(i).getName() + ")");
        }
    }

    public static int getOption(String playerName, int tileNumber, int totalNumberOfTiles, int playerIndex) {
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

    public static boolean winnerScreen(int playerNumber, String playerName)
    {
        System.out.println("-----------------------------------------------------------------------\n" +
                           "Player " + (playerNumber+1) + ": " + playerName + " won the game!\n\n\n");
        return true;
    }

    public static void endTurnScreen(int playerRoll, ArrayList<Player> players)
    {
        System.out.println("You made " + playerRoll + " moves forward.");
        System.out.println("End of turn.\n");
        for(Player player : players)
        {
            System.out.println(player.getName() + " is on tile " + player.getCurrentPosition());
        }
    }

    public static boolean exitGame()
    {
        System.out.println("Exiting game.");
        return true;
    }
}
