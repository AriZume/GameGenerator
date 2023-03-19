import java.util.ArrayList;
import java.util.List;

public class CreateBoard {
    public static void generateBoard(){
        List<Integer> numOfTiles = new ArrayList<>(GameCreation.giveTiles());
        for (int i = 0; i < GameCreation.giveTiles(); i++) {
            numOfTiles.add(0);
        }
        System.out.println(numOfTiles+" ");
    }
}
