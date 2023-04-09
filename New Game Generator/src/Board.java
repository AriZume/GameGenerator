import java.util.ArrayList;

public class Board
{
    private final ArrayList<Tile> tiles;

    public Board(int tileAmount)
    {
        tiles = new ArrayList<>();

        for (int i = 0; i < tileAmount; i++) {
            tiles.add(new Tile());
        }
    }

    public ArrayList<Tile> getTiles()
    {
        return tiles;
    }

}
