package Main;

public enum MainMenuOption
{
    DESIGN_PLAY(1),
    QUICK_GAME(2),
    LOAD(3),
    HELP(4),
    EXIT(5);

    public final int value;
    MainMenuOption(int value)
    {
        this.value = value;
    }
    public int getValue()
    {
        return value;
    }
}
