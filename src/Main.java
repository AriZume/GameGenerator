import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("\tMENU\t\n1. Start Game\n2. Design Game\n3. Save-Load Game\n4. Show Details\n5. Manual\n6. Exit");
        Scanner input = new Scanner(System.in);
        int option = input.nextInt();
        while (option != 6)
        {
            menuOptions(option);
            System.out.println("\tMENU\t\n1. Start Game\n2. Design Game\n3. Save-Load Game\n4. Show Details\n5. Manual\n6. Exit");
            option = input.nextInt();
        }
    }
    private static void menuOptions(int input)
    {
        switch(input)
        {
            case 1:
                RunTime.movement();
                System.out.println("The game WOWOOOWOWOWOW");
                break;

            case 2:
                GameCreation.getGameDetails();
                CreateBoard.generateBoard();
                Player.giveNamesToPlayers();
                Player.showPlayerList();
                break;

            case 3:
                Scanner saveLoad = new Scanner(System.in);
                System.out.println("1. Save\n2. Load");
                int i = saveLoad.nextInt();
                switch(i)
                {
                    case 1:
                        SaveLoadGame.createSaveFile();
                        break;

                    case 2:
                        // TODO: this is just a test, remove "newSave later"
                        SaveLoadGame.loadGameFile("newSave");
                        break;
                }
                //TODO: Saving should be only available while the game is running, this is just a test
                break;
            case 4:
                System.out.println("Tiles: " + GameCreation.giveTiles() + "\nPlayers: " + GameCreation.givePlayers() + "\nDice: " + GameCreation.giveDice());
                break;
            case 5:
                ManualofGameGenerator.ShowManual(); //TODO: CHANGE TO CAPITAL O
            default:
                //
                System.out.println("Option not valid!");
                break;
        }
    }
}