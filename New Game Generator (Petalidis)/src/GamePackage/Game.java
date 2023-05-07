package GamePackage;

import java.util.*;

import UIPackage.Screen;
import IOPackage.GameSaver;
public class Game
{
    enum Options
    {
        OPT_ROLL(1),
        OPT_SAVE(2),
        OPT_EXIT(3);
        public final int option;

        Options(int option)
        {
            this.option = option;
        }
    }
    enum StringEnum
    {
        SQUARE_BOARD("Square"),
        CIRCULAR_BOARD("Circular");
        public final String option;
        StringEnum(String option)
        {
            this.option = option;
        }
        public String getOption()
        {
            return option;
        }

    }
    private String winnerMessage;
    private int diceAmount;
    private final ArrayList<Player> players;
    private final Board board;
    private final Screen screen = new Screen();

    // Simple constructor.
    public Game(int playerAm, int tileAm, int diceAm)
    {
        this(playerAm,tileAm,diceAm,0,"0", false);

    }

    // Constructor for game with enhanced tiles only.
    public Game(int playerAm, int tileAm, int diceAm, int enTiles)
    {
        this(playerAm,tileAm,diceAm,enTiles, "0", false);
    }

    // Constructor for game with cards only.
    public Game(int playerAm, int tileAm, int diceAm, String maxPoints)
    {
        this(playerAm,tileAm,diceAm,0,maxPoints,false);
    }

    // Constructor with both cards and enhanced tiles.
    public Game(int playerAm, int tileAm, int diceAm, int enTiles, String maxPoints)
    {
        this(playerAm,tileAm,diceAm,enTiles,maxPoints,false);
    }

    // Final constructor.
    public Game(int playerAm, int tileAm, int diceAm, int enTiles, String maxPoints, boolean isLoaded)
    {
        this.diceAmount = diceAm;
        this.board = new Board(tileAm, enTiles , maxPoints);
        this.players = new ArrayList<>();
        if(!isLoaded)
        {
            createPlayers(playerAm);
        }
    }

    public void setDiceAmount(int loadDice)
    {
        this.diceAmount = loadDice;
    }
    public Board getBoard()
    {
        return this.board;
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
        if(board.getBoardType().equals(StringEnum.SQUARE_BOARD.getOption()))
        {
            winnerMessage = screen.printWinner(getPlayerIndex(player), player.getName());
            return player.getCurrentPosition() == board.getTiles().size();
        }
        else
        {
            if(board.getLapsToWin() != 0)
            {
                winnerMessage = screen.printWinner(getPlayerIndex(player), player.getName());
                return player.getLap() == board.getLapsToWin();
            }
            else
            {
                winnerMessage = screen.printWinner(getPlayerIndex(player), player.getName(), player.getPoints());
                return player.getPoints() >= board.getMaxPoints();
            }
        }
    }
    // Moves player according to the roll and enhanced tile-if there are any.


    // Keeps track of the current position not exceeding the maximum amount of tiles
    // and not being less than 1.


    public void startGame()
    {
        GameSaver saveGame = new GameSaver();
        int previousPlayer = 0;

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
                if(board.getBoardType().equals(StringEnum.SQUARE_BOARD.getOption()))
                {
                    playerTurn = screen.printPlayerTurn(players.get(i).getName(), getPlayerIndex(players.get(i)));
                }
                else
                {
                    if(board.getLapsToWin() != 0)
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
                        Options userOption = Options.values()[option-1];

                        switch (userOption)
                        {
                            case OPT_ROLL:

                                Response movePlayer = board.movePlayer(players.get(i),getDiceRoll());
                                System.out.print(movePlayer.getMessage());

                                Response endTurn = screen.printEndTurn(players);
                                System.out.print(endTurn.getMessage());
                                break;

                            case OPT_SAVE:
                                option=0;
                                saveGame.saveProgress(players, board.getBoardType(), board.getTiles().size(), board.getMaxPoints(), board.getLapsToWin(), diceAmount, board.getEnhancedTiles());
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
                        System.out.print(inGameMenu.getMessage());
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
        for(GamePackage.Tile t : board.getTiles())
        {
            System.out.println(t);
        }
    }
    */
}
