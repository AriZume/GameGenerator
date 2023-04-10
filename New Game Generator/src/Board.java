import java.util.ArrayList;
import java.util.Collections;

public class Board
{
    private final ArrayList<Tile> tiles;

    public Board(int tileAmount)
    {
        tiles = new ArrayList<>();

        for (int i = 0; i < tileAmount; i++)
        {
            tiles.add(new Tile());
        }
    }

    public Board(int tileAmount, int enhancedTiles)
    {
        tiles = new ArrayList<>();

        for (int i = 0; i < tileAmount; i++)
        {
            tiles.add(new Tile());
        }

        setupEnhancedTiles(enhancedTiles);
    }
    public ArrayList<Tile> getTiles()
    {
        return tiles;
    }

    // Initializes the position of the enhanced tiles.
    // Creates a temporary list containing the index(integer) of each tile except the first(0)
    // and the last(tiles.size()-1) index.
    // Shuffles the temp list to create a random selection.The first half of the enTiles indexes from the temp list are
    // selected to be the forward enhanced tiles, the rest are going to be the backward enhanced tiles.
    // Replaces the corresponding index's with the enhanced one's
    private void setupEnhancedTiles(int enTiles)
    {
        ArrayList<Integer> tempList = new ArrayList<>();
        for (int i = 1; i < tiles.size(); i++)
        {
            tempList.add(i);
        }
        tempList.remove(tempList.size()-1);
        Collections.shuffle(tempList);
        System.out.println(tempList);

        for (int i = 0; i < enTiles; i++)
        {
            for (int j = 0; j < tiles.size(); j++)
            {
                if (j == tempList.get(i) && i < (enTiles/2))
                {
                    tiles.remove(j);
                    tiles.add(j, new ForwardTile());
                }else if(j == tempList.get(i))
                {
                    tiles.remove(j);
                    tiles.add(j, new BackwardTile());
                }
            }
        }
    }

}
