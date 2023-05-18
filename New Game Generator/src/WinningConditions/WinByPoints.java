package WinningConditions;

import Game.Board;
import Game.Player;
import Game.BoardType;

public class WinByPoints implements WinningCondition
{
    @Override
    public boolean checkWinner(Player player, Board board)
    {
        return player.getPoints()>=board.getMaxPoints() && board.getMaxPoints() != 0 && board.getBoardType().equals(BoardType.CIRCULAR_BOARD.getDescription());
    }
}
