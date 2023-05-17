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

            if(age >= Constants.SHARK_BREEDING_AGE) {
                gridState.addSeaAnimal(new Shark(position));
            }

            Position availablePosition = firstAvailablePosition(gridState);
            if( availablePosition != null) {
                energy++;
                position = availablePosition;
            }


        } else {
            gridState.removeAtPosition(position);
        }
    }

    @Override
    public Position firstAvailablePosition(GridState gridState) {
        return null;
    }

}
