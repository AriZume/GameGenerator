import java.util.ArrayList;
import java.util.Random;
import java.nio.charset.Charset;
public class CreatePlayers {
    public static int giveNumOfPlayers() {
        int numOfPlayers = GameCreation.givePlayers();
        return numOfPlayers;
    }

    public static void generateRandomNames() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        Random rand = new Random();
        for (int counter = 1; counter <= giveNumOfPlayers(); counter += 1) {
            int randomNumber = rand.nextInt(giveNumOfPlayers());
            Object demoObject = new Player(generatedString, 0);
        }

    }
}
