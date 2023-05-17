package bg.sofia.uni.fmi.spo.wator;

public enum PositionDirection {
    NORTH("North"),
    EAST("East"),
    SOUTH("South"),
    WEST("West");

    public final String label;

    private (String label) {
        this.label = label;
    }
}
