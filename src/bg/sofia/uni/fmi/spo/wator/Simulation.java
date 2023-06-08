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

        if (args.length > 0) {
            height = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            width = Integer.parseInt(args[1]);
        }
        if (args.length > 2) {
            fishCount = Integer.parseInt(args[2]);
        }
        if (args.length > 3) {
            sharksCount = Integer.parseInt(args[3]);
        }
        if (args.length > 4) {
            threadsCount = Integer.parseInt(args[4]);
        }
        if (args.length > 5) {
            iterations = Integer.parseInt(args[5]);
        }

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
        var world = new World(initialState, threadsCount, 0);

        long startTime = System.nanoTime();

        world.updateGridState(iterations);

        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        long timeElapsedMillis = TimeUnit.NANOSECONDS.toMillis(timeElapsed);
        System.out.println("Execution time millis: " + timeElapsedMillis);
    }
}
