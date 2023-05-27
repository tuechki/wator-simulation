package bg.sofia.uni.fmi.spo.wator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public record World(GridState gridState, int threadsCount, int sleepBetweenIterations) {

    public void updateGridState(long iterations) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);
        int size = gridState.height() / threadsCount;

        for (int threadNumber = 0; threadNumber < threadsCount; threadNumber++) {
            var worker = new Worker(gridState, threadNumber, size, threadsCount, iterations, sleepBetweenIterations);
            executorService.submit(worker);
        }
        awaitTerminationAfterShutdown(executorService);
    }

    private void awaitTerminationAfterShutdown(ExecutorService executorService) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1, TimeUnit.HOURS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
