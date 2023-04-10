import java.util.Random;

public class BackwardTile extends Tile
{
    private final int power;

    public BackwardTile()
    {
        Random random = new Random();
        this.power = -(random.nextInt(6)+1);
    }

    @Override
    public int getPower()
    {
        return this.power;
    }
}
