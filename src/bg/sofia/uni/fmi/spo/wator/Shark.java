package bg.sofia.uni.fmi.spo.wator;

public class Shark extends SeaAnimal {
    public Shark(Position position) {
        super(position, Constants.SHARK_INITIAL_ENERGY, Constants.SHARK_INITIAL_AGE);
    }

    @Override
    public void move(GridState gridState) {

        if (energy != Constants.MIN_ENERGY && age != Constants.SHARK_MAX_AGE) {
            age++;
            energy--;

            Position newPosition = firstAvailablePosition(gridState);
            if(newPosition != null) {

                Position oldPosition = position;
                position = newPosition;
                energy++;

                if(age >= Constants.SHARK_BREEDING_AGE) {
                    gridState.addSeaAnimal(new Shark(oldPosition));
                }

            }


        } else {
            gridState.removeAtPosition(position);
        }
    }

    @Override
    public Position firstAvailablePosition(GridState gridState) {
        //check if fish is nearby
        for(var entrySet : gridState.getNeighboursAtPosition(position()).entrySet()) {
            if(entrySet.getValue().isFish()) {
                return entrySet.getKey();
            }
        }

        //if there are no fish neighbouring the shark check for empty space
        for(var entrySet : gridState.getNeighboursAtPosition(position()).entrySet()) {
            if(entrySet.getValue() == null) {
                return entrySet.getKey();
            }
        }

        return null;
    }

}
