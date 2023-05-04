import java.util.*;

public class Game
{
    public final int OPT_ROLL = 1;
    public final int OPT_SAVE = 2;
    public final int OPT_EXIT = 3;
    private String boardType;
    private String winnerMessage;
    private int lapsToWin;
    private int diceAmount;
    private final ArrayList<Player> players;
    private final Board board;
    private final Screen screen = new Screen();

    // Constructor used to load game.
    public Game()
    {
        players = new ArrayList<>();
        this.board = new Board();
    }
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
        this.board = new Board(tileAm, enTiles , maxPoints);
        this.players = new ArrayList<>();
        createPlayers(playerAm);
    }

    public void setDiceAmount(int loadDice)
    {
        this.diceAmount = loadDice;
    }
    public Board getBoard()
    {
        return this.board;
    }
    public void setLapsToWin(int lapsToWin)
    {
        this.lapsToWin = lapsToWin;
    }
    public void setBoardType(String boardType)
    {
        this.boardType = boardType;
    }

    public void createPlayers(ArrayList<String> loadPlayers)
    {
        for (String loadPlayer : loadPlayers)
        {
            players.add(new Player(loadPlayer));
        }
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
            winnerMessage = screen.printWinner(getPlayerIndex(player), player.getName());
            return player.getCurrentPosition() == board.getTiles().size();
        }
        else
        {
            if(lapsToWin != 0)
            {
                winnerMessage = screen.printWinner(getPlayerIndex(player), player.getName());
                return player.getLap() == lapsToWin;
            }
            else
            {
                winnerMessage = screen.printWinner(getPlayerIndex(player), player.getName(), player.getPoints());
                return player.getPoints() >= board.getMaxPoints();
            }
        }
    }
    // Moves player according to the roll and enhanced tile-if there are any.
    public Response movePlayer(Player player)
    {
        player.setPersonalRoll(getDiceRoll());
        player.setNewPosition(player.getPersonalRoll());

        Response responseCheckPlayerPos = checkPlayerPosition(player);
        System.out.print(responseCheckPlayerPos.getMessage());

        Response responsePlayerStatus = board.getTiles().get(player.getCurrentPosition()-1).updatePlayerStatus(player);

        responseCheckPlayerPos = checkPlayerPosition(player);
        System.out.print(responseCheckPlayerPos.getMessage());

        // If the player is changed by an enhanced tile and lands on a card tile, execute card's updatePlayerStatus.
        // After the action reset players isFromEnhanced to false.
        if (board.getTiles().get(player.getCurrentPosition() - 1).getClass().getName().equals("CardTile"))
        {
            if (player.isFromEnhanced())
            {
                board.getTiles().get(player.getCurrentPosition() - 1).updatePlayerStatus(player);
            }
        }
        player.setIsFromEnhanced(false);

        // Might be needed if future cards move the player.
        // checkPlayerPosition(player);
        return responsePlayerStatus;
    }

    // Keeps track of the current position not exceeding the maximum amount of tiles
    // and not being less than 1.
    public Response checkPlayerPosition(Player player)
    {
        Response lapAward = new Response("\n" + "\033[32m" + "You completed a lap and are awarded " + board.getMaxPoints() / 10 + " points!" + "\033[0m" + "\n");

        if(boardType.equals("Square"))
        {
            if (player.getCurrentPosition() >= board.getTiles().size())
            {
                player.setCurrentPosition(board.getTiles().size());
            }
        }
        else
        {
            if(player.getCurrentPosition() > board.getTiles().size())
            {
                if(lapsToWin != 0)
                {
                    player.setLap(1);
                    lapAward = new Response("");

                }

                player.setCurrentPosition(player.getCurrentPosition() - board.getTiles().size());
                player.setNewPoints(board.getMaxPoints() / 10);
                return new Response(lapAward.getMessage());
            }
        }

        if (player.getCurrentPosition() <= 0)
        {
            player.setCurrentPosition(1);
        }
        return new Response("");
    }

    public void startGame()
    {
        SaveGame saveGame = new SaveGame();
        int previousPlayer = 0;
        Response winner = new Response("");

        Scanner input = new Scanner(System.in);

        decidePlayerTurn();

        boolean endGame = false;
        while (!endGame)
        {
            for(int i = 0; i < players.size(); i++)
            {
                if (players.get(previousPlayer).getHasPlayAgainCard())
                {
                    players.get(previousPlayer).setHasPlayAgainCard(false);
                    i = previousPlayer;
                }

                 Response playerTurn;
                if(boardType.equals("Square"))
                {
                     playerTurn = screen.printPlayerTurn(players.get(i).getName(), getPlayerIndex(players.get(i)));

                }
                else
                {
                    if(lapsToWin != 0)
                    {
                         playerTurn = screen.printPlayerTurnLap(players.get(i).getName(), getPlayerIndex(players.get(i)), players.get(i).getLap());
                    }
                    else
                    {
                        playerTurn = screen.printPlayerTurnPoints(players.get(i).getName(), players.get(i).getPoints(), getPlayerIndex(players.get(i)));
                    }
                }
                System.out.print(playerTurn.getMessage());
                Response descriptiveMap = screen.printDescriptiveMap(players.get(i).getCurrentPosition(), board.getTiles().size());
                Response inGameMenu = screen.printInGameMenu();
                System.out.print(descriptiveMap.getMessage() + inGameMenu.getMessage());

                int option = 0;
                do
                {
                    try
                    {
                        option = input.nextInt();

                        switch (option)
                        {
                            case OPT_ROLL:

                                Response movePlayer = movePlayer(players.get(i));
                                System.out.println(movePlayer.getMessage());

                                Response endTurn = screen.printEndTurn(players);
                                System.out.print(endTurn.getMessage());
                                break;

                            case OPT_SAVE:
                                option=0;
                                saveGame.saveProgress(players, boardType, board.getTiles().size(), board.getMaxPoints(), lapsToWin, diceAmount, board.getEnhancedTiles());

                                break;

                            case OPT_EXIT:
                                endGame = true;
                                break;

                            default:
                                System.out.print(screen.printInvalidOption().getMessage());
                                break;
                        }
                    } catch (InputMismatchException e)
                    {
                        System.out.print(screen.printInvalidOption().getMessage());
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
                    endGame = true;
                    System.out.print(winnerMessage);
                    break;
                }
                previousPlayer = i;
            }
        }
    }

    // Debug only
    /*
    public void printTilesPower()
    {
        for(Tile t : board.getTiles())
        {
            System.out.println(t);
        }
    }
    */
}
