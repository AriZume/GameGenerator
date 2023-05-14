package WinningConditions;

import Game.Board;
import Game.Player;
import Main.EnumClass;

public class WinByFirst implements WinningCondition
{
    @Override
    public boolean checkWinner(Player player, Board board)
    {
        return player.getCurrentPosition() == board.getTiles().size() && board.getBoardType().equals(EnumClass.BoardType.SQUARE_BOARD.getDescription());
    }
}
