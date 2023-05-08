package WinningConditionsPackage;

import GamePackage.Board;
import GamePackage.Player;
import GamePackage.WinningCondition;

public class WinByFirst implements WinningCondition
{
    @Override
    public boolean checkWinner(Player player, Board board)
    {
        return player.getCurrentPosition() == board.getTiles().size() && board.getBoardType().equals(Board.StringEnum.SQUARE_BOARD.getOption());
    }
}
