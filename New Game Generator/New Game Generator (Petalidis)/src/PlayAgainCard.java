import java.util.*;

public class PlayAgainCard extends Card
{
    @Override
    public Response changeStatusCard(Player player, int maxPoints)
    {
        // Change the console text color to green
        player.setHasPlayAgainCard(true);
        return new Response("\033[32m\n"+"You drew a 'Play again' card.\n"+"You can play again."+"\033[0m");
    }

}
