package IOPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import GamePackage.Game;

public class GameLoader
{
    public Game  loadProgress()
    {
        File saveFile = new File("C:\\Users\\etzer\\Documents\\Intellij Saves\\New Game Generator\\gameProgress.txt");

        boolean isLoaded = false;
        int tiles = 0, enhancedTiles = 0, maxPoints = 0, totalLaps = 0, dice = 0, playerIndex = 0;
        ArrayList<String> players = new ArrayList<>();
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
                        players.add(name.trim());
                    }
                }
                else if (key.equals("Current Position"))
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
                else if (key.equals("Laps"))
                {
                    String[] laps = value.split(" ");
                    for(String lap : laps)
                    {
                        playerLaps.add(lap.trim());
                    }
                }
                else if (key.equals("Player Index"))
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
                else if (key.equals("TotalLaps"))
                {
                    totalLaps = Integer.parseInt(value);
                }
                else if (key.equals("Dice"))
                {
                    dice = Integer.parseInt(value);
                }
                isLoaded = true;
            }
            Game game = new Game(players.size(), tiles, dice, enhancedTiles, maxPoints, isLoaded);
            game.createPlayers(players);

            game.getBoard().setBoardType(boardType);
            game.getBoard().setLapsToWin(totalLaps);
            game.setLoadPlayerIndex(playerIndex);

            for (int i=0; i < players.size();i++)
            {
                game.getPlayer().get(i).setCurrentPosition(Integer.parseInt(playerPositions.get(i)));
                game.getPlayer().get(i).setLap(Integer.parseInt(playerLaps.get(i)));
                game.getPlayer().get(i).setPoints(Integer.parseInt(playerPoints.get(i)));
            }

            //game.getBoard().setMaxPoints(maxPoints);
            //game.getBoard().createTilesAndCards(enhancedTiles, maxPoints);
            return game;
        }
        catch (FileNotFoundException e)
        {
            //e.printStackTrace();
        }
        return null;
    }
}
