package Game;

import java.util.ArrayList;

public class QuickGameBuilder
{
    public String boardType;
    public int playerAm;
    public int tileAm;
    public int diceAm;
    public int enTiles;
    public int maxPoints;
    public int lapsToWin;


    public QuickGameBuilder boardType(String boardType)
    {
        this.boardType = boardType;
        return this;
    }

    public QuickGameBuilder playerAm(int playerAm)
    {
        this.playerAm= playerAm;
        return this;
    }

    public QuickGameBuilder tileAm(int tileAm)
    {
        this.tileAm = tileAm;
        return this;
    }

    public QuickGameBuilder diceAm(int diceAm)
    {
        this.diceAm = diceAm;
        return this;
    }

    public QuickGameBuilder enTiles(int enTiles)
    {
        this.enTiles = enTiles;
        return this;
    }

    public QuickGameBuilder maxPoints(int maxPoints)
    {
        this.maxPoints = maxPoints;
        return this;
    }

    public QuickGameBuilder lapsToWin(int lapsToWin)
    {
        this.lapsToWin = lapsToWin;
        return this;
    }

    public QuickGame build()
    {
        return new QuickGame(this);
    }
}
