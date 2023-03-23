package MainPackage;

import java.util.Scanner;

public class PlayGameScreen {
    public static int getOption(String playerName, int tileNumber, int totalNumberOfTiles, int playerIndex) {
        Scanner input = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("It's " +playerName + "'s turn\n(Player " + (playerIndex +1) +")");
        System.out.println("You are placed on tile " + tileNumber + " of " + totalNumberOfTiles);
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
            else if(option > 3 || option <= 0)
            {
                System.out.print("\nInvalid option.\nPlease select one of the other options: ");
            }
        } while (option > 3 || option <= 0);
        return option;
    }
}
