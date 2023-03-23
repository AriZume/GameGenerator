package MainPackage;

public class Player {
    private final String name;

    private int currentPosition;

    public Player(String playerName)
    {
        name = playerName;
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
