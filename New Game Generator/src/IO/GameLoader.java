package IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Game.Game;

public class GameLoader
{
    public Game  loadProgress()
    {
        File saveFile = new File(SaveFile.SAVE_FILE.getPath());

        int tiles = 0, enhancedTiles = 0, maxPoints = 0, totalLaps = 0, dice = 0, playerIndex = 0;
        ArrayList<String> playerNames = new ArrayList<>();
        ArrayList<String> playerPositions = new ArrayList<>();
        ArrayList<String> playerPoints = new ArrayList<>();
        ArrayList<String> playerLaps = new ArrayList<>();
        String boardType = "";

        try(Scanner scanner = new Scanner(saveFile))
        {
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] parts = line.split(":");
                String key = parts[0].trim();
                String value = parts[1].trim();

                if (key.equals("Names"))
                {
                    String[] names = value.split(" ");
                    for (String name : names)
                    {
                        playerNames.add(name.trim());
                    }
                }
                else if (key.equals("CurrentPosition"))
                {
                    String[] positions = value.split(" ");
                    for (String position : positions)
                    {
                       playerPositions.add(position.trim());
                    }
                }
                else if (key.equals("Points"))
                {
                    String[] points = value.split(" ");
                    for(String point : points)
                    {
                        playerPoints.add(point.trim());
                    }
                }
                else if (key.equals("PlayerLaps"))
                {
                    String[] laps = value.split(" ");
                    for(String lap : laps)
                    {
                        playerLaps.add(lap.trim());
                    }
                }
                else if (key.equals("PlayerIndex"))
                {
                    playerIndex = Integer.parseInt(value);
                }
                else if (key.equals("BoardType"))
                {
                    boardType = value;
                }
                else if (key.equals("Tiles"))
                {
                    tiles = Integer.parseInt(value);
                }
                else if (key.equals("EnhancedTiles"))
                {
                    enhancedTiles = Integer.parseInt(value);
                }
                else if (key.equals("MaxPoints"))
                {
                    maxPoints = Integer.parseInt(value);
                }
                else if (key.equals("LapsToWin"))
                {
                    totalLaps = Integer.parseInt(value);
                }
                else if (key.equals("Dice"))
                {
                    dice = Integer.parseInt(value);
                }
            }

            return new Game(boardType, playerNames.size(), tiles, dice, enhancedTiles, maxPoints, playerIndex,
                     totalLaps, playerNames, playerPositions, playerLaps, playerPoints);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
