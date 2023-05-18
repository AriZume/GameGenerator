package Main;

import Game.Game;
import Game.QuickGame;
import IO.GameLoader;
import UserInterface.*;

// TODO: EXCEPTIONS AND TRY-CATCH BLOCKS
// TODO: PACKAGE DIVISION
// TODO: SAVE QUICK GAME IN DIFFERENT FILE
// TODO: CHOOSE TO LOAD QUICK  GAME OR NORMAL GAME

public class Main
{
    public static void main(String[] args)
    {
        int userInput;
        GameSetupHelper gameSetupHelper = new GameSetupHelper();
        UserInputScreen userInputScreen = new UserInputScreen();
        GameLoader loader = new GameLoader();
        UIResponse uiResponse = new UIResponse();

        Response mainMenu = uiResponse.createMainMenuResponse(EnumClass.MainMenuOption.DESIGN_PLAY.getValue(), EnumClass.MainMenuOption.QUICK_GAME.getValue(),
                EnumClass.MainMenuOption.LOAD.getValue(), EnumClass.MainMenuOption.HELP.getValue(), EnumClass.MainMenuOption.EXIT.getValue());
        System.out.print(mainMenu.message());
        while(true)
        {
            userInput = userInputScreen.checkUserInput(EnumClass.InputRestriction.MAIN_MENU.getMin(), EnumClass.InputRestriction.MAIN_MENU.getMax());

            if(userInput == EnumClass.MainMenuOption.EXIT.getValue())
            {
                break;
            }
            EnumClass.MainMenuOption userOption = EnumClass.MainMenuOption.values()[userInput - 1];
            switch (userOption)
            {
                case DESIGN_PLAY:
                    Game newGame = gameSetupHelper.createNewGame(uiResponse, userInputScreen);
                    newGame.startGame();
                    break;
                case QUICK_GAME:
                    QuickGame quickGame = gameSetupHelper.createQuickGame(uiResponse, userInputScreen);
                    quickGame.startGame();
                    break;
                case LOAD:
                    Game loadedGame = gameSetupHelper.loadGame(loader);
                    loadedGame.startGame();
                    break;
                case HELP:
                    gameSetupHelper.showHelp(uiResponse);
                    break;
                default:
                    System.out.print(uiResponse.createInvalidOptionResponse().message());
                    break;
            }

            System.out.print(mainMenu.message());
        }
    }
}
