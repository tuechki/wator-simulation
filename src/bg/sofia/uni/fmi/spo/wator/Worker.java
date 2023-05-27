package bg.sofia.uni.fmi.spo.wator;

public record Worker(GridState gridState, int threadNumber, int size, int threadsCount, long totalIterations, int sleepBetweenIterations) implements Runnable {
    @Override
    public void run() {

        for (int iteration = 0; iteration < totalIterations; iteration++) {
            runSingleIteration();
            try {
                Thread.sleep(sleepBetweenIterations);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void runSingleIteration() {
        int beginRow = threadNumber() * size();
        int endRow = beginRow + size() - 1;
        if (threadNumber() == threadsCount() - 1) {
            endRow = gridState().height() - 1;
        }

        for (int currentRow = beginRow; currentRow <= endRow; currentRow++) {
            int nextRow = (endRow + 1) % gridState().height();

            if (currentRow == beginRow) {
                gridState.lockRow(beginRow);
            } else if (currentRow == endRow) {
                gridState.waitForUpdate(nextRow);
                gridState.lockRow(nextRow);
            }

            int currentRowForUpdate = currentRow;
            gridState.seaAnimals().stream()
                .filter(seaAnimal -> seaAnimal.position.row() == currentRowForUpdate)
                .forEach(seaAnimal -> seaAnimal.move(gridState));

            if (currentRow == beginRow) {
                gridState.unlockRow(beginRow);
                gridState.signalUpdated(beginRow);
            } else if (currentRow == endRow) {
                gridState.unlockRow(nextRow);
            }
        }
    }
}
