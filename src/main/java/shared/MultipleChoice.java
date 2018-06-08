package shared;

public enum MultipleChoice {
    A(0), B(1), C(2), D(3);
    private final int value;

    MultipleChoice(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
