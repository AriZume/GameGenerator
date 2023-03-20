package MainPackage;

import java.util.ArrayList;

public class Board
{
    private ArrayList<Tile> totalTiles;
    private ArrayList<Player> totalPlayers;

    public Board()
    {
        totalPlayers = new ArrayList<>();
        totalTiles = new ArrayList<>();
    }
    // Players------------
    public void addPlayer(Player newPlayer)
    {
        totalPlayers.add(newPlayer);
    }
    public void showPlayer()
    {
        int count =0;
        for(Player currentPlayer : totalPlayers)
        {
            count++;
            String name = currentPlayer.getName();
            int pos = currentPlayer.getCurrentPosition();
            System.out.println("Player" + count +": " +name );
            System.out.println("Position: " +pos );
        }
    }

    //Tiles-----------------
    public void setTileNumber(Tile newTile)
    {
        totalTiles.add(newTile);
    }
    public void showTileNumber()
    {
        for(Tile currentTile : totalTiles)
        {
            int number = currentTile.getTileNumber();
            System.out.println("Number: " + number);
        }
    }
}
