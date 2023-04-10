import java.util.Random;

public class ForwardTile extends Tile
{
    private final int power;

    public ForwardTile()
    {
        Random random = new Random();
        this.power = random.nextInt(6)+1;
    }

    @Override
    public int getPower()
    {
        return this.power;
    }
}
