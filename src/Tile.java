package MainPackage;
import java.util.Random;

public class Tile {

    private final int tileNumber;
    private int tilePower;

    public Tile(int number)
    {
        tileNumber = number;
        tilePower = 0;
    }

    public int getTileNumber()
    {
        return tileNumber;
    }

    public int getTilePower()
    {
        return tilePower;
    }

    public void setTilePower(int positiveNumber, int negativeNumber)
    {
        Random random = new Random();
        int ranNum = random.nextInt(2) + 1;

        if (ranNum == 1)
        {
            tilePower =  random.nextInt(positiveNumber) + 1;
        } else
        {
            tilePower = -random.nextInt(negativeNumber) - 1;
        }

    }
}