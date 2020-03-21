package neon.utils;

import java.util.Random;

/**
 * A class used to generate random values.
 */
public class RandomUtils {
    private static final Random rand = new Random();

    /**
     * Generates a random integer.
     *
     * @param min The minimum value.
     * @param max The maximum value.
     * @return a random integer within the specified range.
     */
    public static int getInt(int min, int max) {
        return (int) Math.floor(getDouble(min, (double) max + 1));
    }

    /**
     * Generates a random double.
     *
     * @param min The minimum value.
     * @param max The maximum value.
     * @return a random double within the specified range.
     */
    public static double getDouble(double min, double max) {
        return min + (max - min) * rand.nextDouble();
    }

    /**
     * Generates a random integer using the weights. Indexes with a larger value
     * weight will be more likely to be chosen by the algorithm.
     *
     * @param weights The list of weights.
     * @return A random index within the bounds of the list.
     */
    public static Integer getRandomUsingWeights(double[] weights) {
        double num = getDouble(0, GeneralUtils.getTotal(weights));
        double sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            if (num <= sum)
                return i;
        }
        return null;
    }
}