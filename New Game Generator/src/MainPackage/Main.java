package MainPackage;

import GamePackage.Response;
import IOPackage.GameLoader;
import UIPackage.UIResponse;
import UIPackage.UIScreen;

public class Main
{
    enum Option
    {
        DESIGN_PLAY(1),
        LOAD(2),
        HELP(3),
        EXIT(4),
        SQUARE_BOARD(1),
        CIRCULAR_BOARD(2);

        public final int value;
        Option(int value)
        {
            this.value = value;
        }
        public int getValue()
        {
            return value;
        }
    }
    // TODO: Ask about the removal of if-statements, and the game constructors to include the [yYnN].
    public static void main(String[] args)
    {
        int userInput;
        GameSetupHelper gameSetupHelper = new GameSetupHelper();
        UIScreen uiScreen = new UIScreen();
        GameLoader loader = new GameLoader();
        UIResponse uiResponse = new UIResponse();

        Response mainMenu = uiResponse.createMainMenuResponse(Option.DESIGN_PLAY.getValue(), Option.LOAD.getValue(),
                Option.HELP.getValue(), Option.EXIT.getValue());
        System.out.print(mainMenu.getMessage());

        while(true)
        {
            userInput = uiScreen.checkUserInput(1, 4);

            if(userInput == Option.EXIT.getValue())
            {
                break;
            }
            Option userOption = Option.values()[userInput - 1];
            switch (userOption)
            {
                case DESIGN_PLAY:
                    gameSetupHelper.createNewGame(uiResponse, uiScreen);
                    break;
                case LOAD:
                    gameSetupHelper.loadGame(loader);
                    break;
                case HELP:
                    gameSetupHelper.showHelp(uiResponse);
                    break;
                default:
                    System.out.print(uiResponse.printInvalidOption().getMessage());
                    break;
            }

            System.out.println(mainMenu.getMessage());
        }
    }
}
