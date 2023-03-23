package MainPackage;
import java.util.Random;

public class Dice {
    private int numberRolled;
    public Dice()
    {
        rollDice();
    }
    public void  rollDice()
    {
        Random roll = new Random();
        numberRolled= roll.nextInt(6)+1;
    }
    public int getNumberRolled()
    {
        return numberRolled;
    }
}
