package CardsAndTiles.Tiles;

import java.util.Random;

import Game.Player;
import UserInterface.Response;

public class ForwardTile implements Tile
{
    private final int power;

    public ForwardTile()
    {
        Random random = new Random();
        this.power = random.nextInt(6)+1;
    }

    @Override
    public Response updatePlayerStatus(Player player)
    {
        player.setNewPosition(this.power);
        player.setIsFromEnhanced(true);
        return new Response("\n" + "\033[32m"
                +"You landed on an enhanced tile and made " + this.power + " moves forward."
                +"\033[0m");
    }
}
