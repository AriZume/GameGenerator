package MainPackage;

import java.util.Scanner;

public class InputCheck {

    //  METHODS
    public static int checkIfInteger()
    {
        Scanner input  = new Scanner(System.in);
        int userInput;

        do
        {
            while (!input.hasNextInt())
            {
                System.out.print("Please try again: ");
                input.next();
            }
            userInput = input.nextInt();
            if(userInput <= 0)
            {
                System.out.println("Please try again: ");
            }
        }while(userInput <= 0);

        return userInput;
    }

    //  --------------
    public static int checkPlayersAmount()
    {
        int userInput;
        do
        {
            userInput = InputCheck.checkIfInteger();

            if (userInput < 2)
            {
                System.out.print("Player amount should be 2 or more: ");
            }
        }while  (userInput < 2);
        return userInput;
    }

    //  --------------
    public static int checkDiceAmount()
    {
        int userInput;
        do
        {
            userInput = InputCheck.checkIfInteger();

            if (userInput < 1)
            {
                System.out.print("Dice amount should be 1 or more: ");
            }
        } while (userInput < 1);
        return userInput;
    }

    //  --------------
    public static int checkTileAmount()
    {
        int userInput;
        do
        {
            userInput = InputCheck.checkIfInteger();

            if (userInput < 1)
            {
                System.out.print("Tiles should be 1 or more: ");
            }
        } while (userInput < 1);
        return userInput;
    }

    //  --------------
    public static String checkIfYesNo()
    {
        Scanner input = new Scanner(System.in);
        String userInput;
        do
        {
            userInput = input.nextLine();
            if (!userInput.matches("[yYnN]"))
            {
                System.out.print("Please try again: ");
            }
        } while (!userInput.matches("[yYnN]"));
        return userInput;
    }
}
