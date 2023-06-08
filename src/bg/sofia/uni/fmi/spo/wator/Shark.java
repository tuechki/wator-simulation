package bg.sofia.uni.fmi.spo.wator;

public class Shark extends SeaAnimal {
    public Shark(Position position) {
        super(position, Constants.SHARK_INITIAL_ENERGY, Constants.SHARK_INITIAL_AGE);
    }

    @Override
    public void move(GridState gridState) {

        if (energy != Constants.ENERGY_IMMORTAL && energy <= Constants.MIN_ENERGY || age > Constants.SHARK_MAX_AGE) {
            gridState.removeAtPosition(position);
            return;
        }

        Position newPosition = firstAvailablePosition(gridState);
        if (newPosition != null) {
            if (gridState.atPosition(newPosition) != null && gridState.atPosition(newPosition).isFish()) {
                energy++;
            } else {
                energy--;
            }
            gridState.moveToPosition(newPosition, this);

            age++;
            if (age >= Constants.SHARK_BREEDING_AGE) {
                SeaAnimal newShark = new Shark(position);
                gridState.addSeaAnimal(newShark.position(), newShark);
            }
            position = newPosition;
        }

    }

    @Override
    public Position firstAvailablePosition(GridState gridState) {
        for(var entrySet : gridState.neighboursAtPosition(position()).entrySet()) {
            if(entrySet.getValue() != null && entrySet.getValue().isFish()) {
                return entrySet.getKey();
            }
        }

        for(var entrySet : gridState.neighboursAtPosition(position()).entrySet()) {
            if(entrySet.getValue() == null) {
                return entrySet.getKey();
            }
        }

        return null;
    }

}
