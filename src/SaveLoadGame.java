import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class SaveLoadGame {
    public static void createSaveFile() {

        Scanner fileName = new Scanner(System.in);
        System.out.println("Name your Save file: ");
        System.out.println("(Saves with the same name with be overwritten)");
        String saveName = fileName.nextLine();
        File saveFile = new File(saveName + ".txt");

        try {
            if (saveFile.createNewFile()) {
                System.out.println("File Created.\n" + saveName);
            } else {
                System.out.println("Save already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter writeDetails = new FileWriter(saveName + ".txt");
            writeDetails.write(GameCreation.giveTiles() + "\n"+ GameCreation.givePlayers() + "\n" + GameCreation.giveDice());
            writeDetails.close();
            System.out.println("Game Saved!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void loadGameFile(String name) {
        File loadFile = new File(name + ".txt");
        List<Integer> info = new ArrayList<>();
        try(Scanner getInfo = new Scanner(loadFile)){
            while (getInfo.hasNextLine())
            {
                info.add(getInfo.nextInt());
            }
        }catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        GameCreation.loadDetails(info.get(0), info.get(1), info.get(2));
    }
}




