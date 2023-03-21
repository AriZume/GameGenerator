package MainPackage;

import java.util.ArrayList;

public class Board
{
    private ArrayList<Tile> totalTiles;
    private ArrayList<Player> totalPlayers;
    private ArrayList<Dice> boardDice;

    public Board(int diceAmount)
    {
        totalPlayers = new ArrayList<>();
        totalTiles = new ArrayList<>();
        boardDice = new ArrayList<>();
        for(int i = 0; i < diceAmount; i++)
        {
            boardDice.add(new Dice());
        }
    }

    public void gameStart()
    {
        System.out.println("All players are placed on the tile " + totalTiles.get(0).getTileNumber() );

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
    public void addTileNumber(Tile newTile)
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

    public int getDiceRoll()
    {
        int number = 0;

        System.out.print("You rolled: ");
        for(int i = 0; i < boardDice.size(); i++)
        {
            number += boardDice.get(i).getNumberRolled();
            System.out.print(boardDice.get(i).getNumberRolled());
            if(!((i+1) >= boardDice.size()))
            {
                System.out.print(" and ");
            }else
            {
                System.out.println();
            }

            boardDice.get(i).rollDice();
        }
        return number;
    }
}
