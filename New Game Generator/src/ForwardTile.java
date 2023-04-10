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
    public void updatePlayerStatus(Player player)
    {
        System.out.println("You landed on an enhanced tile and made " + this.power + " moves forward.");
        player.setNewPosition(this.power);

    }
}
