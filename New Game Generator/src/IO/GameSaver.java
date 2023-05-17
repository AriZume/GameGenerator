package IO;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Game.Player;
import Main.EnumClass;
import UserInterface.Response;

public class GameSaver
{
    public Response saveProgress(ArrayList <Player> players, String boardType, int tiles, int maxPoints, int laps, int dice, int enhancedTiles, int playerIndex) {
        String saveResponse;
        try{
            FileWriter writer = new FileWriter(EnumClass.SaveFile.SAVE_FILE.getName());
            writer.write("Names: ");
            for (int i = 0; i <players.size() ; i++)
            {
                writer.write(players.get(i).getName() + " " );
            }

            writer.write("\nCurrentPosition: ");
            for (Player player : players) {
                writer.write(player.getCurrentPosition() + " ");
            }

            writer.write("\nPoints: ");
            for (Player player : players) {
                writer.write(player.getPoints() + " ");
            }

            writer.write("\nPlayerLaps: ");
            for (Player player : players) {
                writer.write(player.getLap() + " ");
            }

            writer.write("\nPlayerIndex: " + playerIndex + "\nBoardType: " + boardType + "\nTiles: " + tiles + "\nEnhancedTiles: " + enhancedTiles + "\nMaxPoints: " + maxPoints + "\nLapsToWin: " + laps + "\nDice: " + dice + "\n");
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
