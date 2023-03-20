package MainPackage;
import java.util.Random;
public class Dice {
    private int diceAmount;
    public Dice(){
        diceAmount = 1;
    }
    public int  rollDice(){
               Random roll = new Random();
               int dice = roll.nextInt(6)+1;
               return dice;
    }
    public int getTotal(){
        if (diceAmount == 2 ){
            return  rollDice()+rollDice();
        }
        else {
            return rollDice();
        }
    }

    public void setDiceAmount(int amount)
    {
        diceAmount = amount;
    }
}
