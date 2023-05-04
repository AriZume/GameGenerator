import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        final int optDesignPlay = 1;
        final int optLoad = 2;
        final int optHelp = 3;
        final int optExit = 4;

        final int optSquareBoard = 1;
        final int optCircularBoard = 2;

        int playerAmount, tileAmount, diceAmount, enhancedTiles, boardType;
        int maxPoints; // Cards winning condition.
        int lapsToWins = 0; // Default winning condition for circular board.

        String enhanced, cards = "N";

        Screen screen = new Screen();
        Game loadGame =new Game();
        Game newGame;
        Scanner input = new Scanner(System.in);

        int userInput;
        Response mainMenu = screen.printMainMenu(optDesignPlay, optLoad, optHelp, optExit);
        System.out.println(mainMenu.getMessage());

        while(true)
        {
            try {
                userInput = input.nextInt();

                if(userInput == optExit)
                {
                    break;
                }

                switch (userInput)
                {
                    case optDesignPlay:

                        Response designGameTitle = screen.printDesignGameTitle();
                        System.out.print(designGameTitle.getMessage());

                        boardType = screen.getInputIntegerValidation(input, "Select board type:\n1. Square Board\n2. Circular Board\n(Type 1 or 2): ",
                                "Invalid input. Please try again.", 1 ,2);

                        playerAmount = screen.getInputIntegerValidation(input, "Number of players: ",
                                "Player amount should be more than 2.(Max 10)\nPlease try again: ", 2, 10);

                        tileAmount = screen.getInputIntegerValidation(input, "Number of tiles: ",
                                "Tile amount should be at least 10.(Max 150)\nPlease try again: ", 10, 150);

                        diceAmount = screen.getInputIntegerValidation(input, "Number of dice: ",
                                "Dice amount should be 1 or 2.\nPlease try again: ", 1, 2);

                        input.nextLine(); //Collects trash


                        enhanced = screen.getInputStringValidation(input, "Would you like enhanced tiles? (Y/N)\n(Note: Enhanced tile amount should be at least 2 less than the total tile amount): ",
                                "Invalid input. Please try again.", "[yYnN]");

                        if(boardType == optCircularBoard)
                        {
                            cards = screen.getInputStringValidation(input, "Would you like to have cards in your game? (Y/N)",
                                    "Invalid input. Please try again.", "[yYnN]");

                            // When the cards option is not active set default winning condition.
                            if(cards.matches("[Nn]"))
                            {
                                lapsToWins = screen.getInputIntegerValidation(input, "In how many laps would you like the game to end: ",
                                        "Laps should be more than 2.(Max 15)\nPlease try again: ", 2, 15);
                            }
                        }

                        if(enhanced.matches("[nN]") && cards.matches("[Nn]"))
                        {
                            // Create newGame without enhanced tiles.
                            newGame = new Game(playerAmount, tileAmount, diceAmount);
                        }
                        else if (enhanced.matches("[Yy]") && cards.matches("[Nn]"))
                        {
                            // Create newGame with only enhanced tiles.
                            enhancedTiles = screen.getInputIntegerValidation(input, "Enter the amount of enhanced tiles you would like the board to have: ",
                                                        "Enhanced tile amount must be at least 2 less than the total tile amount.\nPlease try again: ", 1, tileAmount-2 );
                            newGame = new Game(playerAmount, tileAmount, diceAmount, enhancedTiles);
                        }
                        else if (enhanced.matches("[Nn]") && cards.matches("[Yy]"))
                        {
                            maxPoints =   screen.getInputIntegerValidation(input, "Number of points required to win the game: ",
                                    "Point amount should be at least 500. (Min 500)(Max 10000)\nPlease try again: ", 500, 10000);

                            // Creates newGame with only cards.
                            newGame = new Game(playerAmount, tileAmount, diceAmount, Integer.toString(maxPoints));
                        }
                        else
                        {
                            enhancedTiles = screen.getInputIntegerValidation(input, "Enter the amount of enhanced tiles you would like the board to have: ",
                                    "Enhanced tile amount must be at least 2 less than the total tile amount.\nPlease try again: ", 1, tileAmount-2 );
                            maxPoints =   screen.getInputIntegerValidation(input, "Number of points required to win the newGame: ",
                                    "Point amount should be at least 500. (Min 500)(Max 10000)\nPlease try again: ", 500, 10000);

                            // Creates newGame with both enhanced tiles and cards.
                            newGame = new Game(playerAmount, tileAmount, diceAmount, enhancedTiles, Integer.toString(maxPoints));
                        }

                        // Debugging
                        //System.out.println();
                        //newGame.printTilesPower();
                        // ---

                        if(boardType == optSquareBoard)
                        {
                            newGame.setBoardType("Square");
                        }
                        else if(boardType == optCircularBoard)
                        {
                            newGame.setBoardType("Circle");
                            newGame.setLapsToWin(lapsToWins);
                        }

                        newGame.startGame();
                        break;

                    case optLoad:
                        LoadGame load = new LoadGame(loadGame);
                        load.loadProgress();
                        loadGame.startGame();
                        break;

                    case optHelp:

                        System.out.println("Can't give you help :(\nPlease donate $19.99 to unlock GameGenerator Premium.");
                        break;

                    default:
                        System.out.print(screen.printInvalidOption().getMessage());
                        break;
                }
            }catch(InputMismatchException e)
            {
                System.out.print(screen.printInvalidOption().getMessage());
                input.nextLine();
            }

            System.out.println(mainMenu.getMessage());
        }
    }
}