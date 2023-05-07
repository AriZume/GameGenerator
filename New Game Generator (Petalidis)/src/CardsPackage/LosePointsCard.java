package CardsPackage;

import GamePackage.Card;
import GamePackage.Player;
import GamePackage.Response;

import java.util.*;

public class LosePointsCard  implements Card
{

    @Override
    public Response changeStatusCard(Player player, int maxPoints)
    {
        Random random = new Random();

        int  points = -(random.nextInt(maxPoints/6));
        if((player.getPoints() + points) <=0)
        {
            player.setPoints(0);
        }
        else
        {
            player.setNewPoints(points);
        }
        return new Response("\033[31m" + "You got a card that made you lose " + (-1 * points) +
                " points. You have a total of "+ player.getPoints() + " points." + "\033[0m");
    }
}