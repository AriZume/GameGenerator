package Main;

public class EnumClass
{
    public enum InputRestriction
    {
        MAIN_MENU(1,4),
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

    public enum MainMenuOption
    {
        DESIGN_PLAY(1),
        LOAD(2),
        HELP(3),
        EXIT(4);

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
    public enum BoardType
    {
        SQUARE_BOARD(1,"Square"), CIRCULAR_BOARD(2,"Circular");

        public final int value;
        public final String description;
        BoardType(int value, String description)
        {
            this.value = value;
            this.description = description;
        }
        public int getValue() {
            return this.value;
        }
        public String getDescription() {
            return this.description;
        }
    }

    public enum SaveFile
    {
        SAVE_FILE("./gameProgress.txt", "gameProgress.txt");
        public final String path;
        public final String name;
        SaveFile(String path, String name)
        {
            this.path = path;
            this.name = name;
        }
        public String getPath() {
            return this.path;
        }
        public String getName() {
            return this.name;
        }
    }
}
