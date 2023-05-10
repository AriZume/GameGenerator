package WinningConditionsPackage;

import GamePackage.Board;
import GamePackage.Player;
import GamePackage.WinningCondition;
import MainPackage.EnumClass;

public class WinByPoints implements WinningCondition
{
    @Override
    public boolean checkWinner(Player player, Board board)
    {
        return player.getPoints()>=board.getMaxPoints() && board.getMaxPoints() != 0 && board.getBoardType().equals(EnumClass.BoardType.CIRCULAR_BOARD.getDescription());
    }
}
