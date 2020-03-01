package flamesdev.neon.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Locale;

/**
 * A class that can used to perform many miscellaneous operations.
 */
public class GeneralUtils {
    private static OSType detectedOS;

    /**
     * @return an enumeration describing the operating system type
     */
    public static OSType getOSType() {
        if (detectedOS == null) {
            String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            if ((OS.contains("mac")) || (OS.contains("darwin")))
                detectedOS = OSType.MacOS;
            else if (OS.contains("win"))
                detectedOS = OSType.Windows;
            else if (OS.contains("nux"))
                detectedOS = OSType.Linux;
            else
                detectedOS = OSType.Other;
        }
        return detectedOS;
    }

    /**
     * @param value the tested value
     * @param min   the minimum value
     * @param max   the maximum value
     * @return whether the value is within the specified range
     */
    public static boolean withinRange(double value, double min, double max) {
        return min <= value && value <= max;
    }

    /**
     * Shifts the value so that it resides within the specified range.
     *
     * @param value the value
     * @param min   the minimum value
     * @param max   the maximum value
     * @return the constrained value
     */
    public static double constrain(double value, double min, double max) {
        return Math.min(Math.max(value, min), max);
    }

    /**
     * @param numbers the array of numbers
     * @return the average of the numbers
     */
    public static double getAverage(double[] numbers) {
        return getTotal(numbers) / numbers.length;
    }

    /**
     * @param numbers the array of numbers
     * @return the sum of all the number
     */
    public static double getTotal(double[] numbers) {
        double total = 0;
        for (double number : numbers) total += number;
        return total;
    }

    /**
     * @param path the path of the image file
     * @return a BufferedImage object or null if an exception is thrown
     */
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}