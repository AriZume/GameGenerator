import java.util.*;
public class CardTile extends Tile{
    private final Card card =new Card();
    private final Random random = new Random();
    private final int maxPoints;
    public CardTile(int maxPoints)
    {
        this.maxPoints = maxPoints;
    }



    @Override
    public void updatePlayerStatus(Player player)
    {
        System.out.println("You landed on a tile that provides you a Card");
        int cardType = random.nextInt(3)+1;
        if (cardType==1)
        {
            card.playAgainCard(player);
        }
        else if (cardType==2)
        {
            card.gainPointsCard(player, maxPoints, random);
        }
        else
        {
            card.losePointsCard(player, maxPoints, random);
        }
    }
}
