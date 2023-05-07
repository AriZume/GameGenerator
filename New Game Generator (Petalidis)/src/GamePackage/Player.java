package GamePackage;

public class Player
{
    private final String name;
    private int currentPosition;
    private int personalRoll;
    private int queuePosition;
    private int points;
    private int lap = 0;
    private boolean isFromEnhanced = false;
    private boolean hasPlayAgainCard = false;

    public Player(String n)
    {
        this.name = n;
        this.currentPosition = 1;
    }

    public String getName()
    {
        return name;
    }

    public void setCurrentPosition(int position)
    {
        this.currentPosition = Integer.max(1,position);
    }

    public int getCurrentPosition()
    {
        return currentPosition;
    }

    public void setNewPosition(int number)
    {
        this.currentPosition += number;
    }

    public void setPersonalRoll(int roll)
    {
        this.personalRoll = roll;
    }
    public int getPersonalRoll()
    {
        return this.personalRoll;
    }

    public int getQueuePosition()
    {
        return this.queuePosition;
    }

    public void setQueuePosition(int roll)
    {
        this.queuePosition = roll;
    }
    public void setHasPlayAgainCard(boolean canPlayAgain)
    {
        this.hasPlayAgainCard = canPlayAgain;
    }

    public boolean getHasPlayAgainCard()
    {
        return this.hasPlayAgainCard;
    }

    public void setNewPoints(int newPoints) {this.points += newPoints;}

    public int getPoints() {return this.points;}

    public void setPoints(int points) {this.points = points;}

    public void increaseBy(int lap)
    {
        this.lap += lap;
    }

    public int getLap()
    {
        return this.lap;
    }

    public boolean isFromEnhanced()
    {
        return isFromEnhanced;
    }

    public void setIsFromEnhanced(boolean fromEnhanced)
    {
        isFromEnhanced = fromEnhanced;
    }
}
