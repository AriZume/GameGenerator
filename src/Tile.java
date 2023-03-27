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
        Random tzegkas = new Random();
        int ranNum = tzegkas.nextInt(2) + 1;

        if (ranNum == 1)
        {
            tilePower = tzegkas.nextInt(positiveNumber) + 1;
        } else
        {
            tilePower = tzegkas.nextInt(negativeNumber) - 1;
        }

    }
}