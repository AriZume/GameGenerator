package TilesPackage;

import java.util.*;

import CardsPackage.GainPointsCard;
import CardsPackage.LosePointsCard;
import CardsPackage.PlayAgainCard;
import GamePackage.Card;
import GamePackage.Player;
import GamePackage.Tile;
import GamePackage.Response;

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

        Response response = card.changeStatusCard(player, maxPoints);

        return new Response("\n" + "\033[34m" + "You landed on a tile that provides you a card\n"+ "\033[0m" + response.getMessage() + "\n");
    }
}
