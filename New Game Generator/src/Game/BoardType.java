package Game;

public enum BoardType {
    SQUARE_BOARD(1, "Square"), CIRCULAR_BOARD(2, "Circular");

    public final int value;
    public final String description;

    BoardType(int value, String description) {
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
