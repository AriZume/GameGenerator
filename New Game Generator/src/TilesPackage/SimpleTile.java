package TilesPackage;

import GamePackage.Player;
import GamePackage.Tile;
import GamePackage.Response;

public class SimpleTile implements Tile
{


    @Override
    public Response updatePlayerStatus(Player player)
    {

        return new Response("\n This tile is a simple one\n");
    }
}
