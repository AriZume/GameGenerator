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
        // Change the console text color to blue
        System.out.print("\033[34m");
        System.out.println("You landed on a tile that provides you a Card");
        // Reset the console color
        System.out.print("\033[0m");
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
