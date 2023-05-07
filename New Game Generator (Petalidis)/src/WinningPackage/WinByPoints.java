package WinningPackage;

import GamePackage.Board;
import GamePackage.Game;
import GamePackage.Player;

public class WinByPoints {
    public boolean winByPoints(Player player, Board board)
    {
        return player.getPoints()>=board.getMaxPoints();
    }
}
