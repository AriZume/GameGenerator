package MainPackage;
import java.util.Random;
public class Dice {
    int diceAmount;

    public Dice(int amount){
        diceAmount = amount;
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
}
