package MainPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Game {
    private final ArrayList<Player> totalPlayers;
    private final ArrayList<Tile> totalTiles;
    private final int dice;

    public Game(ArrayList<Player> totalPlayers, ArrayList<Tile> totalTiles, int dice) {
        this.totalPlayers = totalPlayers;
        this.totalTiles = totalTiles;
        this.dice = dice;
    }

    public void setRandomEnhancedTilerPower(int posNumber, int negNumber) {
        ArrayList<Integer> tempList = new ArrayList<>();

        for (int i = 0; i < totalTiles.size(); i++) {
            tempList.add(i);
            Collections.shuffle(tempList);
        }

        for (int i = 1; i < totalTiles.size(); i++) {
            for (Tile tile : totalTiles) {
                if (tile.getTileNumber() == tempList.get(i)) {
                    totalTiles.get(tempList.get(i) - 1).setTilePower(posNumber, negNumber);
                }
            }
        }
    }

    public ArrayList<Player> getTotalPlayers() {
        return totalPlayers;
    }

    public ArrayList<Tile> getTotalTiles() {
        return totalTiles;
    }

    public int getBoardSize() {
        return totalTiles.size();
    }

    public Player getPlayerAt(int i) {
        return totalPlayers.get(i);
    }

    public int indexOf(Player player) {
        return totalPlayers.indexOf(player);
    }

    public int movePlayer(Player player) {
        int playerRoll = getDiceRoll(dice);
        player.setCurrentPosition(playerRoll);

        if (player.getCurrentPosition() >= totalTiles.size()) {
            player.setCurrentPosition(totalTiles.size() - player.getCurrentPosition());
        }
        return playerRoll;
    }

        //showRollScreen(playerRoll);

    public String  checkPosition(Player player) {
        String message = checkIfEnhancedTile(player);

        if (player.getCurrentPosition() >=  totalTiles.size())
        {
            player.setCurrentPosition(totalTiles.size() - player.getCurrentPosition());
        }
        else if(player.getCurrentPosition() < 0)
        {
            player.resetPosition();
        }
        return message;

    }
    private int getDiceRoll(int diceAmount)
    {
        int totalRoll = 0;
        int diceRoll;
        Random roll = new Random();

        System.out.print("You rolled: ");

        for(int i = 0; i < diceAmount; i++)
        {
            diceRoll = roll.nextInt(6)+1;
            System.out.print(diceRoll);

            if(!((i+1) >= diceAmount))
            {
                System.out.print(" and ");
            }
            totalRoll += diceRoll;
        }

        System.out.print("\n");
        return totalRoll;
    }
    private String checkIfEnhancedTile(Player player)
    {
        Tile currentTile = totalTiles.get(player.getTileIndex());
        return currentTile.updateStatus(player);

    }

    public void setPriorityRoll(Player player) {
        int roll = getDiceRoll(dice);

        player.setPriorityRoll(roll);
    }

    public void sortPlayers() {
        totalPlayers.sort(Comparator.comparing(Player::getPriorityRoll).reversed());

    }
}
