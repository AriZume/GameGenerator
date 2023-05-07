package WinningConditionsPackage;

import GamePackage.Board;
import GamePackage.Player;
import GamePackage.WinningCondition;

public class WinByPoints implements WinningCondition
{
    @Override
    public boolean checkWinner(Player player, Board board)
    {
        return player.getPoints()>=board.getMaxPoints();
    }
}
