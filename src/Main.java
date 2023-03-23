package MainPackage;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Board myBoard;
        Scanner input = new Scanner(System.in);
        System.out.println("\t\t  MENU\n========================\n1. Design Game and Play\n2. Load Game\n3. Help\n4. Exit");
        int EXIT = 4;
        int userOption;
        int playerAmount, diceAmount, tileAmount;


        do
        {
            while (!input.hasNextInt())
            {
                System.out.print("Please select one of the following options: ");
                input.next();
                System.out.println();
            }
            userOption = input.nextInt();

            if (userOption == 2)
            {
                System.out.print("Loading is not available. Try a different option: ");
                userOption = -1; // Probably wrong way to do it, but works
            }
            else if(userOption > 4 || userOption <= 0)
            {
                System.out.print("\nInvalid option.\nPlease select one of the following options: ");
            }
        } while (userOption > 4 || userOption <= 0);


        while(userOption != EXIT)
        {
            // MAIN MENU
            switch (userOption)
            {
                case 1:
                    System.out.println("\tGAME DESIGN\n========================");
                    System.out.print("Give player amount : ");

                    do
                    {
                        while (!input.hasNextInt())
                        {
                            System.out.println("Player amount should be 2 or more.");
                            input.next();
                        }

                        playerAmount = input.nextInt();
                        if (playerAmount < 2)
                        {
                            System.out.println("Player amount should be 2 or more.");
                        }
                    } while (playerAmount < 2);

                    System.out.print("Give tile amount : ");

                    do
                    {
                        while (!input.hasNextInt())
                        {
                            System.out.println("Tiles should be 1 or more.");
                            input.next();
                        }

                        tileAmount = input.nextInt();
                        if (tileAmount < 1)
                        {
                            System.out.println("Tiles should be 1 or more.");
                        }
                    } while (tileAmount < 1);

                    System.out.print("Give dice amount : ");

                    do
                    {
                        while (!input.hasNextInt())
                        {
                            System.out.println("Dice amount should be 1 or more.");
                            input.next();
                        }

                        diceAmount = input.nextInt();
                        if (diceAmount < 2)
                        {
                            System.out.println("Dice amount should be 1 or more.");
                        }
                    } while (diceAmount < 1);

                    myBoard = new Board(diceAmount);
                    System.out.println("Enter names");
                    input.nextLine(); //Probably not correct but works

                    for (int i = 0; i < playerAmount; i++)
                    {
                        System.out.print("Player" + (i + 1) + " : ");
                        String name = input.nextLine();

                        myBoard.addPlayer(new Player(name));
                    }

                    for (int i = 1; i <= tileAmount; i++)
                    {
                        myBoard.addTileNumber(new Tile(i));
                    }

                    myBoard.startGame();
                    break;
                case 2:
                    System.out.println("Load is currently unavailable.");
                    break;
                case 3:
                    System.out.println("Can't give you help :(\nPlease donate $19.99 to unlock GameGenerator Premium.");
                    break;
                default:
                    System.out.println("Option not valid!");
                    break;
            }

            System.out.println("\t\t  MENU\n========================\n1. Design Game and Play\n2. Load Game\n3. Help\n4. Exit");



            do
            {
                while (!input.hasNextInt())
                {
                    System.out.print("Please select one of the other options: ");
                    input.next();
                }
                userOption = input.nextInt();

                if (userOption == 2)
                {
                    System.out.print("Loading is not available. Try a different option: ");
                    userOption = -1; // Probably wrong way to do it, but works
                }
                else if(userOption > 4 || userOption <= 0)
                {
                    System.out.print("\nInvalid option.\nPlease select one of the other options: ");
                }
            } while (userOption > 4 || userOption <= 0);
        }
    }
}
