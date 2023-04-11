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

        int playerAmount, tileAmount, diceAmount, enhancedTiles, maxPoints;
        String enhanced,cards;

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
                        playerAmount = screen.getInputIntegerValidation(input, "Number of player: ",
                                "Player amount should be more than 2.\nPlease try again: ", 2, 100);

                        tileAmount = screen.getInputIntegerValidation(input, "Number of tiles: ",
                                "Tile amount should be at least 10.(Max 150)\nPlease try again: ", 10, 150);

                        diceAmount = screen.getInputIntegerValidation(input, "Number of dice: ",
                                "Dice amount should be 1 or 2.\nPlease try again: ", 1, 2);

                        input.nextLine();
                        enhanced = screen.getInputStringValidation(input, "Would you like enhanced tiles? (Y/N)\n(Note: Enhanced tile amount should be at least 2 less than the total tile amount): ",
                                "Invalid input. Please try again.", "[yYnN]");

                        cards = screen.getInputStringValidation(input, "Would you like to have cards in your game? (Y/N)",
                                "Invalid input. Please try again.", "[yYnN]");

                        if(enhanced.matches("[nN]") && cards.matches("[Nn]"))
                        {
                            // Create game without enhanced tiles.
                            game = new Game(playerAmount, tileAmount, diceAmount);
                        }
                        else if (enhanced.matches("[Yy]") && cards.matches("[Nn]"))
                        {
                            // Create game with enhanced tiles.
                            enhancedTiles = screen.getInputIntegerValidation(input, "Enter the amount of enhanced tiles you would like the board to have: ",
                                                        "Enhanced tile amount must be at least 2 less than the total tile amount.\nPlease try again: ", 1, tileAmount-2 );
                            game = new Game(playerAmount, tileAmount, diceAmount, enhancedTiles);
                        }
                        else if (enhanced.matches("[Nn]") && cards.matches("[Yy]"))
                        {
                            maxPoints =   screen.getInputIntegerValidation(input, "Number of points required to win the game: ",
                                    "Dice amount should be at least 1000. (Max 10000)\nPlease try again: ", 1000, 10000);

                            game = new Game(playerAmount, tileAmount, diceAmount, Integer.toString(maxPoints));
                        }
                        else
                        {
                            enhancedTiles = screen.getInputIntegerValidation(input, "Enter the amount of enhanced tiles you would like the board to have: ",
                                    "Enhanced tile amount must be at least 2 less than the total tile amount.\nPlease try again: ", 1, tileAmount-2 );

                            maxPoints =   screen.getInputIntegerValidation(input, "Number of points required to win the game: ",
                                    "Dice amount should be at least 1000. (Max 10000)\nPlease try again: ", 1000, 10000);

                            game = new Game(playerAmount, tileAmount, diceAmount, enhancedTiles, Integer.toString(maxPoints));
                        }

                        System.out.println();

                        game.printTilesPower();
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
                System.out.print("Invalid Option. Please try again: ");
                input.nextLine();
            }

            screen.printMainMenu(optDesignPlay, optLoad, optHelp, optExit);
        }// End while
    }
}