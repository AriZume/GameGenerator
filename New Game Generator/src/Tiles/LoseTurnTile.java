package Tiles;

import Game.Player;
import UserInterface.Response;

public class LoseTurnTile implements Tile
{
    @Override
    public Response updatePlayerStatus(Player player)
    {
        player.setCurrentPosition(player.getCurrentPosition() - player.getPersonalRoll());
        player.setIsFromEnhanced(false);
        return new Response("\n" + "\033[31m" + "You angered the Gods. You lose your turn." + "\033[0m");
    }
}
