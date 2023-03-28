package MainPackage;

public class Player {
    private final String name;

    private int currentPosition;
    private int priorityRoll;

    // CONSTRUCTOR
    public Player(String playerName)
    {
        name = playerName;
        currentPosition = 1;
        priorityRoll = 0;
    }

    // METHODS
    public String getName()
    {
        return name;
    }

    //  --------------
    public int getCurrentPosition()
    {
        return currentPosition;
    }

    //  --------------
    public void setCurrentPosition(int roll)
    {
        currentPosition +=  roll;
    }

    //  --------------
    public void resetPosition()
    {
        currentPosition = 0;
    }

    //  --------------
    public int getPriorityRoll()
    {
        return priorityRoll;
    }
    //  --------------
    public void setPriorityRoll(int roll)
    {
        priorityRoll = roll;
    }

    //  --------------
    public int getTileIndex()
    {
        return currentPosition-1;
    }

}
