package MainPackage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

// IMPORTANT
// TODO: ASK ABOUT JSON FILES FOR SAVING AND LOADING

// TODO: MIGHT REMOVE HASHMAP
public class Main
{
    public static void main(String[] args)
    {
        Board myBoard = new Board();
        MainMenuScreen myMenu = new MainMenuScreen();
        GameHandler myGame;
        Scanner input = new Scanner(System.in);

        int EXIT = 4;
        int userOption = myMenu.getOptionMainMenu();

        while(userOption != EXIT)
        {
            // MAIN MENU
            switch (userOption)
            {
                case 1:
                    System.out.println("\t  GAME DESIGN\n========================");
                    myMenu.setPlayersAmount();
                    myMenu.setTileAmount();
                    myMenu.setDiceAmount();
                    System.out.print("Please set power-up number tile: ");
                    int number = input.nextInt();
                    input.nextLine();
                    myMenu.setDiceAmount();
                    myGame = new GameHandler(myMenu.getDiceAmount(), myBoard);

                    System.out.println("---Enter names---");
                    for (int i = 0; i < myMenu.getPlayersAmount(); i++)
                    {
                        System.out.print("Player " + (i + 1) + " : ");
                        String name = input.nextLine();

                        myGame.addPlayer(new Player(name));
                    }

                    for (int i = 1; i <= myMenu.getTileAmount(); i++)
                    {
                        myBoard.addTileNumber(new Tile(i));
                    }

                    myGame.startGame();
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
