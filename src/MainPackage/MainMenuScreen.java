package MainPackage;

import java.util.Scanner;

public class MainMenuScreen {

    private int diceAmount;
    private int tileAmount;
    private int playersAmount;
    private int positiveNumber;
    private int negativeNumber;
    private int enhancedTileAmount;

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
    private void setEnhancedTileAmount(int amount)
    {
        enhancedTileAmount = amount;
    }

    public int getEnhancedTileAmount()
    {
        return enhancedTileAmount;
    }
    public int getOptionMainMenu()
    {
        System.out.println("\t\t  MENU\n========================\n1. Design Game and Play\n2. Load Game\n3. Help\n4. Exit");

        return UserInputCheck.checkIfInteger();
    }

    //  --------------
    public void setPlayersAmount()
    {
        System.out.print("Give player amount : ");
        this.playersAmount = UserInputCheck.checkPlayersAmount();
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
        this.diceAmount = UserInputCheck.checkDiceAmount();
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
        this.tileAmount = UserInputCheck.checkTileAmount();
    }

    //  --------------
    public int getTileAmount()
    {
        return tileAmount;
    }

    //  --------------
    public String tilePowerScreen()
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Would you like enhanced tiles on your board?\nType 'Y' for Yes or 'N' for No: ");
        String userInput = UserInputCheck.checkIfYesNo();

        if(userInput.equals("Y") || userInput.equals("y"))
        {
            System.out.print("How many enhanced tiles would you like: ");
            int tileAmount = input.nextInt();
            setEnhancedTileAmount(tileAmount);

            System.out.print("Please set the maximum amount of forward enhanced tiles: ");
            int numInput = UserInputCheck.checkIfInteger();
            setPositiveNumber(numInput);

            System.out.print("Please set the maximum amount of backward enhanced tiles: ");
            numInput = UserInputCheck.checkIfInteger();
            setNegativeNumber(numInput);
            //myBoard.randomPowerUpGenerator(tileAmount, getPositiveNumber(), getNegativeNumber());
        }
        return userInput;
    }

    //  --------------
    public void loadScreen()
    {
        System.out.println("Load is currently unavailable.");
    }

    //  --------------
    public void helpScreen() {
        System.out.println("There is no help in the free version :(\nPlease donate $19.99 to unlock GameGenerator Premium.");
    }

    //  --------------
    public void defaultScreen() {
        System.out.println("Option not valid!");
    }
}
