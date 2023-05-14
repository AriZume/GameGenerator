package Tiles;

import java.util.Random;

import Game.Player;
import UserInterface.Response;

public class BackwardTile implements Tile
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
        player.setCurrentPosition(player.getCurrentPosition() + this.power);
        player.setIsFromEnhanced(true);
        return new Response("\n" + "\033[31m"
                +"You landed on an enhanced tile and made " + (-1 * this.power) + " moves backward."
                +"\033[0m");
    }
}
