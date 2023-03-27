package MainPackage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

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
            Map<Integer, Runnable> options = new HashMap<>();
            options.put(1, () -> {
                System.out.println("\t  GAME DESIGN\n========================");
                myMenu.setPlayersAmount();
                myMenu.setTileAmount();
                myMenu.tilePowerScreen();
                System.out.print("Please set power-up number tile treksto");
                int number = input.nextInt();

                myMenu.setDiceAmount();

                    GameHandler myGame = new GameHandler(myMenu.getDiceAmount(), myBoard);

                System.out.println("---Enter names---");
                for (int i = 0; i < myMenu.getPlayersAmount(); i++)
                {
                    System.out.print("Player : ");
                    String name = input.nextLine();

                    myGame.addPlayer(new Player(name));
                }

                for (int i = 1; i <= myMenu.getTileAmount(); i++)
                {
                    myBoard.addTileNumber(new Tile(i));
                }

                myBoard.randomPowerUpGenerator(number);
                ArrayList<Tile> tiles = myBoard.getTileList();
                for (int i = 0; i <= tiles.size(); i++)
                {
                    System.out.println(tiles.get(i).getTilePower());
                }
                myGame.startGame();
            });
            options.put(2, myMenu::loadScreen);
            options.put(3, myMenu::helpScreen);
            options.getOrDefault(userOption, myMenu::defaultScreen).run();
            userOption = myMenu.getOptionMainMenu();
        }
    }
}
