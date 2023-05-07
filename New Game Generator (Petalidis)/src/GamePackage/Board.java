package GamePackage;

import java.util.ArrayList;
import java.util.Collections;
import TilesPackage.*;

public class Board
{
    private final ArrayList<Tile> tiles;

    private int maxPoints;
    private int lapsToWin;
    private String boardType;
    private int enhancedTiles = 0;
    public ArrayList<Player> players;

    public Board()
    {
        tiles = new ArrayList<>();
    }
    // Creates a simple board with no effects.
    public Board(int tileAmount)
    {
      this(tileAmount,0);
    }

    // Creates a board with enhanced tiles only.
    public Board(int tileAmount, int enTiles)
    {
        this(tileAmount,enTiles,"0");
    }

    // Creates a board with cards.
    public Board(int tileAmount, String maxPoints)
    {
        this(tileAmount,0,maxPoints);

    }

    // Creates a board with both cards and enhanced tiles.
    public Board(int tileAmount, int enTiles, String maxPoints)
    {
        this.maxPoints = Integer.parseInt(maxPoints);

        tiles = new ArrayList<>();

        for (int i = 0; i < tileAmount; i++)
        {
            tiles.add(new SimpleTile());
        }

        setupEnhancedTiles(enTiles);
        setupCardTiles(Integer.parseInt(maxPoints));
    }

    public String getBoardType() {
        return boardType;
    }
    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }
    public void setLapsToWin(int lapsToWin)
    {
        this.lapsToWin = lapsToWin;
    }
    public int getLapsToWin()
    {
        return this.lapsToWin;
    }
    public int getMaxPoints()
    {
        return maxPoints;
    }
    public void setMaxPoints(int loadedMaxPoints)
    {
        this.maxPoints = loadedMaxPoints;
    }
    public ArrayList<Tile> getTiles()
    {
        return tiles;
    }

    public void createTilesAndCards(int loadEnhancedTiles, int loadMaxPoints)
    {
        setupEnhancedTiles(loadEnhancedTiles);
        setupCardTiles(loadMaxPoints);
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
        enhancedTiles = enTiles;

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
        int maxAmountOfCardTiles = (tiles.size() / 5);
        int cardTilePositionCurrent = 0;
        int cardTilePositionPrevious = cardTilePositionCurrent;
        if(maxPoints != 0)
        {
            for (int cardTile = 0; cardTile < maxAmountOfCardTiles; cardTile++)
            {
                cardTilePositionCurrent = (cardTilePositionPrevious + ((tiles.size() - cardTilePositionPrevious) / (maxAmountOfCardTiles - cardTile)));
                tiles.set(((cardTilePositionCurrent + cardTilePositionPrevious) / 2), new CardTile(maxPoints));
                cardTilePositionPrevious = cardTilePositionCurrent;
            }
        }
    }

    public int getEnhancedTiles()
    {
        return enhancedTiles;
    }


    public Response movePlayer(Player player,int diceRoll)
    {
        player.setPersonalRoll(diceRoll);
        player.setNewPosition(player.getPersonalRoll());

        Response responseCheckPlayerPos = checkPlayerPosition(player);
        System.out.print(responseCheckPlayerPos.getMessage());

        Response responsePlayerStatus = getTiles().get(player.getCurrentPosition()-1).updatePlayerStatus(player);

        responseCheckPlayerPos = checkPlayerPosition(player);
        System.out.print(responseCheckPlayerPos.getMessage());

        // If the player is changed by an enhanced tile and lands on a card tile, execute card's updatePlayerStatus.
        // After the action reset players isFromEnhanced to false.
        if (getTiles().get(player.getCurrentPosition() - 1).getClass().getName().equals("CardTile"))
        {
            if (player.isFromEnhanced())
            {
                getTiles().get(player.getCurrentPosition() - 1).updatePlayerStatus(player);
            }
        }
        player.setIsFromEnhanced(false);

        // Might be needed if future cards move the player.
        // checkPlayerPosition(player);

        return responsePlayerStatus;
    }
    public Response checkPlayerPosition(Player player)
    {
        // Default message for lap completion when the game has points.
        Response lapAward = new Response("\n" + "\033[32m" + "You completed a lap and are awarded " + getMaxPoints() / 10 + " points!" + "\033[0m" + "\n");

        if(getBoardType().equals("Square"))
        {
            if (player.getCurrentPosition() >= getTiles().size())
            {
                player.setCurrentPosition(getTiles().size());
            }
        }
        else
        {
            if(player.getCurrentPosition() > getTiles().size())
            {
                if(getLapsToWin() != 0)
                {
                    player.increaseBy(1);
                    // Overwrites message if game has laps as winning condition.
                    lapAward = new Response("");

                }

                player.setCurrentPosition(player.getCurrentPosition() - getTiles().size());
                player.setNewPoints(getMaxPoints() / 10);
                return new Response(lapAward.getMessage());
            }
        }

        return new Response("");
    }

}
