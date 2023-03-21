package MainPackage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {


        Board myBoard = new Board(2);

        Scanner input = new Scanner(System.in);

        int count =1;
        while(true)
        {
            System.out.println("Player" + count + ": ");
            String name = input.nextLine();
            if(name.isEmpty())
            {
                break;
            }
            count++;
            myBoard.addPlayer(new Player(name));
        }
        myBoard.showPlayer();

        System.out.println("Give the amount of tiles: ");
        int tileAmount = input.nextInt();
        for(int i = 1; i <= tileAmount; i++)
        {
            myBoard.addTileNumber(new Tile(i));
        }
        myBoard.showTileNumber();


        System.out.print("Give amount of dice: ");
        int diceAmount = input.nextInt();

        myBoard.getDiceRoll();
    }
}