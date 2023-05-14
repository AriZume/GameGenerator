package Tiles;

import Game.Player;
import UserInterface.Response;

public class SimpleTile implements Tile {


    @Override
    public Response updatePlayerStatus(Player player) {
        player.setIsFromEnhanced(false);
        return new Response("\nThis tile is a simple tile");
    }
}
