package bg.sofia.uni.fmi.spo.wator;

import javax.swing.*;
import java.awt.*;

public class GuiSimulation extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Wa-Tor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GuiSimulation());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private final World world;

    public GuiSimulation() {
        var initialState = GridState.initialRandom(Constants.WORLD_HEIGHT, Constants.WORLD_WIDTH, Constants.FISH_COUNT, Constants.SHARKS_COUNT);
        world = new World(initialState, Constants.THREADS_COUNT, Constants.FRAME_INTERVAL_MILLIS);

        new Timer(Constants.FRAME_INTERVAL_MILLIS, e -> repaint()).start();
        new Thread(() -> world.updateGridState(Constants.ITERATIONS)).start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Constants.WORLD_WIDTH * Constants.CELL_SIZE, Constants.WORLD_HEIGHT * Constants.CELL_SIZE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Constants.OCEAN_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());

        GridState gridState = world.gridState();
        Position position;
        for (int row = 0; row < gridState.height(); row++) {
            for (int col = 0; col < gridState.width(); col++) {
                position = new Position(row, col);

                if(!gridState.isEmptyPosition(position)) {
                    paintCell(g, gridState.atPosition(position), position);
                }
            }
        }
    }

    private void paintCell(Graphics g, SeaAnimal seaAnimal, Position position) {
        var color = Constants.OCEAN_COLOR;

        if (seaAnimal != null && seaAnimal.isFish()) {
            color = Constants.FISH_COLOR;
        } else if (seaAnimal != null && seaAnimal.isShark()) {
            color = Constants.SHARK_COLOR;
        }
        g.setColor(color);

        int visualPositionCol = position.col() * Constants.CELL_SIZE;
        int visualPositionRow = position.row() * Constants.CELL_SIZE;
        g.fillRect(visualPositionCol, visualPositionRow, Constants.CELL_SIZE, Constants.CELL_SIZE);
    }
}