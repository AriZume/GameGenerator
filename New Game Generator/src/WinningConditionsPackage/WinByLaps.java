package WinningConditionsPackage;

import GamePackage.Board;
import GamePackage.Player;
import GamePackage.WinningCondition;
import MainPackage.EnumClass;

public class WinByLaps implements WinningCondition
{
    @Override
    public boolean checkWinner(Player player, Board board)
    {
        return player.getLap() == board.getLapsToWin() && board.getLapsToWin() != 0 && board.getBoardType().equals(EnumClass.BoardType.CIRCULAR_BOARD.getDescription());
    }
}
