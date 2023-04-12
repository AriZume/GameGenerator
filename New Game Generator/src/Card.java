import java.util.Random;

public class Card
{
    public void playAgainCard(Player player)
    {
        System.out.println("You drew a 'Play again' card.");
        player.setHasPlayAgainCard(true);
        System.out.println("You can play again.");
    }

    public void gainPointsCard(Player player, int maxPoints, Random random)
    {
        int addPoints= (random.nextInt(maxPoints/3));
        player.setNewPoints(addPoints);
        System.out.println("This card will give you "+ addPoints+" points. You have a total of "+ player.getPoints() + " points!");
    }

    public void losePointsCard(Player player, int maxPoints, Random random)
    {
        int  points = -(random.nextInt(maxPoints/6));
        if((player.getPoints() + points) <=0)
        {
            player.setPoints(0);
            System.out.println("You got a card that made you lose " + (-1 * points) + " points. You have a total of "+ player.getPoints() + " points.");
        }
        else
        {
            player.setNewPoints(points);
            System.out.println("You got a card that made you lose " + (-1 * points) + " points. You have a total of "+ player.getPoints() + " points.");
        }

    }


}
