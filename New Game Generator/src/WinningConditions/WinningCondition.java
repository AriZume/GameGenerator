package WinningConditions;

import Game.Board;
import Game.Player;

public interface WinningCondition {
    boolean checkWinner(Player p, Board board);
}
