package Tiles;

import java.util.*;

import Cards.GainPointsCard;
import Cards.LosePointsCard;
import Cards.PlayAgainCard;
import Cards.Card;
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
        int cardIndex = random.nextInt(cards.length);
        Card card = cards[cardIndex];

        Response response = card.getCardStatus(player, maxPoints);
         return new Response("\n" + "\033[34m" + "You landed on a tile that provides you a card\n"+ "\033[0m" + response.getMessage());
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
