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
    public void updatePlayerStatus(Player player)
    {
        System.out.println("You landed on an enhanced tile and made " + (-1 * this.power) + " moves backward.");
        player.setIsFromEnhanced(true);
        player.setNewPosition(this.power);
    }
}
