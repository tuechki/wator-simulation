package bg.sofia.uni.fmi.spo.wator;

public class Fish extends SeaAnimal {
    public Fish(Position position) {
        super(position, Constants.FISH_INITIAL_ENERGY, Constants.FISH_INITIAL_AGE);
    }

    @Override
    public void move(GridState gridState) {

        if (energy != Constants.ENERGY_IMMORTAL && energy <= Constants.MIN_ENERGY || age > Constants.FISH_MAX_AGE) {
            gridState.removeAtPosition(position);
            return;
        }

        if (gridState.atPosition(position).isShark()) {
            return;
        }

        if (energy != Constants.ENERGY_IMMORTAL) {
            energy--;
        }

        Position newPosition = firstAvailablePosition(gridState);

        if (newPosition != null) {
            gridState.moveToPosition(newPosition, this);

            age++;
            if (age >= Constants.FISH_BREEDING_AGE) {
                SeaAnimal newFish = new Fish(position);
                gridState.addSeaAnimal(newFish.position(), newFish);
            }

            position = newPosition;
        }

    }


    @Override
    public Position firstAvailablePosition(GridState gridState) {

        for(var entrySet : gridState.neighboursAtPosition(position()).entrySet()) {
            if(entrySet.getValue() == null) {
                return entrySet.getKey();
            }
        }

        return null;
    }
}
