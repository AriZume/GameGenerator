package WinningPackage;

import GamePackage.Board;
import GamePackage.Game;
import GamePackage.Player;

public class WinByFirst {
    public boolean winByFirst(Player player, Board board)
    {
        return player.getCurrentPosition() == board.getTiles().size();
    }
}
