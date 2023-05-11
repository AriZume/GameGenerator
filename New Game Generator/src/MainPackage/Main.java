package MainPackage;

import GamePackage.Response;
import IOPackage.GameLoader;
import UIPackage.UIResponse;
import UIPackage.UserInputScreen;

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
                    gameSetupHelper.createNewGame(uiResponse, userInputScreen);
                    break;
                case LOAD:
                    gameSetupHelper.loadGame(loader);
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
