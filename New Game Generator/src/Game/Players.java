package Game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Players
{
    private final ArrayList<Player> players;
    private int currentPlayerIndex;
    private Player currentPlayer;

    public Players(int playerAmount, int maxPoints)
    {
        this.players = new ArrayList<>();
        createPlayers(playerAmount, maxPoints);
        this.currentPlayerIndex = 0;
        this.currentPlayer = players.get(currentPlayerIndex);
    }
    public Players(ArrayList<String> loadPlayers, int loadPlayerIndex, ArrayList<String> loadPositions,
                   ArrayList<String> loadLaps, ArrayList<String> loadPoints)
    {
        this.players = new ArrayList<>();
        for (String loadPlayer : loadPlayers)
        {
            players.add(new Player(loadPlayer));
        }

        loadPlayerStats(loadPositions, loadLaps, loadPoints);
        this.currentPlayerIndex = loadPlayerIndex;
        this.currentPlayer = players.get(currentPlayerIndex);
    }
    //TODO: NA VGALOUME TA SYSTEMOUT SOSOSOSOSOSOSSOS!
    private void createPlayers(int playerAmount, int maxPoints) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < playerAmount; i++) {
            System.out.print("Player : ");
            String name;
            do {
                name = scanner.nextLine();
                if (name.isBlank()) {
                    System.out.print("Invalid name. Please try again: ");
                } else {
                    names.add(name);
                }
            } while (name.isBlank());

            players.add(new Player(name, maxPoints / 10));
        }
    }
    public Player getCurrentPlayer()
    {
        return this.currentPlayer;
    }
    public ArrayList<Player> getPlayers()
    {
        return this.players;
    }
    public int getCurrentPlayerIndex()
    {
        return this.currentPlayerIndex;
    }

    public void loadPlayerStats(ArrayList<String> playerPositions, ArrayList<String> playerLaps, ArrayList<String> playerPoints)
    {
        for (int i=0; i < players.size();i++)
        {
            players.get(i).setCurrentPosition(Integer.parseInt(playerPositions.get(i)));
            players.get(i).setLap(Integer.parseInt(playerLaps.get(i)));
            players.get(i).setPoints(Integer.parseInt(playerPoints.get(i)));
        }
    }
    public void shufflePlayers()
    {
        players.sort(Comparator.comparing(Player::getQueuePosition).reversed());
    }

    public Player getNextPlayer()
    {
        Player nextPlayer;
        if (currentPlayer.hasPlayAgainCard())
        {
            currentPlayer.setHasPlayAgainCard(false);
            nextPlayer = currentPlayer;
        }
        else if(currentPlayerIndex == players.size()-1)
        {
            currentPlayerIndex = 0;
            nextPlayer = currentPlayer = players.get(currentPlayerIndex);
        }
        else
        {
            currentPlayerIndex++;
            nextPlayer = currentPlayer = players.get(currentPlayerIndex);
        }
        return nextPlayer;
    }
}
