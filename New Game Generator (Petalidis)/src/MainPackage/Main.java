package MainPackage;

import GamePackage.Game;
import GamePackage.Response;
import IOPackage.GameLoader;
import UIPackage.Screen;
import UIPackage.UIScreen;
import java.util.*;

public class Main
{
    enum Options
    {
        DESIGN_PLAY(1),
        LOAD(2),
        HELP(3),
        EXIT(4),
        SQUARE_BOARD(1),
        CIRCULAR_BOARD(2);
        public final int option;

        Options(int option) {
            this.option = option;
        }

        public int getOption() {
            return option;
        }
    }

    enum StringEnum
    {
        SQUARE_BOARD("Square"),
        CIRCULAR_BOARD("Circular");
        public final String option;

        StringEnum(String option) {
            this.option = option;
        }

        public String getOption() {
            return option;
        }
    }

    // TODO: Ask about the removal of if-statements, and the game constructors to include the [yYnN].

    public static void main(String[] args)
    {
        int playerAmount, tileAmount, diceAmount, enhancedTiles, boardType;
        int maxPoints; // Cards winning condition.
        int lapsToWins = 0; // Default winning condition for circular board.
        //boolean isGameLoaded = false;

        String enhanced, cards = "N";

        UIScreen uiScreen = new UIScreen();
        GameLoader loader = new GameLoader();
        Screen screen = new Screen();
        Game newGame;
        //Scanner input = new Scanner(System.in);

        int userInput;
        Response mainMenu = screen.printMainMenu(Options.DESIGN_PLAY.getOption(), Options.LOAD.getOption(),
                Options.HELP.getOption(), Options.EXIT.getOption());
        System.out.println(mainMenu.getMessage());

        while(true)
        {
            userInput = uiScreen.checkUserInput(1, 4);

            if(userInput == Options.EXIT.getOption())
            {
                break;
            }

            Options userOption = Options.values()[userInput - 1];
            switch (userOption)
            {
                case DESIGN_PLAY:

                    Response designGameTitle = screen.printDesignGameTitle();
                    System.out.print(designGameTitle.getMessage());

                    boardType = uiScreen.declareBoardType(1, 2);

                    playerAmount = uiScreen.declarePlayerAmount(2, 10);

                    tileAmount = uiScreen.declareTileAmount(10, 150);

                    diceAmount = uiScreen.declareDiceAmount(1, 2);

                    enhanced = uiScreen.declareEnhancedTiles("[yYnN]");

                    if (boardType == Options.CIRCULAR_BOARD.getOption())
                    {
                        cards = uiScreen.declareCards("[yYnN]");

                        // When the cards option is not active set default winning condition.
                        if (cards.matches("[Nn]"))
                        {
                            lapsToWins = uiScreen.declareLapsToWin(2, 15);
                        }
                    }

                    if (enhanced.matches("[nN]") && cards.matches("[Nn]"))
                    {
                        // Create newGame without enhanced tiles.
                        newGame = new Game(playerAmount, tileAmount, diceAmount);
                    }
                    else if (enhanced.matches("[Yy]") && cards.matches("[Nn]"))
                    {
                        // Create newGame with only enhanced tiles.
                        enhancedTiles = uiScreen.declareEnhancedTileAmount(2, tileAmount - 2);
                        newGame = new Game(playerAmount, tileAmount, diceAmount, enhancedTiles);
                    }
                    else if (enhanced.matches("[Nn]") && cards.matches("[Yy]"))
                    {
                        maxPoints = uiScreen.declareMaxPoints(500, 10000);

                        // Creates newGame with only cards.
                        newGame = new Game(playerAmount, tileAmount, diceAmount, Integer.toString(maxPoints));
                    }
                    else
                    {
                        enhancedTiles = uiScreen.declareEnhancedTileAmount(2, tileAmount - 2);
                        maxPoints = uiScreen.declareMaxPoints(500, 10000);

                        // Creates newGame with both enhanced tiles and cards.
                        newGame = new Game(playerAmount, tileAmount, diceAmount, enhancedTiles, Integer.toString(maxPoints));
                    }

                    // Debugging
                    //System.out.println();
                    //newGame.printTilesPower();
                    // ---

                    if(boardType == Options.SQUARE_BOARD.getOption())
                    {
                        newGame.getBoard().setBoardType(StringEnum.SQUARE_BOARD.getOption());
                    }
                    else if(boardType == Options.CIRCULAR_BOARD.getOption())
                    {
                        newGame.getBoard().setBoardType(StringEnum.CIRCULAR_BOARD.getOption());
                        newGame.getBoard().setLapsToWin(lapsToWins);
                    }

                    newGame.startGame();
                    break;

                case LOAD:
                    try
                    {
                        Game loadGame = loader.loadProgress();
                        loadGame.startGame();
                    } catch (Exception e)
                    {
                        System.out.println("File not found. Please create a new game.");
                    }
                    break;

                case HELP:
                    Response help = screen.printHelp();
                    System.out.println(help.getMessage());
                    break;

                default:
                    System.out.print(screen.printInvalidOption().getMessage());
                    break;
            }

            System.out.println(mainMenu.getMessage());
        }
    }
}
