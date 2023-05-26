package bg.sofia.uni.fmi.spo.wator;

import java.util.HashMap;
import java.util.Map;

public class GridState {

    public SeaAnimal atPosition(Position position) {
        return null;
    }

    public void removeAtPosition(Position position) {
        //set the position to null
    }

    public void addSeaAnimal(SeaAnimal seaAnimal) {
        //add the particular animal to the given position
    }

    public Map<Position, SeaAnimal> getNeighboursAtPosition(Position position) {

        Map<Position, SeaAnimal> neighbouringSeaAnimals = new HashMap();
        neighbouringSeaAnimals.put(northOf(position), atPosition(northOf(position)));
        neighbouringSeaAnimals.put(eastOf(position), atPosition(eastOf(position)));
        neighbouringSeaAnimals.put(southOf(position), atPosition(southOf(position)));
        neighbouringSeaAnimals.put(westOf(position), atPosition(westOf(position)));

        return neighbouringSeaAnimals;
    }


    public Position northOf(Position position) {
        return new Position(position.row() + 1, position.col());
    }

    public Position southOf(Position position) {
        return new Position(position.row() - 1, position.col());
    }

    public Position eastOf(Position position) {
        return new Position(position.row(), position.col() + 1);
    }

    public Position westOf(Position position) {
        return new Position(position.row(), position.col() - 1);
    }
}
