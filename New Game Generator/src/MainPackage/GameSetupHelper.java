package MainPackage;

import GamePackage.Game;
import GamePackage.Response;
import IOPackage.GameLoader;
import UIPackage.UIResponse;
import UIPackage.UserInputScreen;

public class GameSetupHelper
{
    public void createNewGame(UIResponse uiResponse, UserInputScreen userInputScreen)
    {
        Game newGame;

        int playerAmount, tileAmount, diceAmount, enhancedTiles = 0, boardType;
        int maxPoints = 0; // Cards winning condition.
        int lapsToWins = 0; // Default winning condition for circular board.
        String hasEnhanced, hasCards = "N";

        Response designGameTitle = uiResponse.createDesignGameTitleResponse();
        System.out.print(designGameTitle.getMessage());

        boardType = userInputScreen.declareBoardType(EnumClass.InputRestriction.BOARD_TYPE.getMin(), EnumClass.InputRestriction.BOARD_TYPE.getMax());
        playerAmount = userInputScreen.declarePlayerAmount(EnumClass.InputRestriction.PLAYER_AMOUNT.getMin(), EnumClass.InputRestriction.PLAYER_AMOUNT.getMax());
        tileAmount = userInputScreen.declareTileAmount(EnumClass.InputRestriction.TILE_AMOUNT.getMin(), EnumClass.InputRestriction.TILE_AMOUNT.getMax());
        diceAmount = userInputScreen.declareDiceAmount(EnumClass.InputRestriction.DICE_AMOUNT.getMin(), EnumClass.InputRestriction.DICE_AMOUNT.getMax());

        hasEnhanced = userInputScreen.declareEnhancedTiles("[yYnN]");
        if(hasEnhanced.matches("[Yy]"))
        {
            enhancedTiles = userInputScreen.declareEnhancedTileAmount(EnumClass.InputRestriction.ENHANCED_TILE_AMOUNT.getMin(), tileAmount - EnumClass.InputRestriction.ENHANCED_TILE_AMOUNT.getMax());
        }

        if(boardType == EnumClass.BoardType.CIRCULAR_BOARD.getValue())
        {
            hasCards = userInputScreen.declareCards("[yYnN]");
            if(hasCards.matches("[Yy]"))
            {
                maxPoints = userInputScreen.declareMaxPoints(EnumClass.InputRestriction.MAX_POINTS.getMin(), EnumClass.InputRestriction.MAX_POINTS.getMax());
            }
            else
            {
                lapsToWins = userInputScreen.declareLapsToWin(EnumClass.InputRestriction.LAPS_TO_WIN.getMin(), EnumClass.InputRestriction.LAPS_TO_WIN.getMax());
            }
        }
        newGame = new Game(playerAmount, tileAmount, diceAmount, enhancedTiles, maxPoints);

        EnumClass.BoardType boardTypeVerify = EnumClass.BoardType.values()[boardType-1];
        newGame.getBoard().setBoardType(boardTypeVerify.getDescription());

        newGame.getBoard().setLapsToWin(lapsToWins);
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
