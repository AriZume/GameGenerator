package MainPackage;

import java.util.Scanner;

public class MainMenuScreen {

    private int diceAmount;
    private int tileAmount;
    private int playersAmount;

    public int getOptionMainMenu()
    {
        System.out.println("\t\t  MENU\n========================\n1. Design Game and Play\n2. Load Game\n3. Help\n4. Exit");

        return InputCheck.checkIfInteger();
    }

    public void setPlayersAmount()
    {
        System.out.print("Give player amount : ");
        this.playersAmount = InputCheck.checkPlayersAmount();
    }
    public int getPlayersAmount()
    {
        return playersAmount;
    }

    public void setDiceAmount()
    {
        System.out.print("Give dice amount : ");
        this.diceAmount = InputCheck.checkDiceAmount();
    }
    public int getDiceAmount()
    {
        return diceAmount;
    }

    public void setTileAmount()
    {
        System.out.print("Give tile amount : ");
        this.tileAmount = InputCheck.checkTileAmount();
    }
    public int getTileAmount()
    {
        return tileAmount;
    }


}
