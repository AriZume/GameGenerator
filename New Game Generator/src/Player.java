public class Player
{
    private String name;
    private int currentPosition;

    public Player()
    {
        this.name = "";
        this.currentPosition = 1;
    }

    public Player(String n)
    {
        this.name = n;
        this.currentPosition = 1;
    }

    public void setName(String n)
    {
        this.name = n;
    }

    public String getName()
    {
        return name;
    }

    public void setCurrentPosition(int position)
    {
        this.currentPosition = position;
    }

    public int getCurrentPosition()
    {
        return currentPosition;
    }

    public void setPositionAfterRoll(int roll)
    {
        this.currentPosition += roll;
    }
}
