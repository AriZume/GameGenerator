package Game;

import java.util.ArrayList;

public class Director
{
    public void buildNewGame(GameBuilder builder, String boardType, int playerAmount, int tileAmount, int diceAmount,
                             int enhancedTiles, int maxPoints, int lapsToWin)
    {
        builder.boardType(boardType)
                .playerAm(playerAmount)
                .tileAm(tileAmount)
                .diceAm(diceAmount)
                .enTiles(enhancedTiles)
                .maxPoints(maxPoints)
                .lapsToWin(lapsToWin);
    }

    public void buildLoadedGame(GameBuilder builder, String boardType, int playerAmount, int tileAmount, int diceAmount,
                                int enhancedTiles, int maxPoints, int lapsToWin, int playerIndex,
                                ArrayList<String> loadedNames, ArrayList<String> loadedPositions,
                                ArrayList<String> loadedPoints, ArrayList<String> loadedLaps)
    {
        builder.boardType(boardType)
                .playerAm(playerAmount)
                .tileAm(tileAmount)
                .diceAm(diceAmount)
                .enTiles(enhancedTiles)
                .maxPoints(maxPoints)
                .lapsToWin(lapsToWin)
                .loadedPlayerIndex(playerIndex)
                .loadedNames(loadedNames)
                .loadedPositions(loadedPositions)
                .loadedPoints(loadedPoints)
                .loadedLaps(loadedLaps);
    }

    public void buildQuickGame(GameBuilder builder, String boardType, int playerAmount)
    {
        if(boardType.equals(BoardType.SQUARE_BOARD.getDescription()))
        {
            builder.boardType(boardType)
                    .playerAm(playerAmount)
                    .tileAm(50)
                    .diceAm(2)
                    .enTiles(20)
                    .maxPoints(0)
                    .lapsToWin(0);
        }
        else
        {
            builder.boardType(boardType)
                    .playerAm(playerAmount)
                    .tileAm(25)
                    .diceAm(1)
                    .enTiles(10)
                    .maxPoints(1000)
                    .lapsToWin(0);
        }


    }
}
