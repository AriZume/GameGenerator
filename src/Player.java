package MainPackage;

public class Player {
    private final String name;

    private int currentPosition;
    private int priorityRoll;

    public Player(String playerName)
    {
        name = playerName;
        currentPosition = 1;
        priorityRoll = 0;
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

    public int getPriorityRoll()
    {
        return priorityRoll;
    }
    public void setPriorityRoll(int roll)
    {
        priorityRoll = roll;
    }
    public int getTileIndex()
    {
        return currentPosition-1;
    }

}
