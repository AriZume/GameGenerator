package Tiles;

import Game.Player;
import UserInterface.Response;

public interface Tile
{
    Response updatePlayerStatus(Player p);

    default Response fromEnhanced(Player p) {
        return new Response("");
    }

    default void resetIsFromEnhanced(Player p)
    {
        p.setIsFromEnhanced(false);
    }
}
