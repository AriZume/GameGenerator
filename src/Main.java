package MainPackage;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        //Board myBoard = new Board();
        Board myBoard;
        Scanner input = new Scanner(System.in);
        System.out.println("\tMenu\n1. Design Game and Play\n2. Load Game\n3. Help\n4. Exit");
        int EXIT = 4;
        int userOption = input.nextInt();
        int playerAmount, diceAmount, tileAmount;

        while(userOption != EXIT) {
            switch (userOption) {
                case 1:
                    System.out.print("Give player amount : ");

                    do {
                        while (!input.hasNextInt()) {
                            System.out.println("Player amount should be 2 or more.");
                            input.next();
                        }

                        playerAmount = input.nextInt();
                        if (playerAmount < 2) {
                            System.out.println("Player amount should be 2 or more.");
                        }
                    } while (playerAmount < 2);


                    System.out.print("Give tile amount : ");
                    do {
                        while (!input.hasNextInt()) {
                            System.out.println("Tiles should be 1 or more.");
                            input.next();
                        }

                        tileAmount = input.nextInt();
                        if (tileAmount < 1) {
                            System.out.println("Tiles should be 1 or more.");
                        }
                    } while (tileAmount < 1);


                    System.out.print("Give dice amount : ");
                    do {
                        while (!input.hasNextInt()) {
                            System.out.println("Dice amount should be 1 or more.");
                            input.next();
                        }

                        diceAmount = input.nextInt();
                        if (diceAmount < 2) {
                            System.out.println("Dice amount should be 1 or more.");
                        }
                    } while (diceAmount < 1);

                    myBoard = new Board(diceAmount);
                    System.out.println("Enter names");
                    input.nextLine(); //Probably not correct but works
                    for (int i = 0; i < playerAmount; i++) {
                        System.out.print("Player" + (i + 1) + " : ");
                        String name = input.nextLine();

                        myBoard.addPlayer(new Player(name));
                    }

                    for (int i = 1; i <= tileAmount; i++) {
                        myBoard.addTileNumber(new Tile(i));
                    }

                    myBoard.showPlayer();
                    myBoard.showTileNumber();
                    myBoard.getDiceRoll();

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                default:
                    System.out.println("Option not valid!");
                    break;
            }
            System.out.println("\tMenu\n1. Start Game\n2. Design Game\n3. Load Game\n4. Help\n5. Exit");
            userOption = input.nextInt();
        }
    }
}
