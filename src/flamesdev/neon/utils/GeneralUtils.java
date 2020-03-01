package flamesdev.neon.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * A class used to perform many miscellaneous operations.
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

    /**
     * Reads all the lines of text from a file.
     *
     * @param path the path of the file
     * @return a list of strings containing the data in the file
     */
    public static List<String> readAllLines(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Reads all the text from a file.
     *
     * @param path the path of the file
     * @return the text in the file
     */
    public static String readAllText(String path) {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Writes text to a file.
     *
     * @param text the text
     * @param path the path of the file
     */
    public static void writeText(String text, String path) {
        try {
            Files.writeString(Paths.get(path), text);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Writes lines of text to a file.
     *
     * @param lines the lines of text
     * @param path  the path of the file
     */
    public static void writeLines(List<String> lines, String path) {
        try {
            Files.write(Paths.get(path), lines, Charset.defaultCharset());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Writes lines of text to a file.
     *
     * @param lines the lines of text
     * @param path  the path of the file
     */
    public static void writeLines(String[] lines, String path) {
        writeLines(Arrays.asList(lines), path);
    }
}