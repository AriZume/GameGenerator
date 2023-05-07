package WinningPackage;

import GamePackage.*;

public class WinByPoints {
    public boolean winByPoints(Player player, Board board)
    {
        return player.getPoints()>=board.getMaxPoints();
    }
}
