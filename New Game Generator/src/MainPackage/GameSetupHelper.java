package MainPackage;

import GamePackage.Game;
import GamePackage.Response;
import IOPackage.GameLoader;
import UIPackage.UIResponse;
import UIPackage.UIScreen;

public class GameSetupHelper
{
    enum BoardType
    {
        SQUARE_BOARD(1,"Square"), CIRCULAR_BOARD(2,"Circular");

        public final int value;
        public final String description;
        BoardType(int value, String description)
        {
            this.value = value;
            this.description = description;
        }
        public int getValue() {
            return this.value;
        }
        public String getDescription() {
            return this.description;
        }
    }
    enum InputRestriction
    {
        BOARD_TYPE_MIN(1), BOARD_TYPE_MAX(2),
        PLAYER_AMOUNT_MIN(2), PLAYER_AMOUNT_MAX(10),
        TILE_AMOUNT_MIN(10), TILE_AMOUNT_MAX(150),
        DICE_AMOUNT_MIN(1), DICE_AMOUNT_MAX(2),
        LAPS_TO_WIN_MIN(2), LAPS_TO_WIN_MAX(15),
        MAX_POINTS_MIN(500), MAX_POINTS_MAX(10000),
        ENHANCED_TILE_AMOUNT_MIN(1), ENHANCED_TILE_AMOUNT_MAX(2);

        public final int value;
        InputRestriction(int value)
        {
            this.value = value;
        }
        private int getValue()
        {
            return this.value;
        }
    }
    public void createNewGame(UIResponse uiResponse, UIScreen uiScreen)
    {
        Game newGame;

        int playerAmount, tileAmount, diceAmount, enhancedTiles, boardType;
        int maxPoints; // Cards winning condition.
        int lapsToWins = 0; // Default winning condition for circular board.
        String hasEnhanced, hasCards = "N";

        Response designGameTitle = uiResponse.createDesignGameTitleResponse();
        System.out.print(designGameTitle.getMessage());

        boardType = uiScreen.declareBoardType(InputRestriction.BOARD_TYPE_MIN.getValue(), InputRestriction.BOARD_TYPE_MAX.getValue());
        playerAmount = uiScreen.declarePlayerAmount(InputRestriction.PLAYER_AMOUNT_MIN.getValue(), InputRestriction.PLAYER_AMOUNT_MAX.getValue());
        tileAmount = uiScreen.declareTileAmount(InputRestriction.TILE_AMOUNT_MIN.getValue(), InputRestriction.TILE_AMOUNT_MAX.getValue());
        diceAmount = uiScreen.declareDiceAmount(InputRestriction.DICE_AMOUNT_MIN.getValue(), InputRestriction.DICE_AMOUNT_MAX.ordinal());
        hasEnhanced = uiScreen.declareEnhancedTiles("[yYnN]");

        if(boardType == BoardType.CIRCULAR_BOARD.getValue())
        {
            hasCards = uiScreen.declareCards("[yYnN]");

            // When the cards option is not active set default winning condition.
            if(hasCards.matches("[Nn]"))
            {
                lapsToWins = uiScreen.declareLapsToWin(InputRestriction.LAPS_TO_WIN_MIN.getValue(), InputRestriction.LAPS_TO_WIN_MAX.getValue());
            }
        }

        if (hasEnhanced.matches("[nN]") && hasCards.matches("[Nn]"))
        {
            // Create newGame without enhanced tiles.
            newGame = new Game(playerAmount, tileAmount, diceAmount);
        }
        else if (hasEnhanced.matches("[Yy]") && hasCards.matches("[Nn]"))
        {
            // Create newGame with only enhanced tiles.
            enhancedTiles = uiScreen.declareEnhancedTileAmount(1, tileAmount - InputRestriction.ENHANCED_TILE_AMOUNT_MAX.getValue());
            newGame = new Game(playerAmount, tileAmount, diceAmount, enhancedTiles);
        }
        else if (hasEnhanced.matches("[Nn]") && hasCards.matches("[Yy]"))
        {
            maxPoints = uiScreen.declareMaxPoints(InputRestriction.MAX_POINTS_MIN.getValue(), InputRestriction.MAX_POINTS_MAX.getValue());

            // Creates newGame with only cards.
            newGame = new Game(playerAmount, tileAmount, diceAmount, Integer.toString(maxPoints));
        }
        else
        {
            enhancedTiles = uiScreen.declareEnhancedTileAmount(InputRestriction.ENHANCED_TILE_AMOUNT_MIN.getValue(), tileAmount - InputRestriction.ENHANCED_TILE_AMOUNT_MAX.getValue());
            maxPoints = uiScreen.declareMaxPoints(InputRestriction.MAX_POINTS_MIN.getValue(), InputRestriction.MAX_POINTS_MAX.getValue());

            // Creates newGame with both enhanced tiles and cards.
            newGame = new Game(playerAmount, tileAmount, diceAmount, enhancedTiles, Integer.toString(maxPoints));
        }

        // Debugging
        //System.out.println();
        //newGame.printTilesPower();
        // ---

        if(boardType == BoardType.SQUARE_BOARD.getValue())
        {
            newGame.getBoard().setBoardType(BoardType.SQUARE_BOARD.getDescription());
        }
        else if(boardType == BoardType.CIRCULAR_BOARD.getValue())
        {
            newGame.getBoard().setBoardType(BoardType.CIRCULAR_BOARD.getDescription());
            newGame.getBoard().setLapsToWin(lapsToWins);
        }

        newGame.startGame();
    }

    public void loadGame(GameLoader loader)
    {
        Response fileNotFoundResponse = new Response("");
        try
        {
            Game loadGame = loader.loadProgress();
            loadGame.startGame();
        } catch (Exception e)
        {
            fileNotFoundResponse = new Response("\nFile not found. Please create a new game.");
        }
        System.out.print(fileNotFoundResponse.getMessage());
    }

    public void showHelp(UIResponse uiResponse)
    {
        Response help = uiResponse.createHelpResponse();
        System.out.print(help.getMessage());
    }
}
