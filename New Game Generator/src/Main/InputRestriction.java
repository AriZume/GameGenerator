package Main;

public enum InputRestriction
{
    MAIN_MENU(1,5),
    GAME_MENU(1,3),
    BOARD_TYPE(1,2),
    PLAYER_AMOUNT(2,10),
    TILE_AMOUNT(10,150),
    DICE_AMOUNT(1,2),
    LAPS_TO_WIN(2,15),
    MAX_POINTS(500,10000),
    ENHANCED_TILE_AMOUNT(1,2);

    public final int min, max;
    InputRestriction(int min, int max)
    {
        this.min = min;
        this.max = max;
    }
    public int getMin()
    {
        return this.min;
    }
    public int getMax()
    {
        return this.max;
    }
}
