package neon.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
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
     * @return an enumeration specifying the operating system type
     */
    public static OSType getOSType() {
        if (detectedOS == null) {
            String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            if ((OS.contains("mac")) || (OS.contains("darwin")))
                detectedOS = OSType.MAC_OS;
            else if (OS.contains("win"))
                detectedOS = OSType.WINDOWS;
            else if (OS.contains("nux"))
                detectedOS = OSType.LINUX;
            else
                detectedOS = OSType.OTHER;
        }
        return detectedOS;
    }

    /**
     * @param value the tested value
     * @param min   the minimum value
     * @param max   the maximum value
     * @return whether the value is within the specified range
     */
    public static boolean isWithinRange(double value, double min, double max) {
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
        for (double number : numbers)
            total += number;
        return total;
    }

    /**
     * Loads an image from a file.
     *
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
     * @param path    the path of the file
     * @param charset the charset to be used to decode the data
     * @return a list of strings containing the text in the file or null if the file does not exist
     */
    public static List<String> readAllLines(String path, Charset charset) {
        Path pathObj = Paths.get(path);
        if (Files.exists(pathObj))
            try {
                return Files.readAllLines(pathObj, charset);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        return null;
    }

    /**
     * Reads all the text from a file.
     *
     * @param path    the path of the file
     * @param charset the charset to be used to decode the data
     * @return the text in the file or null if the file does not exist
     */
    public static String readAllText(String path, Charset charset) {
        Path pathObj = Paths.get(path);
        if (Files.exists(pathObj))
            try {
                return new String(Files.readAllBytes(pathObj), charset);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        return null;
    }

    /**
     * Writes text to a file.
     *
     * @param text    the text
     * @param path    the path of the file
     * @param charset the charset to be used to encode the data
     * @return whether the operation was successful
     */
    public static boolean writeText(String text, String path, Charset charset) {
        try {
            Files.write(Paths.get(path), text.getBytes(charset));
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Writes lines of text to a file.
     *
     * @param lines   the lines of text
     * @param path    the path of the file
     * @param charset the charset to be used to encode the data
     * @return whether the operation was successful
     */
    public static boolean writeLines(List<String> lines, String path, Charset charset) {
        try {
            Files.write(Paths.get(path), lines, charset);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Writes lines of text to a file.
     *
     * @param lines   the lines of text
     * @param path    the path of the file
     * @param charset the charset to be used to encode the data
     * @return whether the operation was successful
     */
    public static boolean writeLines(String[] lines, String path, Charset charset) {
        return writeLines(Arrays.asList(lines), path, charset);
    }

    /**
     * Checks whether a noun should use its singular or plural form and returns the correct one.
     *
     * @param number    the quantity of the noun
     * @param singular  the singular form of the word
     * @param plural    the plural form of the word
     * @param inclusive whether the result should include the number
     * @return the correct form
     */
    public static String pluralCheck(int number, String singular, String plural, boolean inclusive) {
        String output = number == 1 ? singular : plural;
        return inclusive ? number + " " + output : output;
    }

    /**
     * Checks whether a noun should use its singular or plural form and returns the correct one.
     *
     * @param number    the quantity of the noun
     * @param singular  the singular form of the word
     * @param plural    the plural form of the word
     * @param inclusive whether the result should include the number
     * @return the correct form
     */
    public static String pluralCheck(double number, String singular, String plural, boolean inclusive) {
        String output = number == 1 ? singular : plural;
        return inclusive ? number + " " + output : output;
    }
}