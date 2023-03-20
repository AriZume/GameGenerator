package MainPackage;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        ArrayList<Player> currentPlayers = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        int count =1;
        while(true)
        {
            System.out.println("Player" + count + ": ");
            String name = input.nextLine();
            count++;
            if(name.isEmpty())
            {
                break;
            }
            currentPlayers.add(new Player(name));
        }

        System.out.println("Current Players: ");
        for(Player currentPlayer : currentPlayers)
        {
            String name = currentPlayer.getName();
            int playerPos = currentPlayer.getCurrentPosition();
            System.out.println(name);
            System.out.println(playerPos);
        }

        int amountOfDice = input.nextInt();
        while(amountOfDice >2 || amountOfDice <1) {
            System.out.println("something went wrong or you enter less than 1 or more than 2 enter agein :");
            amountOfDice = input.nextInt();
        }
        Dice amountDice = new Dice(amountOfDice);
        int Roll = amountDice.getTotal();
        System.out.println(Roll);
    }
}