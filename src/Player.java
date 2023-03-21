package MainPackage;

public class Player {
    private String name;
    private int currentPosition;
    public Player(String n)
    {
        name = n;
        currentPosition = 1;
    }
    public String getName()
    {
        return name;
    }

    public int getCurrentPosition()
    {
        return currentPosition;
    }

    public void setCurrentPosition(int curPos, int roll)
    {
        currentPosition = curPos + roll;
    }


}
