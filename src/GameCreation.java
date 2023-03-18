import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class GameCreation {
    static int  getTiles;
    static int  getPlayers;
    static int  getDice;
    public static void getGameDetails()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Input number of tiles: ");
         getTiles = input.nextInt();
        while(getTiles<1)
        {
            System.out.print("The number of tiles should be bigger than one!\n Insert number of tiles: ");
            getTiles = input.nextInt();
        }
        System.out.print("Input number of players: ");
         getPlayers = input.nextInt();
        while(getPlayers<2)
        {
            System.out.print("The number of players should be bigger than two!\n Insert number of players: ");
            getPlayers = input.nextInt();
        }
        System.out.print("Input number of Dice: ");
         getDice = input.nextInt();
        while(getDice>2 || getDice<1)
        {
            System.out.print("The number of dice should be between one and two!\n Insert number of dice: ");
            getDice = input.nextInt();
        }
    }
    public static int giveTiles(){
        return getTiles;
    }
    public static int givePlayers(){
        return getPlayers;
    }
    public static int giveDice(){
        return getDice;
    }
    
    public static void loadDetails(int tiles, int players, int dice)
    {
        getTiles = tiles;
        getPlayers = players;
        getDice = dice;
    }
}

//TODO: FUNCTION THAT BRINGS THE ELEMENTS FROM TILES PLAYERS ETC.