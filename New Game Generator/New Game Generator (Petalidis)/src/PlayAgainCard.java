
public class PlayAgainCard extends Card
{
    @Override
    public Response changeStatusCard(Player player, int maxPoints)
    {
        player.setHasPlayAgainCard(true);
        return new Response("\n" + "\033[32m" + "You drew a 'Play again' card.\n" + "You can play again." + "\033[0m" + "\n");
    }

}
