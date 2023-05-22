package Game;

public class QuickGame extends Game
{
    QuickGame(QuickGameBuilder builder)
    {
        super(builder.boardType, builder.playerAm, builder.tileAm, builder.diceAm, builder.enTiles, builder.maxPoints, builder.lapsToWin);
    }

    @Override
    public void startGame()
    {
        super.startGame();
    }
}
