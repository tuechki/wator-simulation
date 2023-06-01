package bg.sofia.uni.fmi.spo.wator;

import java.awt.*;

public class Constants {



    public static final int ENERGY_IMMORTAL = -1;
    public static final int FISH_INITIAL_ENERGY = ENERGY_IMMORTAL;
    public static final int FISH_INITIAL_AGE = 0;
    public static final int FISH_MAX_AGE = 10;
    public static final int FISH_BREEDING_AGE = 3;


    public static final int SHARK_INITIAL_ENERGY = 10;
    public static final int SHARK_INITIAL_AGE = 0;
    public static final int SHARK_MAX_AGE = 30;
    public static final int SHARK_BREEDING_AGE = 15;

    public static final int MIN_ENERGY = 0;


    public static final int WORLD_HEIGHT = 100;
    public static final int WORLD_WIDTH = 200;
    public static final int FISH_COUNT = 50;
    public static final int SHARKS_COUNT = 50;
    public static final int THREADS_COUNT = 8;
    public static final int ITERATIONS = 1000;



    public static final int FRAME_INTERVAL_MILLIS = 40;
    public static final int CELL_SIZE = 1;
    public static final Color OCEAN_COLOR = new Color(33, 33, 33);
    public static final Color FISH_COLOR = new Color(102, 187, 106);
    public static final Color SHARK_COLOR = new Color(33, 150, 243);
}
