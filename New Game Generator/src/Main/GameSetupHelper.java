package Main;

import Game.Game;
import Game.QuickGame;
import UserInterface.Response;
import IO.GameLoader;
import UserInterface.UIResponse;
import UserInterface.UserInputScreen;

public class GameSetupHelper
{
    public Game createNewGame(UIResponse uiResponse, UserInputScreen userInputScreen)
    {
        int playerAmount, tileAmount, diceAmount, enhancedTiles = 0, boardType;
        int maxPoints = 0;
        int lapsToWins = 0;
        String hasEnhanced, hasCards;

        Response designGameTitle = uiResponse.createDesignGameTitleResponse();
        System.out.print(designGameTitle.message());

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
        return new Game(playerAmount, tileAmount, diceAmount, enhancedTiles, maxPoints, EnumClass.BoardType.values()[boardType-1].getDescription(), lapsToWins);
    }

    public QuickGame createQuickGame(UIResponse uiResponse, UserInputScreen userInputScreen)
    {
        int playerAmount, boardType;
        boardType = userInputScreen.declareBoardType(EnumClass.InputRestriction.BOARD_TYPE.getMin(), EnumClass.InputRestriction.BOARD_TYPE.getMax());
        playerAmount = userInputScreen.declarePlayerAmount(EnumClass.InputRestriction.PLAYER_AMOUNT.getMin(), EnumClass.InputRestriction.PLAYER_AMOUNT.getMax());
        return new QuickGame(playerAmount, boardType);
    }

    public Game loadGame(GameLoader loader)
    {
        Response fileNotFoundResponse;
        try
        {
            return loader.loadProgress();
        } catch (Exception e)
        {
            fileNotFoundResponse = new Response("\nFile not found. Please create a new game.");
        }
        System.out.print(fileNotFoundResponse.message());
        return null;
    }

    public void showHelp(UIResponse uiResponse)
    {
        Response help = uiResponse.createHelpResponse();
        System.out.print(help.message());
    }
}
