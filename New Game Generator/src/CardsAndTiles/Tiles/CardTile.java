package CardsAndTiles.Tiles;

import java.util.*;

import CardsAndTiles.Cards.Card;
import CardsAndTiles.Cards.GainPointsCard;
import CardsAndTiles.Cards.LosePointsCard;
import CardsAndTiles.Cards.PlayAgainCard;
import Game.Player;
import UserInterface.Response;

public class CardTile implements Tile
{
    private final Card[] cards = new Card[]{new GainPointsCard(), new LosePointsCard(), new PlayAgainCard()};

    private final int maxPoints;

    public CardTile(int maxPoints)
    {
        this.maxPoints = maxPoints;
    }

    @Override
    public Response updatePlayerStatus(Player player)
    {
        Random random = new Random();
        Card card = cards[random.nextInt(cards.length)];

        Response response = card.getCardStatus(player, maxPoints);
         return new Response("\n" + "\033[34m" + "You landed on a tile that provides you a card\n"+ "\033[0m" + response.message());
    }
    @Override
     public Response fromEnhanced(Player player)
    {
        if (player.isFromEnhanced())
        {
            return updatePlayerStatus(player);
        }
        return new Response("");
    }

}
