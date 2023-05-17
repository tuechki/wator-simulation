package bg.sofia.uni.fmi.spo.wator;

public enum PositionDirection {
    NORTH("North"),
    EAST("East"),
    SOUTH("South"),
    WEST("West");

    public final String direction;

    private PositionDirection(String direction) {
        this.direction = direction;
    }
}
