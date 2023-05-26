package bg.sofia.uni.fmi.spo.wator;

public class Fish extends SeaAnimal {
    public Fish(Position position) {
        super(position, Constants.FISH_INITIAL_ENERGY, Constants.FISH_INITIAL_AGE);
    }


    @Override
    public void move(GridState gridState) {

        if (age != Constants.FISH_MAX_AGE) {
            age++;


            Position newPosition = firstAvailablePosition(gridState);
            if(newPosition != null) { // only if there is an available it is changed

                Position oldPosition = position;
                position = newPosition;

                if(age >= Constants.FISH_BREEDING_AGE) {
                    gridState.addSeaAnimal(new Fish(oldPosition));
                }
            }


        } else {
            gridState.removeAtPosition(position);
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
