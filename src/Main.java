package MainPackage;

import java.util.Scanner;

// IMPORTANT
// TODO: ASK ABOUT JSON FILES FOR SAVING AND LOADING

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
                        myBoard.addTileNumber(new Tile(i));
                    }

                    myMenu.tilePowerScreen();

                    myMenu.setDiceAmount();

                    GameHandler myGame = new GameHandler(myMenu.getDiceAmount(), myBoard);

                    System.out.println("---Enter names---");
                    for (int i = 0; i < myMenu.getPlayersAmount(); i++)
                    {
                        System.out.print("Player : ");
                        String name = input.nextLine();

                        myGame.addPlayer(new Player(name));
                    }

                    myGame.startGame(myMenu.getPositiveNumber(), myMenu.getNegativeNumber());
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
