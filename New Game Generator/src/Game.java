import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game
{
    private final int diceAmount;
    private final ArrayList<Player> players;
    private final Board board;

    public Game(int playerAm, int tileAm, int diceAm)
    {
        this.diceAmount = diceAm;
        this.board = new Board(tileAm);
        this.players = new ArrayList<>();
        createPlayers(playerAm);
    }

    public Game(int playerAm, int tileAm, int diceAm, int enhancedTiles)
    {
        this.diceAmount = diceAm;
        this.board = new Board(tileAm, enhancedTiles);
        this.players = new ArrayList<>();
        createPlayers(playerAm);
    }
    public void createPlayers(int playerAmount)
    {
        Scanner scanner = new Scanner(System.in);

        for(int i = 0; i < playerAmount; i++)
        {
            System.out.print("Player : ");
            String name = scanner.nextLine();
            players.add(new Player(name));
        }
    }

    public int getPlayerIndex(Player player)
    {
        return players.indexOf(player);
    }

    // Rolls the dice and updates the players position
    public void movePlayer(Player player)
    {
        int roll = getDiceRoll();
        player.setPositionAfterRoll(roll);
    }

    private int getDiceRoll()
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

    public boolean weHaveAWinner(Player player)
    {
        return player.getCurrentPosition() == board.getTiles().size();
    }

    public void checkPlayerPosition(Player player)
    {
        if(player.getCurrentPosition() >= board.getTiles().size())
        {
            player.setCurrentPosition(board.getTiles().size());
        }
    }

    public void startGame()
    {
        final int optRoll = 1;
        final int optSave = 2;
        final int optExit = 3;

        Screen screen = new Screen();
        Scanner input = new Scanner(System.in);

        boolean endGame = false;
        while (!endGame)
        {
            for (Player player : players)
            {
                screen.printPlayerTurn(player.getName(), getPlayerIndex(player));
                screen.printDescriptiveMap(player.getCurrentPosition(), board.getTiles().size());
                screen.printInGameMenu();
                int option = 0;
                do {

                    try {
                        option = input.nextInt();
                        switch (option)
                        {
                            case optRoll:
                                movePlayer(player);
                                checkPlayerPosition(player);

                                screen.printEndTurn(players);
                                break;

                            case optSave:

                                break;

                            case optExit:
                                endGame = true;
                                break;

                            default:
                                System.out.println("Invalid option. Please try again.");
                                break;
                        }
                    } catch (InputMismatchException e)
                    {
                        System.out.println("Invalid option. Please try again");
                        input.nextLine();
                    }
                }while(option <= 0 || option > 3);

                if (endGame)
                {
                    break;
                } else if(weHaveAWinner(player))
                {
                    endGame = screen.printWinner(getPlayerIndex(player), player.getName());
                    break;
                }
            }
        }
    }

    public void printTilesPower()
    {
        for(Tile t : board.getTiles())
        {
            System.out.println(t.getPower());
        }
    }

}
