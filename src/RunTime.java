import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RunTime extends Player{

    public static void movement()
    {
        Scanner option = new Scanner(System.in);
        //List<String> players = Player.getPlayerList();
        List<String> boardTiles = CreateBoard.getTilesList();
        // System.out.println(players);
        for (Player c : listOfPlayers) {
            boardTiles.add(c.getPosition(), "1");
        }
        //int player;
        while(boardTiles.get(boardTiles.size() - 1) != "0"){
            for (int player = 0; player < GameCreation.givePlayers(); player++) {
                System.out.println(boardTiles);
                System.out.println("\tMENU\t\n1. Roll\n2. Save-Load Game\n3. Exit");
                int playerOption = option.nextInt();
                inGameMenuOption(playerOption);

                int playerRoll = CreateDice.returnNumberOfDice();
                boardTiles.add(listOfPlayers.indexOf(playerRoll), "1");
                // Clears previous Position
                int previousIndex =- playerRoll;
                boardTiles.add(previousIndex, "0");
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
        }

    }

}
