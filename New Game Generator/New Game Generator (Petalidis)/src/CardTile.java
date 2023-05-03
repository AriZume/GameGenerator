import java.util.*;
public class CardTile extends Tile
{
    private final int maxPoints;
    private final Card[] cards = new Card[]{new GainPointsCard(), new LosePointsCard(), new PlayAgainCard()};

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

        return new Response("\n" + "\033[34m" + "You landed on a tile that provides you a Card\n"+ "\033[0m" + response.getMessage());
    }
}
