package WinningPackage;

import GamePackage.*;

public class WinByLaps {
    public boolean winByLaps(Player player, Board board)
    {
        return player.getLap() == board.getLapsToWin();
    }
}
