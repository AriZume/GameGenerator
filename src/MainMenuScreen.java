package MainPackage;

import java.util.Scanner;

public class MainMenuScreen {

    private int diceAmount;
    private int tileAmount;
    private int playersAmount;
    private int positiveNumber;
    private int negativeNumber;

    private Scanner input = new Scanner(System.in);

    public int getOptionMainMenu()
    {
        int userInput;
        System.out.println("\t\t  MENU\n========================\n1. Design Game and Play\n2. Load Game\n3. Help\n4. Exit");

        do
        {
            while (!input.hasNextInt())
            {
                System.out.print("Option should be number: ");
                input.next();
            }
            userInput = input.nextInt();
            if (userInput <= 0 || userInput >= 5)
            {
                System.out.print("Option should be 1 to 4: ");
            }
        }while  (userInput <= 0 || userInput >= 5);

        return userInput;
    }

    public void setPlayersAmount()
    {
        System.out.print("Give player amount : ");
        this.playersAmount = checkPlayersAmount();
    }
    public int getPlayersAmount()
    {
        return playersAmount;
    }
    private int checkPlayersAmount()
    {
        int userInput;
        do
        {
            while (!input.hasNextInt())
            {
                System.out.print("Player amount should be number: ");
                input.next();
            }
            userInput = input.nextInt();
            if (userInput < 2)
            {
                System.out.print("Player amount should be 2 or more: ");
            }
        }while  (userInput < 2);
        return userInput;
    }

    public void setDiceAmount()
    {
        System.out.print("Give dice amount : ");
        this.diceAmount = checkDiceAmount();
    }
    public int getDiceAmount()
    {
        return diceAmount;
    }
    private int checkDiceAmount()
    {
        int userInput;
        do
        {
            while (!input.hasNextInt())
            {
                System.out.print("Dice amount should be number: ");
                input.next();
            }
            userInput = input.nextInt();
            if (userInput < 1)
            {
                System.out.print("Dice amount should be 1 or more: ");
            }
        } while (userInput < 1);
        return userInput;
    }


    public void setTileAmount()
    {
        System.out.print("Give tile amount : ");
        this.tileAmount = checkTileAmount();
    }
    public int getTileAmount()
    {
        return tileAmount;
    }
    private int checkTileAmount()
    {
        int userInput;
        do
        {
            while (!input.hasNextInt())
            {
                System.out.print("Tiles should be number: ");
                input.next();
            }
            userInput = input.nextInt();
            if (userInput < 1)
            {
                System.out.print("Tiles should be 1 or more: ");
            }
        } while (userInput < 1);
        return userInput;
    }

    public void tilePowerScreen()
    {
        System.out.print("\nWould you like power-ups on your tiles?\nType 'Y' for Yes or 'N' for No: ");
        String userInput = input.nextLine();
        if(userInput.equals('Y'))
        {
            System.out.print("\nPlease set the maximum amount of the forward powered up tiles: ");
            int numInput = input.nextInt();
            setPositiveNumber(numInput);

            System.out.print("\nPlease set the maximum amount of the backward powered up tiles: ");
            numInput = input.nextInt();
            setNegativeNumber(numInput);
        }

    }

    public void setPositiveNumber(int posNumber)
    {
        positiveNumber = posNumber;
    }

    public void setNegativeNumber(int negNumber)
    {
        negativeNumber = negNumber;
    }

    public int getPositiveNumber()
    {
        return positiveNumber;
    }

    public int getNegativeNumber()
    {
        return negativeNumber;
    }

}
