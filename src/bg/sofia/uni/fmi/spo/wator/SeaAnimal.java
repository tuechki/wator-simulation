package bg.sofia.uni.fmi.spo.wator;

import java.util.Objects;

public abstract class SeaAnimal {

    public static SeaAnimal of(Position position) {
        return new SeaAnimal(position, 0, 0) {
            @Override
            public void move(GridState gridstate) {}

            @Override
            public Position firstAvailablePosition(GridState gridState) {
                return null;
            }
        };
    }
    protected Position position;
    protected int energy;
    protected int age;


    public SeaAnimal(Position position, int energy, int age) {
        this.position = position;
        this.energy = energy;
        this.age = age;
    }

    public abstract void move(GridState gridstate);

    public abstract Position firstAvailablePosition(GridState gridState);

    public Position position() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isShark() {
       return this instanceof Shark;
    }

    public boolean isFish() {
        return this instanceof Fish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeaAnimal seaAnimal)) return false;
        return position.equals(seaAnimal.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
