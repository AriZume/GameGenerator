import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RunTime{

    public static void movement()
    {

        Scanner option = new Scanner(System.in);
        //List<String> players = Player.getPlayerList();
        List<String> boardTiles = CreateBoard.getTilesList();
        // System.out.println(players);
        for (Player c : Player.listOfPlayers) {
            boardTiles.set(c.getPosition(), String.valueOf(c.getPlayerTag()));
        }
        //int player;
        boolean gameWon = false;
        while(boardTiles.get(boardTiles.size() - 1) != "0"){
            for (Player c : Player.listOfPlayers)
            {
                String tag = String.valueOf(c.getPlayerTag());
                //Menu
                System.out.println("\n\nPlayer"+ tag + ": " + c.getName());
                System.out.println("\n" + boardTiles);
                System.out.println("\tMENU\t\n1. Roll\n2. Save-Load Game\n3. Exit");
                int playerOption = option.nextInt();
                inGameMenuOption(playerOption);
                //c.setPrevPos(c.getPosition());
                int playerRoll = CreateDice.returnNumberOfDice();
                int previousIndex = 0;
                if((boardTiles.size() - 1) - c.getPosition() <= playerRoll){
                    System.out.println("Player"+ tag + ": " +c.getName() + " Won the game!");
                    boardTiles.set(boardTiles.size() - 1, tag);
                    c.setPosition(playerRoll + c.getPosition());
                    // Clears previous Position
                    previousIndex = rollCheck(c.getPosition(),playerRoll);
                    boardTiles.set(previousIndex, "0");
                    gameWon = true;
                }
                else {
                    c.setPosition(playerRoll + c.getPosition());
                    boardTiles.set(c.getPosition(), tag);
                    // Clears previous Position
                    previousIndex=rollCheck(c.getPosition(),playerRoll);
                    boardTiles.set(previousIndex, "0");
                }
                if(gameWon == true)
                {
                    boardTiles.set(boardTiles.size() - 1, tag);
                    break;
                }
            }
            if(gameWon == true)
            {
                break;
            }
        }
    }

    private static void inGameMenuOption(int input){

        switch(input){
            case 1:
                CreateDice.diceRandomizer(GameCreation.giveDice());
                break;
            case 2:
                break;
            case 3:

                break;
            default:
                System.out.println("NO");
                break;
        }

    }
    private static int rollCheck(int currentPosition, int plrRoll)
    {
        if(currentPosition > plrRoll)
        {
            return currentPosition - plrRoll;
        }else
        {
            return plrRoll - currentPosition;
        }
    }

}
