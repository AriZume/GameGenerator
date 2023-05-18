package Game;

import Main.EnumClass;

public class QuickGame extends Game
{
    public QuickGame(int playerAmount, int boardType)
    {
        super(playerAmount, 25, 1, 10, 1500, EnumClass.BoardType.values()[boardType-1].getDescription(), 0);
    }

    @Override
    public void startGame()
    {
        super.startGame();
    }
}
