package MainPackage;
import java.util.Random;
public class Dice {
    private int numberRolled;
    public Dice()
    {
        rollDice();
    }
    public void  rollDice(){
               Random roll = new Random();
               int dice = roll.nextInt(6)+1;
               numberRolled = dice;
    }
    public int getNumberRolled()
    {
        return numberRolled;
    }
}
