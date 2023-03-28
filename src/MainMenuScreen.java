package MainPackage;

import java.util.Scanner;

public class MainMenuScreen {

    private int diceAmount;
    private int tileAmount;
    private int playersAmount;
    private int positiveNumber;
    private int negativeNumber;

    //  METHODS
    public void setPositiveNumber(int posNumber)
    {
        positiveNumber = posNumber;
    }

    //  --------------
    public void setNegativeNumber(int negNumber)
    {
        negativeNumber = negNumber;
    }

    //  --------------
    public int getPositiveNumber()
    {
        return positiveNumber;
    }

    //  --------------
    public int getNegativeNumber()
    {
        return negativeNumber;
    }

    //  --------------
    public int getOptionMainMenu()
    {
        System.out.println("\t\t  MENU\n========================\n1. Design Game and Play\n2. Load Game\n3. Help\n4. Exit");

        return InputCheck.checkIfInteger();
    }

    //  --------------
    public void setPlayersAmount()
    {
        System.out.print("Give player amount : ");
        this.playersAmount = InputCheck.checkPlayersAmount();
    }

    //  --------------
    public int getPlayersAmount()
    {
        return playersAmount;
    }

    //  --------------
    public void setDiceAmount()
    {
        System.out.print("Give dice amount : ");
        this.diceAmount = InputCheck.checkDiceAmount();
    }

    //  --------------
    public int getDiceAmount()
    {
        return diceAmount;
    }

    //  --------------
    public void setTileAmount()
    {
        System.out.print("Give tile amount : ");
        this.tileAmount = InputCheck.checkTileAmount();
    }

    //  --------------
    public int getTileAmount()
    {
        return tileAmount;
    }

    //  --------------
    public void tilePowerScreen()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Would you like power-ups on your tiles?\nType 'Y' for Yes or 'N' for No: ");
        String userInput = InputCheck.checkIfYesNo();
        if(userInput.equals("Y"))
        {
            System.out.print("Please set the maximum amount of the forward powered up tiles: ");
            int numInput = InputCheck.checkIfInteger();
            setPositiveNumber(numInput);

            System.out.print("Please set the maximum amount of the backward powered up tiles: ");
            numInput = InputCheck.checkIfInteger();
            setNegativeNumber(numInput);
        }
    }

    //  --------------
    public void loadScreen()
    {
        System.out.println("Load is currently unavailable.");
    }

    //  --------------
    public void helpScreen() {
        System.out.println("Can't give you help :(\nPlease donate $19.99 to unlock GameGenerator Premium.");
    }

    //  --------------
    public void defaultScreen() {
        System.out.println("Option not valid!");
    }
}
