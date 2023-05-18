package WinningConditions;

import Game.Board;
import Game.Player;
import Game.BoardType;

public class WinByLaps implements WinningCondition
{
    @Override
    public boolean checkWinner(Player player, Board board)
    {
        return player.getLap() == board.getLapsToWin() && board.getLapsToWin() != 0 && board.getBoardType().equals(BoardType.CIRCULAR_BOARD.getDescription());
    }
}
