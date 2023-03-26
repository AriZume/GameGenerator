package MainPackage;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

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
                myMenu.setDiceAmount();

                    GameHandler myGame = new GameHandler(myMenu.getDiceAmount(), myBoard);

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
            });
            options.put(2, () -> System.out.println("Load is currently unavailable."));
            options.put(3, () -> System.out.println("Can't give you help :(\nPlease donate $19.99 to unlock GameGenerator Premium."));
            options.getOrDefault(userOption, () -> System.out.println("Option not valid!")).run();
            userOption = myMenu.getOptionMainMenu();
        }
    }
}
