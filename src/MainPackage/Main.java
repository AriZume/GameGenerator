package MainPackage;

import TilesPackage.EnhancedTile;
import java.util.Scanner;


public class Main
{
    public static void main(String[] args)
    {
        Board myBoard = new Board();
        MainMenuScreen myMenuScreen = new MainMenuScreen();
        Scanner input = new Scanner(System.in);

        int EXIT = 4;
        int userOption = myMenuScreen.getOptionMainMenu();

        while(userOption != EXIT)
        {
            switch (userOption)
            {
                case 1:
                    myMenuScreen.gameDesignScreen();
                    myMenuScreen.setPlayersAmount();
                    myMenuScreen.setTileAmount();
                    for (int i = 1; i <= myMenuScreen.getTileAmount(); i++)
                    {
                        myBoard.addTileNumber(new EnhancedTile(i));
                    }

                    String powerOption = myMenuScreen.tilePowerScreen();

                    myMenuScreen.setDiceAmount();

                    GameHandler myGame = new GameHandler(myMenuScreen.getDiceAmount(), myBoard);

                    myMenuScreen.enterNamesScreen();
                    for (int i = 0; i < myMenuScreen.getPlayersAmount(); i++)
                    {
                        myMenuScreen.CurrentPlayerScreen();
                        String name = input.nextLine();

                        myGame.addPlayer(new Player(name));
                    }

                    myGame.startGame(powerOption, myMenuScreen);
                    break;
                case 2:
                    myMenuScreen.loadScreen();
                    break;
                case 3:
                    myMenuScreen.helpScreen();
                    break;
                default:
                    myMenuScreen.defaultScreen();
                    break;
            }
            userOption = myMenuScreen.getOptionMainMenu();
        }
    }
}
