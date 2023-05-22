package UserInterface;

import java.util.*;

import Game.Player;
import Game.Players;
import Game.BoardType;

public class UIResponse
{
    private final String resetColor = "\033[0m";
    private final String[] colors =
            {
            "\033[34m", // Blue
            "\033[33m", // Yellow
            "\033[38;2;255;105;180m", //Pink
            "\u001B[38;5;208m", // Orange
            "\033[35m", // Purple
            "\033[36m", // Cyan
            "\u001B[38;5;75m", // Indigo
            "\u001B[38;5;165m", // Violet
            "\u001b[38;2;245;245;220m", // Beige
            "\u001b[38;2;139;69;19m" // Brown
            };

    public Response createMainMenuResponse(int optDesignPlay, int optQuickGame,int optLoad, int optHelp, int optExit) {
        // Change the color to rose gold
        return new Response("\033[38;2;255;192;203m" + "\n========================\n" + "\t\t  MENU" + "\n========================" +
        resetColor + "\n"+ optDesignPlay + ". Design Game and Play\n" + optQuickGame + ". Quick Game\n" + optLoad + ". Load Game\n" + optHelp + ". Help\n" + optExit + ". Exit\n");
    }

    public Response createDesignGameTitleResponse()
    {
        // Change the color to rose gold
        return new Response("\033[38;2;255;192;203m\n"+"========================\n"+"\t  GAME DESIGN\n"+"========================"+ resetColor+"\n");
    }

    public Response createDescriptiveMapResponse(int playerPos, int boardSize)
    {
        return new Response("\nYou are placed on tile " + playerPos + " of " + boardSize+"\n");
    }

    public Response createPlayerTurnResponse(String boardType, int lapsToWin, Player player, int playerIndex)
    {
        if(boardType.equals(BoardType.SQUARE_BOARD.getDescription()))
        {
            return new Response("-----------------------------------------------------------------------\n"+ "It's " +
                colors[playerIndex] + player.getName() + resetColor + "'s turn.\n(Player " + (playerIndex + 1) + ")\n");
        }
        else
        {
            if(lapsToWin != 0)
            {
                return new Response("-----------------------------------------------------------------------\n"+"It's " +
                        colors[playerIndex] + player.getName() + resetColor + "'s turn.\n(Player " + (playerIndex + 1) + ") - Lap " + player.getLap() + "\n");            }
            else
            {
                return new Response("-----------------------------------------------------------------------\n"+"It's " + colors[playerIndex]
                        + player.getName() + resetColor + "'s turn.\n(Player " + (playerIndex + 1) + "), you have " + player.getPoints() + " points.\n");            }
        }
    }

    public Response createInGameResponse()
    {
        return new Response("\n1. Roll Dice\n2. Save Game\n3. Exit\n");
    }

    public Response createEndTurnResponse(ArrayList<Player> players,String boardType)
    {
        StringBuilder endOfTurn = new StringBuilder("\nEnd of turn.\n") ;
        for (Player p : players)
        {
            endOfTurn.append(p.getName());
            if(boardType.equals(BoardType.CIRCULAR_BOARD.getDescription()))
            {
                endOfTurn.append(" (");
                endOfTurn.append(p.getPoints());
                endOfTurn.append(" points) ");
            }
            endOfTurn.append(" is on tile ");
            endOfTurn.append(p.getCurrentPosition());

            endOfTurn.append("\n");
        }
        return new Response(endOfTurn.toString());
    }

    public Response createWinnerResponse(int playerIndex, String playerName)
    {
        return new Response("\n-----------------------------------------------------------------------\n"
                + "Player " + (playerIndex + 1) + " (" + colors[playerIndex] + playerName + resetColor + ") wins! Congratulations!"
                // Reset the console color
                + resetColor + "\n");
    }

    public Response createHelpResponse()
    {
        return new Response ("\nHello and welcome to the latest version of our Game Generator.\n" +
                "To start a new game please select the fist option below.\n" +
                "If you have the progress of your last game saved please select the second option below to continue.\n" +
                "If you want to exit the game please select the fourth option below.\n" +
                "HAVE FUN! ^_^\n" +
                "Please donate to our OnlyFans ;)");
    }

    public Response createInvalidOptionResponse()
    {
        return new Response("Invalid input. Please try again: ");
    }

    public Response createDiceRollsResponse(ArrayList<Integer> diceRolls)
    {
        StringBuilder  diceResponse = new StringBuilder("You rolled: ");
        for(int i = 0; i < diceRolls.size(); i++)
        {
            diceResponse.append(diceRolls.get(i));
            if(!((i+1) >= diceRolls.size()))
            {
                diceResponse.append(" and ");
            }
        }
        return new Response(diceResponse.toString());
    }

    public Response createPlayerPriorityResponse(Players players,int diceAmount)
    {
        StringBuilder priorityResponse = new StringBuilder("\nLet's see who's starting first!\n");
        for (Player player : players.getPlayers())
        {
            priorityResponse.append(player.getName());
            if (diceAmount==2)
            {
                priorityResponse.append(" rolled a total of: ");
                priorityResponse.append(player.getQueuePosition());
            }
            else
            {
                priorityResponse.append(" rolled: ");
                priorityResponse.append(player.getQueuePosition());
            }
            priorityResponse.append("\n");
        }
        return new Response(priorityResponse.toString());
    }

    public int getIntegerInputValidation(Scanner input, String promptMessage, String errorMessage, int min, int max)
    {
        int number;
        System.out.print(promptMessage);
        while (true) {
            try {
                number = input.nextInt();
                if (number < min || number > max)
                {
                    System.out.print(errorMessage);
                } else
                {
                    break; // Exit loop when condition is met
                }
            } catch (InputMismatchException e)
            {
                System.out.print(createInvalidOptionResponse().message());
                input.nextLine();
            }
        }
        return number;
    }

    public String getStringInputValidation(Scanner input, String promptMessage, String errorMessage, String regexCondition)
    {
        String userInput;
        System.out.print(promptMessage);
        do
        {
            userInput = input.nextLine();
            if(!userInput.matches(regexCondition))
            {
                System.out.print(errorMessage);
            }
        }while(!userInput.matches(regexCondition) && !userInput.isEmpty());
        return userInput;
    }
}
