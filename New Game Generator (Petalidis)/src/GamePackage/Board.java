package GamePackage;

import java.util.ArrayList;
import java.util.Collections;
import TilesPackage.*;
import WinningConditionsPackage.*;

public class Board
{
    public enum StringEnum
    {
        SQUARE_BOARD("Square"),
        CIRCULAR_BOARD("Circular"),
        CARD_TILE("CardTile");
        public final String option;
        StringEnum(String option)
        {
            this.option = option;
        }
        public String getOption()
        {
            return option;
        }

    }
    private final WinningCondition[] winningConditions = new WinningCondition[]{new WinByFirst(), new WinByLaps(), new WinByPoints()};
    private final ArrayList<Tile> tiles;
    private final int maxPoints;
    private int lapsToWin = 0;
    private String boardType;
    private int enhancedTiles = 0;

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
    public ArrayList<Tile> getTiles()
    {
        return tiles;
    }
    public int getEnhancedTiles()
    {
        return enhancedTiles;
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

    public Response movePlayer(Player player,int diceRoll)
    {
        Response responsePlayerStatus;

        player.setPersonalRoll(diceRoll);
        player.setNewPosition(player.getPersonalRoll());

        Response responseCheckPlayerPos = checkPlayerPosition(player);
        System.out.print(responseCheckPlayerPos.getMessage());

        responsePlayerStatus = getTiles().get(player.getCurrentPosition()-1).updatePlayerStatus(player);

        responseCheckPlayerPos = checkPlayerPosition(player);
        System.out.print(responseCheckPlayerPos.getMessage());

        // If the player is changed by an enhanced tile and lands on a card tile, execute card's updatePlayerStatus.
        // After the action reset players isFromEnhanced to false.
        if (getTiles().get(player.getCurrentPosition() - 1).getClass().getName().equals(StringEnum.CARD_TILE.getOption()))
        {
            if (player.isFromEnhanced())
            {
                getTiles().get(player.getCurrentPosition() - 1).updatePlayerStatus(player);
                player.setIsFromEnhanced(false);
            }
        }
        return responsePlayerStatus;
    }

    // Keeps position updated according to the board type.
    public Response checkPlayerPosition(Player player)
    {
        String lapAwardMessage = "";
        if(boardType.equals(StringEnum.SQUARE_BOARD.getOption()))
        {
            if(player.getCurrentPosition() >= getTiles().size())
            {
                player.setCurrentPosition(getTiles().size());
            }
        }
        else if(boardType.equals(StringEnum.CIRCULAR_BOARD.getOption()))
        {
            if(player.getCurrentPosition() > getTiles().size())
            {
                lapAwardMessage = updateLap(player).getMessage();
                player.setCurrentPosition(player.getCurrentPosition() - getTiles().size());
            }
        }
        return new Response(lapAwardMessage);
    }

    private Response updateLap(Player player)
    {
        String awardMessage = "\n" + "\033[32m" + "You completed a lap and are awarded " + getMaxPoints() / 10
                + " points!" + "\033[0m" + "\n";
        if(getLapsToWin() != 0)
        {
            player.increaseBy(1);
            awardMessage = "";
        }

        player.setNewPoints(getMaxPoints() / 10); // Set points awarded to the player when completing a lap.
        return new Response(awardMessage);
    }

    public boolean isWinner(Player player, Board board)
    {
        for(WinningCondition condition : winningConditions)
        {
            if(condition.checkWinner(player, board))
            {
                return true;
            }
        }
        return false;
    }
}
