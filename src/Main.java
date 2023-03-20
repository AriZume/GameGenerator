package MainPackage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Board myBoard = new Board();
        Tile myTiles = new Tile();

        Scanner input = new Scanner(System.in);

        int count =1;
        while(true)
        {
            System.out.println("Player" + count + ": ");
            String name = input.nextLine();
            count++;
            myBoard.addPlayer(new Player(name));
            if(name.isEmpty())
            {
                break;
            }
        }
        myBoard.showPlayer();

        System.out.println("Give the amount of tiles: ");
        int tileAmount = input.nextInt();
        count = 1;
        for(int i = 0; i < tileAmount; i++)
        {
            myBoard.setTileNumber(new Tile(count));
            count++;
        }
        myBoard.showTileNumber();
    }
}