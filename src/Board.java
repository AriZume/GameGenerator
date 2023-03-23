package MainPackage;
import java.util.ArrayList;
import java.util.Scanner;

public class Board
{
    private final ArrayList<Tile> totalTiles;
    private final ArrayList<Player> totalPlayers;
    private final ArrayList<Dice> boardDice;
    int currentPlayer = -1;

    private Player getNextPlayer() {
        int nextPlayer = currentPlayer++;
        currentPlayer = currentPlayer%totalPlayers.size();
        return totalPlayers.get(nextPlayer);
    }
    public Board(int diceAmount)
    {
        totalPlayers = new ArrayList<>();
        totalTiles = new ArrayList<>();
        boardDice = new ArrayList<>();
        for(int i = 0; i < diceAmount; i++)
        {
            boardDice.add(new Dice());
        }
    }

    public void startGame()
    {
        System.out.println("\n\n\nAll players are placed on tile " + totalTiles.get(0).getTileNumber());
        int playerRoll;
        boolean endGame = false;

        while(!endGame)
        {
           for (Player player:totalPlayers)
            {
                int option = PlayGameScreen.getOption(player.getName(),
                        player.getCurrentPosition(), totalTiles.size(),totalPlayers.indexOf(player));

                //IN-GAME MENU
                switch(option)      //TODO:REPLACE WITH ENHANCED SWITCH
                {
                    case 1:
                        playerRoll = getDiceRoll();

                        player.setCurrentPosition(playerRoll);
                        System.out.println("You made " + playerRoll + " moves forward.");
                        System.out.println("End of turn.\n");

                            if(player.getCurrentPosition()> totalTiles.size())
                            {
                                player.setCurrentPosition(totalTiles.size()-player.getCurrentPosition());
                            }
                            System.out.println(player.getName() + " is on tile " + player.getCurrentPosition());

                        break;
                    case 3:
                        System.out.println("Exiting game.");
                        endGame = true;
                        break;
                    default:
                        System.out.println("Option not available.");
                        break;
                }

                if(endGame)
                {
                    break;
                }
                else if ( weHaveAWinner(player) )
                {
                    System.out.println("\n-----------------------------------------------------------------------\nPlayer " + (totalPlayers.indexOf(player)+1) + ": " + totalPlayers.get(i).getName()+" won the game!\n\n\n");
                    endGame = true;
                    break;
                }
            }
        }
    }

    private boolean weHaveAWinner(Player player) {
        return player.getCurrentPosition() >= totalTiles.size();
    }

    // Players------------
    public void addPlayer(Player newPlayer)
    {
        totalPlayers.add(newPlayer);
    }

    /* //FOR DEBUGGING
    public void showPlayer()
    {
        int count =0;
        for(Player currentPlayer : totalPlayers)
        {
            count++;
            String name = currentPlayer.getName();
            int pos = currentPlayer.getCurrentPosition();
            System.out.println("Player" + count +": " +name );
            System.out.println("Position: " +pos );
        }
    }
    */

    //Tiles-----------------
    public void addTileNumber(Tile newTile)
    {
        totalTiles.add(newTile);
    }

    /* //FOR DEBUGGING
    public void showTileNumber()
    {
        for(Tile currentTile : totalTiles)
        {
            int number = currentTile.getTileNumber();
            System.out.println("Number: " + number);
        }
    }
    */

    public int getDiceRoll()
    {
        int totalRoll = 0;
        System.out.print("You rolled: ");

        for(int i = 0; i < boardDice.size(); i++)
        {
            totalRoll += boardDice.get(i).getNumberRolled();
            System.out.print(boardDice.get(i).getNumberRolled());
            if(!((i+1) >= boardDice.size()))
            {
                System.out.print(" and ");
            }
            else
            {
                System.out.println();
            }

            boardDice.get(i).rollDice();
        }
        return totalRoll;
    }
}
