package MainPackage;

import GamePackage.Game;
import GamePackage.Response;
import IOPackage.GameLoader;

import java.util.*;

public class Main
{
    enum Options
    {
        optDesignPlay(1),
        optLoad(2),
        optHelp(3),
        optExit(4),
        optSquareBoard(1),
        optCircularBoard(2);
        public final int option;
        Options(int option)
        {
            this.option = option;
        }
        public int getOption()
        {
            return option;
        }
    }

    public static void main(String[] args)
    {
        int playerAmount, tileAmount, diceAmount, enhancedTiles, boardType;
        int maxPoints; // Cards winning condition.
        int lapsToWins = 0; // Default winning condition for circular board.
        //boolean isGameLoaded = false;

        String enhanced, cards = "N";

        GameLoader loader = new GameLoader();
        Screen screen = new Screen();
        Game newGame;
        Scanner input = new Scanner(System.in);


        int userInput;
        Response mainMenu = screen.printMainMenu(Options.optDesignPlay.getOption(), Options.optLoad.getOption(),
                Options.optHelp.getOption(), Options.optExit.getOption());
        System.out.println(mainMenu.getMessage());

        while(true)
        {
            try {
                userInput = input.nextInt();
                if(userInput == Options.optExit.getOption())
                {
                    break;
                }

                Options userOption = Options.values()[userInput-1];
                switch(userOption)
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

                        if(boardType == Options.optCircularBoard.getOption())
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
                            maxPoints =   screen.getInputIntegerValidation(input, "Number of points required to win the game: ",
                                    "Point amount should be at least 500. (Min 500)(Max 10000)\nPlease try again: ", 500, 10000);

                            // Creates newGame with both enhanced tiles and cards.
                            newGame = new Game(playerAmount, tileAmount, diceAmount, enhancedTiles, Integer.toString(maxPoints));
                        }

                        // Debugging
                        //System.out.println();
                        //newGame.printTilesPower();
                        // ---

                        if(boardType == Options.optSquareBoard.getOption())
                        {
                            newGame.getBoard().setBoardType("Square");
                        }
                        else if(boardType == Options.optCircularBoard.getOption())
                        {
                            newGame.getBoard().setBoardType("Circle");
                            newGame.getBoard().setLapsToWin(lapsToWins);
                        }

                        newGame.startGame();
                        break;

                    case optLoad:
                        Game loadGame =  loader.loadProgress();
                        loadGame.startGame();
                        break;

                    case optHelp:
                        Response help = screen.printHelp();
                        System.out.println(help.getMessage());
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