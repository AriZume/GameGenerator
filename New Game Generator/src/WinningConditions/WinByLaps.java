package WinningConditions;

import Game.Board;
import Game.Player;
import Main.EnumClass;

public class WinByLaps implements WinningCondition
{
    @Override
    public boolean checkWinner(Player player, Board board)
    {
        return player.getLap() == board.getLapsToWin() && board.getLapsToWin() != 0 && board.getBoardType().equals(EnumClass.BoardType.CIRCULAR_BOARD.getDescription());
    }
}
