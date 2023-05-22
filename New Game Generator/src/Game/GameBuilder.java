package Game;

import java.util.ArrayList;

public class GameBuilder
{
    public String boardType;
    public int playerAm;
    public int tileAm;
    public int diceAm;
    public int enTiles;

    public int maxPoints;
    public int lapsToWin;
    private int loadedPlayerIndex;
    private ArrayList<String> loadedNames;
    private ArrayList<String> loadedPositions;
    private ArrayList<String> loadedLaps;
    private ArrayList<String> loadedPoints;


    public GameBuilder boardType(String boardType)
    {
        this.boardType = boardType;
        return this;
    }

    public GameBuilder playerAm(int playerAm)
    {
        this.playerAm= playerAm;
        return this;
    }

    public GameBuilder tileAm(int tileAm)
    {
        this.tileAm = tileAm;
        return this;
    }

    public GameBuilder diceAm(int diceAm)
    {
        this.diceAm = diceAm;
        return this;
    }

    public GameBuilder enTiles(int enTiles)
    {
        this.enTiles = enTiles;
        return this;
    }

    public GameBuilder maxPoints(int maxPoints)
    {
        this.maxPoints = maxPoints;
        return this;
    }

    public GameBuilder lapsToWin(int lapsToWin)
    {
        this.lapsToWin = lapsToWin;
        return this;
    }

    public GameBuilder loadedPlayerIndex(int loadedPlayerIndex) {
        this.loadedPlayerIndex = loadedPlayerIndex;
        return this;
    }

    public GameBuilder loadedNames(ArrayList<String> loadedNames) {
        this.loadedNames = loadedNames;
        return this;
    }

    public GameBuilder loadedPositions(ArrayList<String> loadedPositions) {
        this.loadedPositions = loadedPositions;
        return this;
    }

    public GameBuilder loadedLaps(ArrayList<String> loadedLaps) {
        this.loadedLaps = loadedLaps;
        return this;
    }

    public GameBuilder loadedPoints(ArrayList<String> loadedPoints) {
        this.loadedPoints = loadedPoints;
        return this;
    }

    public Game build() {
        return new Game(boardType, playerAm, tileAm, diceAm, enTiles, maxPoints,  lapsToWin, loadedPlayerIndex,
                loadedNames, loadedPositions, loadedLaps, loadedPoints);
    }
}
