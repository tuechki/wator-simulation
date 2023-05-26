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
}
