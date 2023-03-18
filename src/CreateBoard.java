import java.util.ArrayList;
import java.util.List;

public class CreateBoard {
    public static void GenerateBoard(){
        List<String> numOfTiles = new ArrayList<>(GameCreation.giveTiles());
        for (int i = 0; i < GameCreation.giveTiles(); i++) {
            numOfTiles.add("[/]");
        }
        System.out.println(numOfTiles);
    }
}
