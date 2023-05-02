import java.util.*;

public class Game
{
    public final int OPT_ROLL = 1;
    public final int OPT_SAVE = 2;
    public final int OPT_EXIT = 3;
    private String boardType;
    private int lapsToWin;
    private final int diceAmount;
    private final ArrayList<Player> players;
    private final Board board;
    private final Screen screen = new Screen();

    // Simple constructor.
    public Game(int playerAm, int tileAm, int diceAm)
    {
        this.diceAmount = diceAm;
        this.board = new Board(tileAm);
        this.players = new ArrayList<>();
        createPlayers(playerAm);
    }

    // Constructor for game with enhanced tiles only.
    public Game(int playerAm, int tileAm, int diceAm, int enTiles)
    {
        this.diceAmount = diceAm;
        this.board = new Board(tileAm, enTiles);
        this.players = new ArrayList<>();
        createPlayers(playerAm);
    }

    // Constructor for game with cards only.
    public Game(int playerAm, int tileAm, int diceAm, String maxPoints)
    {
        this.diceAmount = diceAm;
        this.board = new Board(tileAm, maxPoints);
        this.players = new ArrayList<>();
        createPlayers(playerAm);
    }

    // Constructor with both cards and enhanced tiles.
    public Game(int playerAm, int tileAm, int diceAm, int enTiles, String maxPoints)
    {
        this.diceAmount = diceAm;
        this.board = new Board(tileAm,enTiles , maxPoints);
        this.players = new ArrayList<>();
        createPlayers(playerAm);
    }

    public void setLapsToWin(int lapsToWin)
    {
        this.lapsToWin = lapsToWin;
    }
    public void setBoardType(String boardType)
    {
        this.boardType = boardType;
    }
    public void createPlayers(int playerAmount)
    {
        Scanner scanner = new Scanner(System.in);

        for(int i = 0; i < playerAmount; i++)
        {
            System.out.print("Player : ");
            String name;
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

    // TODO: MAKE MORE BEAUTIFUL ;)
    public void decidePlayerTurn()
    {
        System.out.println("Let's see who's starting first!");
        for (Player player : players)
        {
            System.out.print(player.getName() + ": ");
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
        if(boardType.equals("Square"))
        {
            return player.getCurrentPosition() == board.getTiles().size();
        }
        else
        {
            if(lapsToWin != 0)
            {
                return player.getLap() == lapsToWin;
            }
            else
            {
                return player.getPoints() >= board.getMaxPoints();
            }
        }
    }

    // Moves player according to the roll and enhanced tile-if there are any.
    public Response movePlayer(Player player)
    {
        player.setPersonalRoll(getDiceRoll());

        player.setNewPosition(player.getPersonalRoll());
        checkPlayerPosition(player);

        Response response = board.getTiles().get(player.getCurrentPosition()-1).updatePlayerStatus(player);

        checkPlayerPosition(player);

        // If the player is changed by an enhanced tile and lands on a card tile, execute card's updatePlayerStatus.
        // After the action reset players isFromEnhanced to false.
        if( player.isFromEnhanced() && board.getTiles().get(player.getCurrentPosition()-1).getClass().getName().equals("CardTile"))
        {
            board.getTiles().get(player.getCurrentPosition()-1).updatePlayerStatus(player);
        }
        player.setIsFromEnhanced(false);

        // Might be needed if future cards move the player.
        // checkPlayerPosition(player);
        return response;
    }

    // Keeps track of the current position not exceeding the maximum amount of tiles
    // and not being less than 1.
    public void checkPlayerPosition(Player player)
    {
        if(boardType.equals("Square"))
        {
            if (player.getCurrentPosition() >= board.getTiles().size()) {
                player.setCurrentPosition(board.getTiles().size());
            }

            if (player.getCurrentPosition() <= 0) {
                player.setCurrentPosition(1);
            }
        }
        else
        {
            if(player.getCurrentPosition() > board.getTiles().size())
            {
                if(lapsToWin != 0)
                {
                    player.setLap(1);
                }
                player.setCurrentPosition(player.getCurrentPosition() - board.getTiles().size());
            }
        }
    }

    public void startGameSquare()
    {
        Scanner input = new Scanner(System.in);

        decidePlayerTurn();

        boolean endGame = false;
        while (!endGame)
        {
            for(int i = 0; i < players.size(); i++)
            {

                screen.printPlayerTurn(players.get(i).getName(), getPlayerIndex(players.get(i)));
                screen.printDescriptiveMap(players.get(i).getCurrentPosition(), board.getTiles().size());
                screen.printInGameMenu();

                int option = 0;
                do
                {
                    try
                    {
                        option = input.nextInt();

                        switch (option)
                        {
                            case OPT_ROLL:
                                movePlayer(players.get(i));
                                screen.printEndTurn(players);
                                break;

                            case OPT_SAVE:
                                option=0;
                                break;

                            case OPT_EXIT:
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
                    if(option == 0)
                    {
                        screen.printInGameMenu();
                    }
                }while(option <= 0 || option > 3);

                if (endGame)
                {
                    break;
                }
                else if(weHaveAWinner(players.get(i)))
                {
                    endGame = screen.printWinner(getPlayerIndex(players.get(i)), players.get(i).getName());
                    break;
                }
            } // End for
        } // End while
    }

    public void startGameCircle()
    {
        int previousPlayer = 0;

        Scanner input = new Scanner(System.in);

        decidePlayerTurn();

        boolean endGame = false;
        while (!endGame)
        {
            for(int i = 0; i < players.size(); i++)
            {

                if(players.get(previousPlayer).getHasPlayAgainCard())
                {
                    players.get(previousPlayer).setHasPlayAgainCard(false);
                    i = previousPlayer;
                }

                if(lapsToWin != 0)
                {
                    screen.printPlayerTurnLap(players.get(i).getName(), getPlayerIndex(players.get(i)), players.get(i).getLap());
                }
                else
                {
                    screen.printPlayerTurnPoints(players.get(i).getPoints(),players.get(i).getName());
                }
                screen.printDescriptiveMap(players.get(i).getCurrentPosition(), board.getTiles().size());
                screen.printInGameMenu();

                int option = 0;
                do
                {
                    try
                    {
                        option = input.nextInt();

                        switch (option)
                        {
                            case OPT_ROLL:
                                Response response = movePlayer(players.get(i));
                                screen.printEndTurn(players);
                                System.out.println(response.getMessage());
                                break;

                            case OPT_SAVE:
                                option=0;
                                break;

                            case OPT_EXIT:
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
                    if(option == 0)
                    {
                        screen.printInGameMenu();
                    }
                }while(option <= 0 || option > 3);

                if (endGame)
                {
                    break;
                }
                else if(weHaveAWinner(players.get(i)))
                {
                    if(lapsToWin != 0)
                    {
                        endGame = screen.printWinner(getPlayerIndex(players.get(i)), players.get(i).getName());
                    }
                    else
                    {
                        endGame = screen.printWinner(getPlayerIndex(players.get(i)), players.get(i).getName(), players.get(i).getPoints());
                    }
                    break;
                }
                previousPlayer = i;
            } // End for
        } // End while
    }

    // Debug only
    /* public void printTilesPower()
    {
        for(Tile t : board.getTiles())
        {
            System.out.println(t);
        }
    } */

}
