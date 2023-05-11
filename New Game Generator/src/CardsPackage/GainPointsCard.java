package CardsPackage;

import GamePackage.Card;
import GamePackage.Player;
import GamePackage.Response;

import java.util.*;
public class GainPointsCard implements Card
{
    @Override
    public Response changeStatusCard(Player player, int maxPoints)
    {
        Random random = new Random();
        int addPoints= (random.nextInt(maxPoints/3));
        player.setNewPoints(addPoints);
        return new Response("\033[32m"+"This card will give you "+ addPoints +
                " points. You have a total of "+ player.getPoints() + " points!"+"\033[0m");
    }
}
