package TilesPackage;

import GamePackage.Player;
import GamePackage.Tile;
import GamePackage.Response;

public class SimpleTile implements Tile
{


    @Override
    public Response updatePlayerStatus(Player player)
    {

        return new Response("\nThis tile is a simple one\n");
    }
}
