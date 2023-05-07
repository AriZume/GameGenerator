import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveGame
{
    public void saveProgress(ArrayList <Player> players, String boardType, int tiles, int maxPoints, int laps, int dice, int enhancedTiles) {
        try{
            FileWriter writer = new FileWriter("gameProgress.txt");
            writer.write("Names: ");
            for (int i = 0; i <players.size() ; i++)
            {
                writer.write(players.get(i).getName() + " " );
            }

            writer.write("\nBoardType: " + boardType + "\nTiles: " + tiles + "\nEnhancedTiles: " + enhancedTiles + "\nMaxPoints: " + maxPoints + "\nLaps: " + laps + "\nDice: " + dice + "\n");
            writer.close();
            System.out.println("Game Saved!");
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
