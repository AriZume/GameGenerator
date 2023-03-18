import java.util.Random;
public class CreateDice {
    static int total=0;
    public static void diceRandomizer(int numberOfDice){
        Random ranNum = new Random();
        for (int i = 0; i < numberOfDice; i++) {
            int randomNumber = ranNum.nextInt(6) + 1;
            total = total + randomNumber;
            System.out.print("You rolled: "+ randomNumber +"\n");
        }
        if(numberOfDice==2){
            System.out.println("Total: " + total);
        }
    }
    public static int returnNumberOfDice(){
        return total;
    }

}
