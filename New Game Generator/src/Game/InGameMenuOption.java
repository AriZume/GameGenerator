package Game;

public enum InGameMenuOption
{
    ROLL(1),
    SAVE(2),
    EXIT(3);
    public final int option;
    InGameMenuOption(int option)
    {
        this.option = option;
    }
}