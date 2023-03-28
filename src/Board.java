package MainPackage;

import java.util.ArrayList;
import java.util.Collections;

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

    //  --------------
    public void randomPowerUpGenerator(int tileAmount, MainMenuScreen menuScreen)
    {
        ArrayList<Integer> tempList= new ArrayList<>();

        for (int i = 0; i < tiles.size(); i++)
        {
            tempList.add(i);
            Collections.shuffle(tempList);
        }

        for (int i = 0; i < tileAmount; i++)
        {
            for(int j = 0; j < tiles.size(); j++)
            {
                if (tiles.get(j).getTileNumber() == tempList.get(i)) {
                    tiles.get(tempList.get(i)-1).setTilePower(menuScreen.getPositiveNumber(), menuScreen.getNegativeNumber());
                }
            }
        }
    }
}
