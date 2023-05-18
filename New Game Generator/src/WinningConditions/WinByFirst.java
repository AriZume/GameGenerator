package WinningConditions;

import Game.Board;
import Game.Player;
import Game.BoardType;

public class WinByFirst implements WinningCondition
{
    @Override
    public boolean checkWinner(Player player, Board board)
    {
        return player.getCurrentPosition() == board.getTiles().size() && board.getBoardType().equals(BoardType.SQUARE_BOARD.getDescription());
    }
}
