import java.util.*;

public class Screen
{
    private final String resetColor = "\033[0m";
    private final String[] colors =
            {
            "\033[33m", // Yellow
            "\033[38;2;255;105;180m", //Pink
            "\033[34m", // Blue
            "\033[35m", // Purple
            "\u001B[38;5;208m", // Orange
            "\033[36m", // Cyan
            "\u001B[38;5;75m", // Indigo
            "\u001B[38;5;165m", // Violet
            "\u001b[38;2;245;245;220m", // Beige
            "\u001b[38;2;139;69;19m" // Brown
            };
    private String message;
    public void printMainMenu(int optDesignPlay, int optLoad, int optHelp, int optExit)
    {
        // Change the color to rose gold
        System.out.print("\033[38;2;255;192;203m");

        System.out.println("========================");
        System.out.println("\t\t  MENU");
        System.out.println("========================");

        // Reset the console color
        System.out.print(resetColor);
        System.out.println(optDesignPlay + ". Design Game and Play\n" + optLoad + ". Load Game\n" + optHelp + ". Help\n" + optExit + ". Exit");
    }

    public void printDesignGameTitle()
    {
        // Change the color to rose gold
        System.out.print("\033[38;2;255;192;203m");
        System.out.println("========================");
        System.out.println("\t  GAME DESIGN");
        System.out.println("========================");

        // Reset the console color
        System.out.print(resetColor);
    }

    public void printDescriptiveMap(int playerPos, int boardSize)
    {
        System.out.println("\nYou are placed on tile " + playerPos + " of " + boardSize);
    }

    public void printPlayerTurn(String playerName, int playerIndex)
    {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("It's " + colors[playerIndex] + playerName + resetColor + "'s turn.\n(Player " + (playerIndex + 1) + ")");
    }

    public void printPlayerTurnLap(String playerName, int playerIndex, int lap)
    {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("It's " + colors[playerIndex] + playerName + resetColor + "'s turn.\n(Player " + (playerIndex + 1) + ") - Lap " + lap);
    }

    public void printPlayerTurnPoints(String playerName, int playerPoints, int playerIndex)
    {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("It's " + colors[playerIndex] + playerName + resetColor + "'s turn.\n(Player " + (playerIndex + 1) + "), you have " + playerPoints + " points.");
    }

    public void printInGameMenu()
    {
        System.out.println("\n1. Roll Dice\n2. Save (Unavailable)\n3. Exit");
    }

    public void printEndTurn(ArrayList<Player> players)
    {
        System.out.println("End of turn.\n");
        for (Player p : players)
        {
            System.out.println(p.getName() + " is on tile " + p.getCurrentPosition());
        }
    }

    public String printWinner(int playerIndex, String playerName)
    {
        message = ("\n-----------------------------------------------------------------------\n"
        // Change the console text color to purple
                + "\033[35m"
                + "Player " + (playerIndex + 1) + " (" + colors[playerIndex] + playerName + resetColor + ") wins! Congratulations!"
        // Reset the console color
                + resetColor + "\n");
        return message;
    }

    public String printWinner(int playerIndex, String playerName, int playerPoints)
    {
       message = ("\n-----------------------------------------------------------------------"
        // Change the console text color to purple/magenta
                +"\033[35m"
                + "Player " + (playerIndex + 1) + " (" + colors[playerIndex] + playerName + resetColor + ") wins with a total of "+ playerPoints+ " points! Congratulations!"
        // Reset the console color
                + resetColor + "\n");
        return message;
    }

    public int getInputIntegerValidation(Scanner input, String promptMessage, String errorMessage, int min, int max)
    {
        int number;
        System.out.print(promptMessage);
        while (true) {
            try {
                number = input.nextInt();
                if (number < min || number > max) {
                    System.out.print(errorMessage);
                } else
                {
                    break; // Exit loop when condition is met
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please try again: ");
                input.nextLine();
            }
        }
        return number;
    }

    public String getInputStringValidation(Scanner input, String promptMessage, String errorMessage, String regexCondition)
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
