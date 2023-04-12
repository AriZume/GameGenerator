import java.util.ArrayList;
import java.util.Collections;

public class Board
{
    private final ArrayList<Tile> tiles;
    private int maxPoints;

    // Creates a simple board with no effects.
    public Board(int tileAmount)
    {
        tiles = new ArrayList<>();

        for (int i = 0; i < tileAmount; i++)
        {
            tiles.add(new Tile());
        }
    }

    // Creates a board with enhanced tiles only.
    public Board(int tileAmount, int enTiles)
    {
        tiles = new ArrayList<>();

        for (int i = 0; i < tileAmount; i++)
        {
            tiles.add(new Tile());
        }
        setupEnhancedTiles(enTiles);
    }

    // Creates a board with cards.
    public Board(int tileAmount, String maxPoints)
    {
        this.maxPoints = Integer.parseInt(maxPoints);

        tiles = new ArrayList<>();

        for (int i = 0; i < tileAmount; i++)
        {
            tiles.add(new Tile());
        }

        setupCardTiles(Integer.parseInt(maxPoints));
    }

    // Creates a board with both cards and enhanced tiles.
    public Board(int tileAmount, int enTiles, String maxPoints)
    {
        this.maxPoints = Integer.parseInt(maxPoints);

        tiles = new ArrayList<>();

        for (int i = 0; i < tileAmount; i++)
        {
            tiles.add(new Tile());
        }

        setupEnhancedTiles(enTiles);
        setupCardTiles(Integer.parseInt(maxPoints));
    }

    public int getMaxPoints()
    {
        return maxPoints;
    }

    public ArrayList<Tile> getTiles()
    {
        return tiles;
    }

    // Initializes the position of the enhanced tiles.
    // Creates a temporary list containing the index(integer) of each tile except the first(0)
    // and the last(tiles.size()-1) index.
    // Shuffles the temp list to create a random selection.The first third of the enTiles indexes from the temp list are
    // selected to be the turn loss enhanced tiles, the next third are going to be the backward enhanced tiles
    // and the rest will be the forward enhanced tiles.
    // Replaces the corresponding index's with the enhanced one's.
    private void setupEnhancedTiles(int enTiles)
    {
        ArrayList<Integer> tempList = new ArrayList<>();
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

    // Sets the card-type tiles in the middle of each side of the board.
    private void setupCardTiles(int maxPoints)
    {
        /*
        int cardTilePosition1 = (tiles.size() / 4);
        int cardTilePosition2 = (cardTilePosition1 + ((tiles.size() - cardTilePosition1) / 3));
        int cardTilePosition3 = (cardTilePosition2 + ((tiles.size() - cardTilePosition2) / 2));
        int cardTilePosition4 = (cardTilePosition3 + (tiles.size() - cardTilePosition3));

        tiles.set((cardTilePosition1 / 2), new CardTile(maxPoints));
        tiles.set(((cardTilePosition2 + cardTilePosition1) / 2), new CardTile(maxPoints));
        tiles.set(((cardTilePosition3 + cardTilePosition2)/ 2), new CardTile(maxPoints));
        tiles.set(((cardTilePosition4 + cardTilePosition3)/ 2), new CardTile(maxPoints));

         */

        int maxAmountOfCardTiles = (tiles.size() / 5);
        int cardTilePositionCurrent = 0;
        int cardTilePositionPrevious = cardTilePositionCurrent;
        for(int cardTile = 0; cardTile < maxAmountOfCardTiles; cardTile++)
        {
            cardTilePositionCurrent = (cardTilePositionPrevious + ((tiles.size() - cardTilePositionPrevious) / (maxAmountOfCardTiles - cardTile)));
            tiles.set(((cardTilePositionCurrent + cardTilePositionPrevious) / 2), new CardTile(maxPoints));
            cardTilePositionPrevious = cardTilePositionCurrent;
        }
    }

}
