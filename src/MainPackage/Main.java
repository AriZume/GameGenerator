package MainPackage;

import TilesPackage.EnhancedTile;

import java.util.Scanner;


public class Main
{
    public static void main(String[] args)
    {
        Board myBoard = new Board();
        MainMenuScreen myMenu = new MainMenuScreen();

        Scanner input = new Scanner(System.in);

        int EXIT = 4;
        int userOption = myMenu.getOptionMainMenu();

        while(userOption != EXIT)
        {
            switch (userOption)
            {
                case 1:
                    System.out.println("\t  GAME DESIGN\n========================");

                    myMenu.setPlayersAmount();

                    myMenu.setTileAmount();
                    for (int i = 1; i <= myMenu.getTileAmount(); i++)
                    {
                        myBoard.addTileNumber(new EnhancedTile(i));
                    }

                    String powerOption = myMenu.tilePowerScreen();

                    myMenu.setDiceAmount();

                    GameHandler myGame = new GameHandler(myMenu.getDiceAmount(), myBoard);

                    System.out.println("---Enter names---");
                    for (int i = 0; i < myMenu.getPlayersAmount(); i++)
                    {
                        System.out.print("Player : ");
                        String name = input.nextLine();

                        myGame.addPlayer(new Player(name));
                    }

                    myGame.startGame(powerOption, myMenu);
                    break;
                case 2:
                    myMenu.loadScreen();
                    break;
                case 3:
                    myMenu.helpScreen();
                    break;
                default:
                    myMenu.defaultScreen();
                    break;
            }
            userOption = myMenu.getOptionMainMenu();
        }
    }
}
