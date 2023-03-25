package MainPackage;
import java.util.ArrayList;
import java.util.Random;

public class GameHandler
{
    private final ArrayList<Player> totalPlayers;
    private final ArrayList<Tile> totalTiles;
    private final int dice;
    int currentPlayer = 0;

    /* //NOT SURE HOW TO DO THIS
    private Player getNextPlayer() {
        int nextPlayer = currentPlayer++;
        currentPlayer = currentPlayer%totalPlayers.size() + 1;
        return totalPlayers.get(nextPlayer);
    }
     */
    public GameHandler(int diceAmount, Board tileList)
    {
        totalPlayers = new ArrayList<>();
        totalTiles = tileList.getTileList();
        dice = diceAmount;
    }

    public void addPlayer(Player newPlayer)
    {
        totalPlayers.add(newPlayer);
    }
    public void startGame()
    {
        System.out.println("\n\nAll players are placed on tile " + totalTiles.get(0).getTileNumber());
        int playerRoll;
        boolean endGame = false;
        while(!endGame)
        {
           for (Player player : totalPlayers)
            {
                int option = PlayGameScreen.getOption(player.getName(),
                             player.getCurrentPosition(), totalTiles.size(),totalPlayers.indexOf(player));

                //IN-GAME MENU
                switch(option)
                {
                    case 1:
                        playerRoll = getDiceRoll(dice);

                        player.setCurrentPosition(playerRoll);

                        if(player.getCurrentPosition() > totalTiles.size())
                        {
                            player.setCurrentPosition(totalTiles.size()-player.getCurrentPosition());
                        }

                        PlayGameScreen.endTurnScreen(player.getName(), player.getCurrentPosition(), playerRoll);

                        break;
                    case 3:
                        endGame = PlayGameScreen.exitGame();

                        break;

                    default:
                        System.out.println("Option not available.");

                        break;
                }

                //EXIT GAME OR WIN CHECK
                if(endGame)
                {
                    break;
                }
                else if(weHaveAWinner(player))
                {
                    endGame = PlayGameScreen.winnerScreen(totalPlayers.indexOf(player), player.getName());
                    break;
                }
            }
        }
    }

    private boolean weHaveAWinner(Player player)
    {
        return player.getCurrentPosition() >= totalTiles.size();
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

}
