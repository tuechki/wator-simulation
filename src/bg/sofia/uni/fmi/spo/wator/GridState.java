package bg.sofia.uni.fmi.spo.wator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GridState {

    public static GridState initialRandom(int height, int width, int fishCount, int sharkCount) {

        var random = new Random(0);
        GridState gridState = new GridState(height, width, ConcurrentHashMap.newKeySet());

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

//    private Set<SeaAnimal> seaAnimals;
    private final Map<Integer, SeaAnimal> seaAnimals;
    private final int height;
    private final int width;
    private final ReentrantLock[] rowLocks;
    private final Semaphore[] isRowUpdated;

    public GridState(int height, int width, Map<Integer, SeaAnimal> seaAnimals) {
        this.height = height;
        this.width = width;
        this.seaAnimals = seaAnimals;
        this.rowLocks = Stream.generate(ReentrantLock::new).limit(height).toArray(ReentrantLock[]::new);
        this.isRowUpdated = Stream.generate(() -> new Semaphore(0)).limit(height).toArray(Semaphore[]::new);
    }

    public void lockRow(int row) {
        rowLocks[row].lock();
    }

    public void unlockRow(int row) {
        rowLocks[row].unlock();
    }

    public void waitForUpdate(int row) {
        try {
            isRowUpdated[row].acquire();
        } catch (InterruptedException e) {
            //TODO: Throw a meaningful exception
            throw new RuntimeException(e);
        }

    }

    public void signalUpdated(int row) {
        isRowUpdated[row].release();
    }

    public SeaAnimal atPosition(Position position) {
        return seaAnimals().stream()
            .filter(seaAnimal -> seaAnimal.position().equals(position))
            .findAny()
            .orElse(null);
    }

    public Set<SeaAnimal> atRow(int row) {
        return seaAnimals().stream()
            .filter(seaAnimal -> seaAnimal.position().row() == row)
            .collect(Collectors.toSet());
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
        if(position.row() == lastRow()) {
            return new Position(firstRow(), position.col());
        }
        return new Position(position.row() + 1, position.col());
    }

    public Position southOf(Position position) {
        if(position.row() == firstRow()) {
            return new Position(lastRow(), position.col());
        }
        return new Position(position.row() - 1, position.col());
    }

    public Position eastOf(Position position) {
        if(position.col() == lastCol()) {
            return new Position(position.row(), firstCol());
        }
        return new Position(position.row(), position.col() + 1);
    }

    public Position westOf(Position position) {
        if(position.col() == firstCol()) {
            return new Position(position.row(), lastCol());
        }
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

    public int firstRow() {
        return 0;
    }

    public int lastRow() {
        return height() - 1;
    }

    public int firstCol() {
        return 0;
    }

    public int lastCol() {
        return width() - 1;
    }
}
