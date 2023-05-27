package bg.sofia.uni.fmi.spo.wator;

import java.util.concurrent.TimeUnit;

public class Simulation {
    public static void main(String[] args) {
        int height = Constants.WORLD_HEIGHT;
        int width = Constants.WORLD_WIDTH;
        int fishCount = Constants.FISH_COUNT;
        int sharksCount = Constants.SHARKS_COUNT;
        int threadsCount = Constants.THREADS_COUNT;
        int iterations = Constants.ITERATIONS;

        System.out.printf("""
                        Height: %d
                        Width: %d
                        FishCount: %d
                        SharksCount: %d
                        ThreadsCount: %d
                        Iterations: %d
                        """,
            height, width, fishCount, sharksCount, threadsCount, iterations);

        var initialState = GridState.initialRandom(height, width, fishCount, sharksCount);
        var world = new World(initialState, threadsCount, 200);

        long startTime = System.nanoTime();

        world.updateGridState(iterations);

        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        long timeElapsedMillis = TimeUnit.NANOSECONDS.toMillis(timeElapsed);
        System.out.println("Execution time millis: " + timeElapsedMillis);
    }
}
