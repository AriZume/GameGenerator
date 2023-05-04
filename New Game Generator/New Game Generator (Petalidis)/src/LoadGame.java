import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadGame
{
    private final Game game;

    public LoadGame(Game g)
    {
        game = g;
    }
    public void loadProgress()
    {
        File saveFile = new File("C:\\Users\\etzer\\Documents\\Intellij Saves\\New Game Generator (Petalidis)\\gameProgress.txt");

        int tiles = 0, enhancedTiles = 0, maxPoints = 0, laps = 0, dice = 0;
        ArrayList<String> players = new ArrayList<>();
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
                else if (key.equals("Laps"))
                {
                    laps = Integer.parseInt(value);
                }
                else if (key.equals("Dice"))
                {
                    dice = Integer.parseInt(value);
                }
            }
            game.createPlayers(players);
            game.setBoardType(boardType);
            game.setDiceAmount(dice);
            game.setLapsToWin(laps);
            game.getBoard().createTilesAndCards(tiles, enhancedTiles, maxPoints);
            game.getBoard().setMaxPoints(maxPoints);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
