package Game;

import java.util.*;

import Tiles.*;
import UserInterface.Response;
import WinningConditions.*;

public class Board
{
    private final WinningCondition[] winningConditions = new WinningCondition[]{new WinByFirst(), new WinByLaps(), new WinByPoints()};
    private final ArrayList<Tile> tiles;
    private final int maxPoints;
    private final int lapsToWin;
    private final String boardType;
    private int enhancedTiles = 0;
    private TilePlacementStrategy tilePlacementStrategy = new RandomPlacementStrategy();

    public Board(int tileAmount, int enTiles, int maxPoints, String boardType, int lapsToWin)
    {
        this.maxPoints = maxPoints;
        this.boardType = boardType;
        this.lapsToWin = lapsToWin;
        tiles = new ArrayList<>();

        for (int i = 0; i < tileAmount; i++)
        {
            tiles.add(new SimpleTile());
        }

        generateEnhancedTiles(enTiles);
        generateCardTiles(maxPoints);
    }

    public void setTilePlacementStrategy(TilePlacementStrategy tilePlacementStrategy) {
        this.tilePlacementStrategy = tilePlacementStrategy;
    }

    public String getBoardType() {
        return boardType;
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
    private void generateEnhancedTiles(int enTiles)
    {
        enhancedTiles = enTiles;
        tilePlacementStrategy.placeTiles(enTiles,tiles);
    }

    // Generates a fair amount of card tiles depending on the board size.
    private void generateCardTiles(int maxPoints)
    {
        int maxAmountOfCardTiles = (tiles.size() / 5), cardTilePositionCurrent = 0;
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


    public List<Response> movePlayer(Player player, int diceRoll)
    {
        List<Response> responses = new ArrayList<>();

        player.setPersonalRoll(diceRoll);
        player.setNewPosition(player.getPersonalRoll());

        responses.add(checkPlayerPosition(player));
        responses.add(getTiles().get(player.getCurrentPosition()-1).updatePlayerStatus(player));
        responses.add(checkPlayerPosition(player));

        // If the player's position is changed by an enhanced tile and the player lands on a card tile,
        // execute card's updatePlayerStatus. After the action reset player's isFromEnhanced to false.
        responses.add(getTiles().get(player.getCurrentPosition() - 1).fromEnhanced(player));
        getTiles().get(player.getCurrentPosition() - 1).resetIsFromEnhanced(player);
        return responses;
    }

    // Keeps position updated according to the board type.
    public Response checkPlayerPosition(Player player)
    {
        String lapRewardMessage = "";
        if(boardType.equals(BoardType.SQUARE_BOARD.getDescription()))
        {
            if(player.getCurrentPosition() > getTiles().size())
            {
                player.setCurrentPosition(getTiles().size());
            }
        }
        else if(boardType.equals(BoardType.CIRCULAR_BOARD.getDescription()))
        {
            if(player.getCurrentPosition() > getTiles().size())
            {
                lapRewardMessage = updateLap(player).message();
                player.setCurrentPosition(player.getCurrentPosition() - getTiles().size());
            }
        }
        return new Response(lapRewardMessage);
    }

    private Response updateLap(Player player)
    {
        int lapCompletionRewardValue = getMaxPoints() / 10;
        String rewardMessage = "\n" + "\033[32m" + "You completed a lap and are awarded " + lapCompletionRewardValue
                + " points!" + "\033[0m";

        if(getLapsToWin() != 0)
        {
            player.increaseBy(1);
            rewardMessage = "";
        }

        player.setNewPoints(lapCompletionRewardValue); // Set points awarded to the player when completing a lap.
        return new Response(rewardMessage);
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
