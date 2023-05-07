package WinningPackage;

import GamePackage.Board;
import GamePackage.Game;
import GamePackage.Player;

public class WinByLaps {
    public boolean winByLaps(Player player, Board board)
    {
        return player.getLap() == board.getLapsToWin();
    }
}
