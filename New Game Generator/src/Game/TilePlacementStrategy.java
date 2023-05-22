package Game;

import CardsAndTiles.Tiles.Tile;

import java.util.List;

public interface TilePlacementStrategy
{
    void placeTiles (int enTiles, List<Tile> tiles);
}
