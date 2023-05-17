package bg.sofia.uni.fmi.spo.wator;

public abstract class SeaAnimal {
    protected Position position;
    protected int energy;
    protected int age;

    public SeaAnimal(Position position, int energy, int age) {
        this.position = position;
        this.energy = energy;
        this.age = age;
    }

    public abstract void move(GridState gridstate, Random random);

    public abstract Position getFirstAvailablePos();

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getNorthPosition() {
        return new Position(getPosition().row() + 1, getPosition().col());
    }

    public Position getSouthPosition() {
        return new Position(getPosition().row() - 1, getPosition().col());
    }

    public Position getEastPosition() {
        return new Position(getPosition().row(), getPosition().col() + 1);
    }

    public Position getWestPosition() {
        return new Position(getPosition().row(), getPosition().col() - 1);
    }

    public boolean isShark() {
       return this instanceof Shark;
    }

    public boolean isFish() {
        return this instanceof Fish;
    }
}
