public class LoseTurnTile extends Tile
{
    @Override
    public Response updatePlayerStatus(Player player)
    {
        player.setCurrentPosition(player.getCurrentPosition() - player.getPersonalRoll());
        return new Response("\033[31m" + "You lose your turn. " + "\033[0m");
    }
}
