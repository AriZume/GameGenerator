package WinningConditionsPackage;

import GamePackage.Board;
import GamePackage.Player;
import GamePackage.WinningCondition;
import MainPackage.EnumClass;

public class WinByFirst implements WinningCondition
{
    @Override
    public boolean checkWinner(Player player, Board board)
    {
        return player.getCurrentPosition() == board.getTiles().size() && board.getBoardType().equals(EnumClass.BoardType.SQUARE_BOARD.getDescription());
    }
}
