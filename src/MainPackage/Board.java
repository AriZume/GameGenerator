package MainPackage;

import TilesPackage.EnhancedTile;

import java.util.ArrayList;

public class Board {
    private final ArrayList<EnhancedTile> tiles;

    //  CONSTRUCTOR
    public Board()
    {
        tiles = new ArrayList<>();
    }

    // METHODS
    public ArrayList<EnhancedTile> getTileList()
    {
        return tiles;
    }

    //  --------------
    public void addTileNumber(EnhancedTile newTile)
    {
        tiles.add(newTile);
    }
}
