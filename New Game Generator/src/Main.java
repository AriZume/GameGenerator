import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        final int optDesignPlay = 1;
        final int optLoad = 2;
        final int optHelp = 3;
        final int optExit = 4;

        int playerAmount;
        int tileAmount;
        int diceAmount;

        Screen screen = new Screen();
        Game game;

        Scanner input = new Scanner(System.in);
        int userInput;

        screen.printMainMenu(optDesignPlay, optLoad, optHelp, optExit);

        while(true)
        {
            try {
                userInput = input.nextInt();

                if(userInput == optExit)
                {
                    break;
                }

                switch (userInput) {
                    case optDesignPlay:
                        screen.printDesignGameTitle();

                        System.out.print("Number of player: ");
                        playerAmount = input.nextInt();

                        System.out.print("Number of tiles: ");
                        tileAmount = input.nextInt();

                        System.out.print("Number of dice: ");
                        diceAmount = input.nextInt();

                        game = new Game(playerAmount, tileAmount, diceAmount);
                        game.startGame();
                        break;
                    case optLoad:

                        break;
                    case optHelp:

                        break;
                    default:
                        System.out.print("Invalid option. Please try again.");
                        break;
                }
            }catch(InputMismatchException e)
            {
                System.out.println("Invalid Option. Please try again.");
                input.nextLine();
            }

            screen.printMainMenu(optDesignPlay, optLoad, optHelp, optExit);
        }
    }
}