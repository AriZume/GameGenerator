package MainPackage;

import java.util.ArrayList;

public class Board {
    private ArrayList<Tile> totalTiles = new ArrayList<>();
    private ArrayList<Player> totalPlayers;

    public Board()
    {
        totalPlayers = new ArrayList<>();
    }

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
}
