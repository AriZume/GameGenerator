package CardsAndTiles.Cards;

import Game.Player;
import UserInterface.Response;

public class PlayAgainCard implements Card
{
    @Override
    public Response getCardStatus(Player player, int maxPoints)
    {
        player.setHasPlayAgainCard(true);
        return new Response("\033[32m" + "The Gods gave you another chance. You can play again." + "\033[0m");
    }

}
