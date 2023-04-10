public class LoseTurnTile extends Tile
{
    @Override
    public void updatePlayerStatus(Player player)
    {
        System.out.println("You lose your turn. ");
        player.setCurrentPosition(player.getCurrentPosition() - player.getPersonalRoll());
    }
}
