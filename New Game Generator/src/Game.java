import java.util.*;

public class Game
{
    private final int diceAmount;
    private final ArrayList<Player> players;
    private final Board board;
    private final Screen screen = new Screen();

    public Game(int playerAm, int tileAm, int diceAm)
    {
        this.diceAmount = diceAm;
        this.board = new Board(tileAm);
        this.players = new ArrayList<>();
        createPlayers(playerAm);
    }

    public Game(int playerAm, int tileAm, int diceAm, String cards)
    {
        this.diceAmount = diceAm;
        this.board = new Board(tileAm, cards);
        this.players = new ArrayList<>();
        createPlayers(playerAm);
    }


    public Game(int playerAm, int tileAm, int diceAm,int enhancedTiles,String cards)
    {
        this.diceAmount = diceAm;
        this.board = new Board(tileAm,enhancedTiles, cards);
        this.players = new ArrayList<>();
        createPlayers(playerAm);
    }

    public void createPlayers(int playerAmount)
    {
        Scanner scanner = new Scanner(System.in);

        for(int i = 0; i < playerAmount; i++)
        {
            System.out.print("Player : ");
            String name = "";
            do
            {
                name = scanner.nextLine();
                if(name.isBlank())
                {
                    System.out.print("Invalid name. Please try again: ");
                }
            }while(name.isBlank());

            players.add(new Player(name));
        }
    }

    public int getPlayerIndex(Player player)
    {
        return players.indexOf(player);
    }

    // Moves player according to the roll and enhanced tile-if there are any.
    public void movePlayer(Player player)
    {
        player.setPersonalRoll(getDiceRoll());

        player.setNewPosition(player.getPersonalRoll());
        checkPlayerPosition(player);

        board.getTiles().get(player.getCurrentPosition()-1).updatePlayerStatus(player);
        checkPlayerPosition(player);
    }

    public void rollQueueOfPlayers()
    {
        for (Player player : players)
        {
            System.out.print(player.getName()+ " ");
            int roll = getDiceRoll();
            player.setQueuePosition(roll);
        }
        players.sort(Comparator.comparing(Player::getQueuePosition).reversed());
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

    // Keeps track of the current position not exceeding the maximum amount of tiles
    // and not being less than 1.
    public void checkPlayerPosition(Player player)
    {
        if(player.getCurrentPosition() >= board.getTiles().size())
        {
            player.setCurrentPosition(board.getTiles().size());
        }

        if(player.getCurrentPosition() <= 0)
        {
            player.setCurrentPosition(1);
        }
    }

    public void startGame()
    {
        final int optRoll = 1;
        final int optSave = 2;
        final int optExit = 3;

        Screen screen = new Screen();
        Scanner input = new Scanner(System.in);

        rollQueueOfPlayers();

        boolean endGame = false;
        while (!endGame)
        {
            for (Player player : players)
            {
                screen.printPlayerTurn(player.getName(), getPlayerIndex(player));
                screen.printDescriptiveMap(player.getCurrentPosition(), board.getTiles().size());
                screen.printInGameMenu();
                int option = 0;
                do
                {
                    try
                    {
                        option = input.nextInt();
                        switch (option)
                        {
                            case optRoll:
                                movePlayer(player);

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

    // Debug only
    public void printTilesPower()
    {
        for(Tile t : board.getTiles())
        {
            System.out.println(t);
        }
    }

}
