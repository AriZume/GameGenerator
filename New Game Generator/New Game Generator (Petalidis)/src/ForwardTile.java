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
        // Change the console text color to green
        System.out.print("\033[32m");
        System.out.println("You landed on an enhanced tile and made " + this.power + " moves forward.");
        // Reset the console color
        System.out.print("\033[0m");
        player.setIsFromEnhanced(true);
        player.setNewPosition(this.power);

    }
}
