package Main;

import Game.Game;
import Game.BoardType;
import Game.GameBuilder;
import Game.Director;
import UserInterface.Response;
import IO.GameLoader;
import UserInterface.UIResponse;
import UserInterface.UserInputScreen;

public class GameSetupHelper
{
    public Game createNewGame(UIResponse uiResponse, UserInputScreen userInputScreen)
    {
        Director director = new Director();
        GameBuilder builder = new GameBuilder();
        int playerAmount, tileAmount, diceAmount, enhancedTiles = 0, boardType;
        int maxPoints = 0;
        int lapsToWin = 0;
        String hasEnhanced, hasCards;

        Response designGameTitle = uiResponse.createDesignGameTitleResponse();
        System.out.print(designGameTitle.message());

        boardType = userInputScreen.declareBoardType(InputRestriction.BOARD_TYPE.getMin(), InputRestriction.BOARD_TYPE.getMax());
        playerAmount = userInputScreen.declarePlayerAmount(InputRestriction.PLAYER_AMOUNT.getMin(), InputRestriction.PLAYER_AMOUNT.getMax());
        tileAmount = userInputScreen.declareTileAmount(InputRestriction.TILE_AMOUNT.getMin(), InputRestriction.TILE_AMOUNT.getMax());
        diceAmount = userInputScreen.declareDiceAmount(InputRestriction.DICE_AMOUNT.getMin(), InputRestriction.DICE_AMOUNT.getMax());

        hasEnhanced = userInputScreen.declareEnhancedTiles("[yYnN]");
        if(hasEnhanced.matches("[Yy]"))
        {
            enhancedTiles = userInputScreen.declareEnhancedTileAmount(InputRestriction.ENHANCED_TILE_AMOUNT.getMin(), tileAmount - InputRestriction.ENHANCED_TILE_AMOUNT.getMax());
        }

        if(boardType == BoardType.CIRCULAR_BOARD.getValue())
        {
            hasCards = userInputScreen.declareCards("[yYnN]");
            if(hasCards.matches("[Yy]"))
            {
                maxPoints = userInputScreen.declareMaxPoints(InputRestriction.MAX_POINTS.getMin(), InputRestriction.MAX_POINTS.getMax());
            }
            else
            {
                lapsToWin = userInputScreen.declareLapsToWin(InputRestriction.LAPS_TO_WIN.getMin(), InputRestriction.LAPS_TO_WIN.getMax());
            }
        }

        director.buildNewGame(builder, BoardType.values()[boardType-1].getDescription(), playerAmount,
                              tileAmount, diceAmount, enhancedTiles, maxPoints, lapsToWin);
        return builder.build();
    }

    public Game createQuickGame(UserInputScreen userInputScreen)
    {
        Director director = new Director();
        GameBuilder builder = new GameBuilder();
        int playerAmount, boardType;

        boardType = userInputScreen.declareBoardType(InputRestriction.BOARD_TYPE.getMin(), InputRestriction.BOARD_TYPE.getMax());
        playerAmount = userInputScreen.declarePlayerAmount(InputRestriction.PLAYER_AMOUNT.getMin(), InputRestriction.PLAYER_AMOUNT.getMax());

        director.buildQuickGame(builder, BoardType.values()[boardType-1].getDescription(), playerAmount);
        return builder.build();
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
