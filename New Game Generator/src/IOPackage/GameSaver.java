package IOPackage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import GamePackage.Player;
import GamePackage.Response;

public class GameSaver
{
    public Response saveProgress(ArrayList <Player> players, String boardType, int tiles, int maxPoints, int laps, int dice, int enhancedTiles, int playerIndex) {
        String saveResponse = "";
        try{
            FileWriter writer = new FileWriter("gameProgress.txt");
            writer.write("Names: ");
            for (int i = 0; i <players.size() ; i++)
            {
                writer.write(players.get(i).getName() + " " );
            }

            writer.write("\nCurrent Position: ");
            for (Player player : players) {
                writer.write(player.getCurrentPosition() + " ");
            }

            writer.write("\nPoints: ");
            for (Player player : players) {
                writer.write(player.getPoints() + " ");
            }

            writer.write("\nLaps: ");
            for (Player player : players) {
                writer.write(player.getLap() + " ");
            }

            writer.write("\nPlayer Index: " + playerIndex + "\nBoardType: " + boardType + "\nTiles: " + tiles + "\nEnhancedTiles: " + enhancedTiles + "\nMaxPoints: " + maxPoints + "\nLaps: " + laps + "\nDice: " + dice + "\n");
            writer.close();
            saveResponse = "\nGame Saved!";
        }
        catch (IOException e)
        {
            saveResponse = "\nAn error occurred.";
            e.printStackTrace();
        }
        return new Response(saveResponse);
    }
}
