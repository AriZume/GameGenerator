package CardsPackage;

import GamePackage.Card;
import GamePackage.Player;
import GamePackage.Response;

public class PlayAgainCard implements Card
{
    @Override
    public Response changeStatusCard(Player player, int maxPoints)
    {
        player.setHasPlayAgainCard(true);
        return new Response("\033[32m" + "The Gods gave you another chance. You can play again." + "\033[0m");
    }

}
