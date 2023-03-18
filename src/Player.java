public class Player {
    static String name;
    static int position;
    Player(String name,int position){
        this.name = name;
        this.position = position;
    }
    public static void setName(String newName) {
        name = newName;
    }
    public static String getName() {
        return name;
    }

    public static int getPosition() {
        return position;
    }
    public void setPosition(int newPosition) {
        position = newPosition;
    }
    public static void  printName() {
        System.out.println(name);
    }
    public static void  printPosition() {
        System.out.println(position);
    }
}
