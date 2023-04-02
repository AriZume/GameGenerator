package MainPackage;

import java.util.ArrayList;

public class Board {
    private final ArrayList<Tile> tiles;

    //  CONSTRUCTOR
    public Board()
    {
        tiles = new ArrayList<>();
    }

    // METHODS
    public ArrayList<Tile> getTileList()
    {
        return tiles;
    }

    //  --------------
    public void addTileNumber(Tile newTile)
    {
        tiles.add(newTile);
    }
}
