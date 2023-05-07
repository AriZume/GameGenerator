package WinningPackage;

import GamePackage.Player;
import GamePackage.Response;

public interface WinningCondition {
    Response updateWinningCondition(Player p);
}
