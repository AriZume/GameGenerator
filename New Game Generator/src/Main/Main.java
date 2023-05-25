package Main;

import Game.Game;
import IO.GameLoader;
import UserInterface.*;

// TODO: EXCEPTIONS AND TRY-CATCH BLOCKS
// TODO: PACKAGE DIVISION
// TODO: SAVE QUICK GAME IN DIFFERENT FILE
// TODO: CHOOSE TO LOAD QUICK GAME OR NORMAL GAME

public class Main
{
    public static void main(String[] args)
    {
        int userInput;
        GameSetupHelper gameSetupHelper = new GameSetupHelper();
        UserInputScreen userInputScreen = new UserInputScreen();
        GameLoader loader = new GameLoader();
        UIResponse uiResponse = new UIResponse();

        Response mainMenu = uiResponse.createMainMenuResponse(MainMenuOption.NEW_GAME.getValue(), MainMenuOption.QUICK_GAME.getValue(),
                MainMenuOption.LOAD.getValue(), MainMenuOption.HELP.getValue(), MainMenuOption.EXIT.getValue());
        System.out.print(mainMenu.message());
        while(true)
        {
            userInput = userInputScreen.checkUserInput(InputRestriction.MAIN_MENU.getMin(), InputRestriction.MAIN_MENU.getMax());

            if(userInput == MainMenuOption.EXIT.getValue())
            {
                break;
            }
            MainMenuOption userOption = MainMenuOption.values()[userInput - 1];
            switch (userOption)
            {
                case NEW_GAME:
                    Game newGame = gameSetupHelper.createNewGame(uiResponse, userInputScreen);
                    newGame.startGame();
                    break;
                case QUICK_GAME:
                    Game quickGame = gameSetupHelper.createQuickGame(userInputScreen);
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
