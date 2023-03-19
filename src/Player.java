import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
       private String name;
    private int position;

    public Player(String name) {
        this.name = name;
        this.position = 0; //Always starts from 0
    }

    public String toString() {
        return "Name: " + name + ", Position: " + position;
    }

    public void setName(String newName) {
        name = newName;

    }

    public String getName() {

        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int newPosition) {

        position = newPosition;
    }

    public void printName() {

        System.out.println(name);
    }

    public void printPosition() {
        System.out.println(position);
    }

    static List<Player> listOfPlayers = new ArrayList<>();

    public static void giveNamesToPlayers() {
        Scanner sc = new Scanner(System.in);
        for (int i = 1; i < GameCreation.givePlayers() + 1; i++) {
            System.out.print("Enter a Username for player" + i + ": ");
            String getPlayerName = sc.nextLine();
            Player newPlayer = new Player(getPlayerName);
            newPlayer.setPosition(0);
            listOfPlayers.add(newPlayer);
        }
        System.out.println("=======CURRENT PLAYERS=======");
        int counter = 1;
        for (Player c : listOfPlayers) {
            System.out.println("Player" +counter+ ": " + c.getName());
            counter++;

        }
    }
}