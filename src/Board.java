package MainPackage;

import java.util.ArrayList;
import java.util.Collections;

public class Board {
    private final ArrayList<Tile> tiles;

    public Board()
    {
        tiles = new ArrayList<>();
    }

    public ArrayList<Tile> getTileList()
    {
        return tiles;
    }

    public void addTileNumber(Tile newTile)
    {
        tiles.add(newTile);
    }

    public void randomPowerUpGenerator(int userSetting)
    {
        ArrayList<Integer> tempList= new ArrayList<Integer>();
        MainMenuScreen menuScreen = new MainMenuScreen();
        for (int i=1; i < tiles.size(); i++)
        {
            tempList.add(i);
            Collections.shuffle(tempList);
        }
        for (int i=0; i < userSetting; i++)
        {
            if(tiles.get(i).getTileNumber() == tempList.get(i))
            {
                tiles.get(i).setTilePower(menuScreen.getPositiveNumber(), menuScreen.getNegativeNumber());
            }
        }
    }
}
