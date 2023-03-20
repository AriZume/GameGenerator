package MainPackage;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Board myBoard = new Board();

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

// Dice example
        int amountOfDice = input.nextInt();
        while(amountOfDice >2 || amountOfDice <1) {
            System.out.println("something went wrong or you enter less than 1 or more than 2 enter again :");
            amountOfDice = input.nextInt();
        }
        Dice amountDice = new Dice(amountOfDice);
        int Roll = amountDice.getTotal();
        System.out.println(Roll);
    }
}