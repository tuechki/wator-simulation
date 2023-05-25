package bg.sofia.uni.fmi.spo.wator;

public class Fish extends SeaAnimal {
    public Fish(Position position) {
        super(position, Constants.FISH_INITIAL_ENERGY, Constants.FISH_INITIAL_AGE);
    }


    @Override
    public void move(GridState gridState) {

        if (age != Constants.FISH_MAX_AGE) {
            age++;

            if(age >= Constants.FISH_BREEDING_AGE) {
                gridState.addSeaAnimal(new Fish(position));
            }

            Position availablePosition = firstAvailablePosition(gridState);
            if( availablePosition != null) { // only if there is an available it is changed
                position = availablePosition;
            }

        } else {
            gridState.removeAtPosition(position);
        }

    }


    @Override
    public Position firstAvailablePosition(GridState gridState) {

        if(gridState.atPosition(northPosition()) == null) {
            return northPosition();
        }

        if(gridState.atPosition(eastPosition()) == null) {
            return eastPosition();
        }

        if(gridState.atPosition(southPosition()) == null) {
            return southPosition();
        }

        if(gridState.atPosition(westPosition()) == null) {
            return westPosition();
        }
    }
}
