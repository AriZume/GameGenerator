package UserInterface;

import Game.Player;
import Game.Players;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInputScreen
{
    UIResponse uiResponse = new UIResponse();
    Scanner input = new Scanner(System.in);

    public int declareBoardType(int min, int max)
    {
         return uiResponse.getIntegerInputValidation(input, "Select board type:\n1. Square Board\n2. Circular Board\n(Type 1 or 2): ",
                 "Invalid input. Please try again.", min ,max);
    }

    public int declarePlayerAmount(int min, int max)
    {
        return uiResponse.getIntegerInputValidation(input, "Number of players: ",
                "Player amount should be more than 2.(Max " + max + ")\nPlease try again: ", min, max);
    }

    public int declareTileAmount(int min, int max)
    {
        return uiResponse.getIntegerInputValidation(input, "Number of tiles: ",
                "Tile amount should be at least " + min + ".(Max " + max + ")\nPlease try again: ", min, max);
    }

    public int declareDiceAmount(int min, int max)
    {
        int dice = uiResponse.getIntegerInputValidation(input, "Number of dice: ",
                "Dice amount should be " + min + " or " + max + "\nPlease try again: ", min, max);
        input.nextLine(); //Collects trash
        return dice;
    }

    public String declareEnhancedTiles(String regexCondition)
    {
        return  uiResponse.getStringInputValidation(input, "Would you like enhanced tiles? (Y/N)\n(Note: Enhanced tile amount should be at least 2 less than the total tile amount): ",
                "Invalid input. Please try again.", regexCondition);
    }

    public String declareCards(String regexCondition)
    {
        return uiResponse.getStringInputValidation(input, "Would you like to have cards in your game? (Y/N)",
                "Invalid input. Please try again.", regexCondition);
    }

    public int declareLapsToWin(int min, int max)
    {
        return uiResponse.getIntegerInputValidation(input, "In how many laps would you like the game to end: ",
                "Laps should be more than " + min + ".(Max " + max + ")\nPlease try again: ", min, max);
    }

    public int declareEnhancedTileAmount(int min, int max)
    {
        int enhancedTiles = uiResponse.getIntegerInputValidation(input, "Enter the amount of enhanced tiles you would like the board to have: ",
                "Enhanced tile amount must be at least " + min + " less than the total tile amount.\nPlease try again: ", min, max);
        input.nextLine(); //Collects trash
        return enhancedTiles;
    }

    public  int declareMaxPoints(int min, int max)
    {
        int maxPoints = uiResponse.getIntegerInputValidation(input, "Number of points required to win the game: ",
                "Point amount should be at least " + min + ". (Min 500)(Max " + max + ")\nPlease try again: ", min, max);
        input.nextLine(); //Collects trash
        return maxPoints;
    }

    public int checkUserInput(int min, int max)
    {
        return uiResponse.getIntegerInputValidation(input, "", uiResponse.createInvalidOptionResponse().getMessage(), min, max);
    }

    public void declarePlayerNames(Players players, int playerAmount)
    {
        Scanner scanner = new Scanner(System.in);

        String prompt ="Enter player names:\n";
        String string = "Player : ";
        String errorMessage = "\nInvalid name. Please try again: ";

        System.out.print(prompt);
        for(int i = 0; i < playerAmount; i++)
        {
            System.out.print(string);
            String name;
            do {
                name = scanner.nextLine();
                if (name.isBlank()) {
                    System.out.print(errorMessage);
                }
            } while (name.isBlank());
            players.setPlayerNames(players.getPlayers().get(i), name);
        }
    }
}
