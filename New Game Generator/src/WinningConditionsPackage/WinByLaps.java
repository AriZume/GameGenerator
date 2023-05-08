package WinningConditionsPackage;

import GamePackage.Board;
import GamePackage.Player;
import GamePackage.WinningCondition;

public class WinByLaps implements WinningCondition
{
    @Override
    public boolean checkWinner(Player player, Board board)
    {
        return player.getLap() == board.getLapsToWin() && board.getLapsToWin() != 0 && board.getBoardType().equals(Board.StringEnum.CIRCULAR_BOARD.getOption());
    }
}
