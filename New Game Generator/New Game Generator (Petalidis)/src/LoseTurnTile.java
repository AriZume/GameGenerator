public class LoseTurnTile extends Tile
{
    @Override
    public Response updatePlayerStatus(Player player)
    {

//        // Change the console text color to red
//        System.out.print("\033[31m");
//        System.out.println("You lose your turn. ");
//        // Reset the console color
//        System.out.print("\033[0m");
        player.setCurrentPosition(player.getCurrentPosition() - player.getPersonalRoll());
        return new Response("\033[31m" + "You lose your turn. " + "\033[0m");
    }
}
