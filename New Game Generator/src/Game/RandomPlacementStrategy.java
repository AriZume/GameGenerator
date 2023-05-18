package Game;

import Tiles.BackwardTile;
import Tiles.ForwardTile;
import Tiles.LoseTurnTile;
import Tiles.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomPlacementStrategy implements TilePlacementStrategy {
    @Override
    public void placeTiles(int enTiles, List<Tile> tiles) {

        List<Integer> tempList = new ArrayList<>();
        for (int i = 1; i < tiles.size(); i++)
        {
            tempList.add(i);
        }
        tempList.remove(tempList.size()-1);
        Collections.shuffle(tempList);

        int loseTurnTiles = enTiles/3;
        int backwardTiles = (enTiles - loseTurnTiles) / 2;

        for (int i = 0; i < enTiles; i++)
        {
            for (int j = 0; j < tiles.size(); j++)
            {
                if (j == tempList.get(i) && i < loseTurnTiles)
                {
                    tiles.set(j, new LoseTurnTile());
                }else if(j == tempList.get(i) && i < (backwardTiles+loseTurnTiles))
                {
                    tiles.set(j, new BackwardTile());
                }
                else if(j == tempList.get(i))
                {
                    tiles.set(j, new ForwardTile());
                }
            }
        }
    }
}
