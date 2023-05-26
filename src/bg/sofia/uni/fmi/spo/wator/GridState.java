package bg.sofia.uni.fmi.spo.wator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class GridState {

    public static GridState initialRandom(int height, int width, int fishCount, int sharkCount) {

        var random = new Random(0);
        GridState gridState = new GridState(height, width, new HashSet<>());

        int currentFishCount = 0;
        while (currentFishCount < fishCount) {
            var randomPosition = new Position(random.nextInt(height), random.nextInt(width));

            if(gridState.isEmptyPosition(randomPosition)) {
                gridState.addSeaAnimal(new Fish(randomPosition));
                currentFishCount++;
            }
        }

        int currentSharkCount = 0;
        while (currentSharkCount < sharkCount) {
            var randomPosition = new Position(random.nextInt(height), random.nextInt(width));

            if(gridState.isEmptyPosition(randomPosition)) {
                gridState.addSeaAnimal(new Shark(randomPosition));
                currentSharkCount++;
            }
        }

        return gridState;
    }

    private Set<SeaAnimal> seaAnimals;
    private final int height;
    private final int width;

    private GridState(int height, int width, Set<SeaAnimal> seaAnimals) {
        this.height = height;
        this.width = width;
        this.seaAnimals = seaAnimals;
    }

    public SeaAnimal atPosition(Position position) {
        return seaAnimals().stream()
            .filter(seaAnimal -> seaAnimal.position().equals(position))
            .findAny()
            .orElse(null);
    }

    public boolean isEmptyPosition(Position position) {
        return atPosition(position) == null;
    }

    public void removeAtPosition(Position position) {
        seaAnimals.remove(SeaAnimal.of(position));
    }

    public void addSeaAnimal(SeaAnimal seaAnimal) {
        seaAnimals.add(seaAnimal);
    }

    public Map<Position, SeaAnimal> neighboursAtPosition(Position position) {

        Map<Position, SeaAnimal> neighbouringSeaAnimals = new HashMap<>();
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

    public Set<SeaAnimal> seaAnimals() {
        return seaAnimals;
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }
}
