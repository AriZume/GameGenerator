package Main;

import Game.Game;
import UserInterface.Response;
import IO.GameLoader;
import UserInterface.UIResponse;
import UserInterface.UserInputScreen;

// TODO: ASK ABOUT EXCEPTIONS AND TRY-CATCH BLOCKS
// TODO: ASK ABOUT PACKAGE DIVISION
// TODO: ASK ABOUT THE DICE ROLL METHOD TO USE A LIST FOR THE INDIVIDUAL DIE TO REFACTOR FROM VOID TO RESPONSE
public class Main
{
    public static void main(String[] args)
    {
        int userInput;
        GameSetupHelper gameSetupHelper = new GameSetupHelper();
        UserInputScreen userInputScreen = new UserInputScreen();
        GameLoader loader = new GameLoader();
        UIResponse uiResponse = new UIResponse();

        Response mainMenu = uiResponse.createMainMenuResponse(EnumClass.MainMenuOption.DESIGN_PLAY.getValue(), EnumClass.MainMenuOption.LOAD.getValue(),
                EnumClass.MainMenuOption.HELP.getValue(), EnumClass.MainMenuOption.EXIT.getValue());
        System.out.print(mainMenu.getMessage());

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
                case LOAD:
                    Game loadedGame = gameSetupHelper.loadGame(loader);
                    loadedGame.startGame();
                    break;
                case HELP:
                    gameSetupHelper.showHelp(uiResponse);
                    break;
                default:
                    System.out.print(uiResponse.createInvalidOptionResponse().getMessage());
                    break;
            }

            System.out.print(mainMenu.getMessage());
        }
    }
}
