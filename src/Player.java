import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private String name;
    private int position;
    private int prevPos;
    private int playerTag;

    public Player(String name) {
        this.name = name;
        this.position = 0; //Always starts from 0
        this.prevPos = 0;
        this.playerTag = 0;
    }

    /*
    public String toString() {
        return "Name: " + name + ", Position: " + position;
    }
     */
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
    public void setPrevPos(int newPrevPos)
    {
        prevPos = newPrevPos;
    }
    public int getPrevPos()
    {
        return prevPos;
    }
    public void setPlayerTag(int newPlayerTag)
    {
        playerTag = newPlayerTag;
    }
    public int getPlayerTag()
    {
        return playerTag;
    }
    static List<Player> listOfPlayers = new ArrayList<>();
    public static List getPlayerList() {
        return listOfPlayers;
    }
    public static void giveNamesToPlayers() {
        Scanner sc = new Scanner(System.in);
        for (int i = 1; i < GameCreation.givePlayers() + 1; i++) {
            System.out.print("Enter a Username for player" + i + ": ");
            String getPlayerName = sc.nextLine();
            Player newPlayer = new Player(getPlayerName);
            listOfPlayers.add(newPlayer);
        }

    }
    public static void showPlayerList() {
        System.out.println("=======CURRENT PLAYERS=======");
        int counter = 1;
        for (Player c : listOfPlayers) {
            System.out.println("Player" + counter + ": " + c.getName());
            c.setPlayerTag(counter);
            counter++;

        }
    }
}