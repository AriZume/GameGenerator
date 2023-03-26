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

    public void setCurrentPosition(int roll)
    {
        currentPosition +=  roll;
    }

    public int getTileIndex()
    {
        return currentPosition-1;
    }

}
