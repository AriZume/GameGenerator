import java.util.ArrayList;
import java.util.List;

public class CreateBoard {
    static List<Integer> numOfTiles = new ArrayList<>(GameCreation.giveTiles());
    public static void generateBoard() {

        for (int i = 0; i < GameCreation.giveTiles(); i++) {
            numOfTiles.add(0);
        }
        System.out.println(numOfTiles + " ");
    }

    public static List getTilesList()
    {
        return numOfTiles;
    }
}
