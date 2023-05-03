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
    public Response updatePlayerStatus(Player player)
    {
        player.setIsFromEnhanced(true);
        player.setNewPosition(this.power);
        return new Response("\033[31m"
                +"You landed on an enhanced tile and made " + (-1 * this.power) + " moves backward."
                +"\033[0m");
    }
}
