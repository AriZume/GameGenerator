package MainPackage;

import TilesPackage.EnhancedTile;
import java.util.*;

public class Game {
    InGameScreen inGameScr = new InGameScreen();
    private boolean gameOver = false;
    private Player winner;
    private final ArrayList<Player> totalPlayers;
    private final ArrayList<EnhancedTile> totalTiles;
    private final int dice;

    public Game(ArrayList<Player> totalPlayers, ArrayList<EnhancedTile> totalTiles, int dice) {
        this.totalPlayers = totalPlayers;
        this.totalTiles = totalTiles;
        this.dice = dice;
    }

    public void setRandomEnhancedTilerPower(int enhancedTileAmount, int posNumber, int negNumber) {
        ArrayList<Integer> tempList = new ArrayList<>();

        for (int i = 0; i < totalTiles.size(); i++)
        {
            tempList.add(i);
            Collections.shuffle(tempList);
        }

        for (int i = 1; i < enhancedTileAmount; i++)
        {
            for (EnhancedTile tile : totalTiles)
            {
                if (tile.getTileNumber() == tempList.get(i))
                {
                    totalTiles.get(tempList.get(i) - 1).setTilePower(posNumber, negNumber);
                }
            }
        }
    }


    public boolean isWinner(Player player)
    {
        return player.getCurrentPosition() >= totalTiles.size();
    }

    public void setWinner(Player player) {
        winner = player;
    }

    public Player getWinner()
    {
        return winner;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean value) {
        gameOver = value;
    }
    public ArrayList<Player> getTotalPlayers()
    {
        return totalPlayers;
    }

    public ArrayList<EnhancedTile> getTotalTiles()
    {
        return totalTiles;
    }

    public int getBoardSize()
    {
        return totalTiles.size();
    }

    public Player getPlayerAt(int i)
    {
        return totalPlayers.get(i);
    }

    public int indexOf(Player player)
    {
        return totalPlayers.indexOf(player);
    }

    public int movePlayer(Player player)
    {
        int playerRoll = getDiceRoll(dice);
        player.setCurrentPosition(playerRoll);

        if (player.getCurrentPosition() >= totalTiles.size())
        {
            player.setCurrentPosition(totalTiles.size() - player.getCurrentPosition());
        }
        return playerRoll;
    }

    public String  checkPosition(Player player)
    {
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

        inGameScr.rolledScreen();

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
        EnhancedTile currentTile = totalTiles.get(player.getTileIndex());
        return currentTile.updateStatus(player);

    }

    public void setPriorityRoll(Player player)
    {
        int roll = getDiceRoll(dice);

        player.setPriorityRoll(roll);
    }

    public void sortPlayers()
    {
        totalPlayers.sort(Comparator.comparing(Player::getPriorityRoll).reversed());
    }

    public void setPlayerPriority()
    {
        inGameScr.whosFirstScreen();

        for (Player player : getTotalPlayers()) {
            inGameScr.priorityActionScreen(player.getName());
            setPriorityRoll(player);
        }

        sortPlayers();

        inGameScr.priorityResults(getTotalPlayers());
    }
}
