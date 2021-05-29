package neon.utils;

import java.awt.Color;
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
import javax.imageio.ImageIO;

/** A class used to perform many miscellaneous operations. */
public class GeneralUtils {
  private static OsType detectedOS;

  /** @return an enumeration specifying the operating system type */
  public static OsType getOSType() {
    if (detectedOS == null) {
      String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
      if ((OS.contains("mac")) || (OS.contains("darwin"))) {
        detectedOS = OsType.MAC_OS;
      } else if (OS.contains("win")) {
        detectedOS = OsType.WINDOWS;
      } else if (OS.contains("nux")) {
        detectedOS = OsType.LINUX;
      } else {
        detectedOS = OsType.OTHER;
      }
    }
    return detectedOS;
  }

  /**
   * @param value the tested value
   * @param min the minimum value
   * @param max the maximum value
   * @return whether the value is within the specified range (inclusive)
   */
  public static boolean isWithinRange(double value, double min, double max) {
    return min <= value && value <= max;
  }

  /**
   * Shifts the value so that it resides within the specified range.
   *
   * @param value the value
   * @param min the minimum value
   * @param max the maximum value
   * @return the constrained value
   */
  public static double constrain(double value, double min, double max) {
    return Math.min(Math.max(value, min), max);
  }

  /**
   * @param numbers the array of numbers
   * @return the average of the numbers or NaN if there are invalid inputs
   */
  public static double getAverage(double[] numbers) {
    return Arrays.stream(numbers).average().orElse(Double.NaN);
  }

  /**
   * @param numbers the array of numbers
   * @return the average of the numbers or null if there are invalid inputs
   */
  public static Integer getAverage(int[] numbers) {
    double avg = Arrays.stream(numbers).average().orElse(Double.NaN);
    return Double.isNaN(avg) ? null : (int) avg;
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
   * Reads lines of text from a file.
   *
   * @param path the path of the file
   * @param charset the charset to be used to decode the data
   * @return a list of strings containing the text in the file or null if the file does not exist
   */
  public static List<String> readLines(String path, Charset charset) {
    Path pathObj = Paths.get(path);
    try {
      return Files.readAllLines(pathObj, charset);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  /**
   * Reads text from a file.
   *
   * @param path the path of the file
   * @return the text in the file or null if the file does not exist
   */
  public static String readText(String path) {
    Path pathObj = Paths.get(path);
    try {
      return Files.readString(pathObj);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  /**
   * Writes text to a file.
   *
   * @param text the text
   * @param path the path of the file
   * @return whether the operation was successful
   */
  public static boolean writeText(String text, String path) {
    try {
      Files.writeString(Paths.get(path), text);
      return true;
    } catch (IOException ex) {
      return false;
    }
  }

  /**
   * Writes lines of text to a file.
   *
   * @param lines the lines of text
   * @param path the path of the file
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
   * @param lines the lines of text
   * @param path the path of the file
   * @param charset the charset to be used to encode the data
   * @return whether the operation was successful
   */
  public static boolean writeLines(String[] lines, String path, Charset charset) {
    return writeLines(Arrays.asList(lines), path, charset);
  }

  /**
   * Formats a quantity.
   *
   * @param number the quantity of the noun
   * @param singular the singular form of the word
   * @param plural the plural form of the word
   * @param doIncludeNumber whether the result should include the number
   * @return the correct form
   */
  public static String formatQuantity(
      int number, String singular, String plural, boolean doIncludeNumber) {
    String output = number == 1 ? singular : plural;
    return doIncludeNumber ? number + " " + output : output;
  }

  /**
   * Formats a quantity.
   *
   * @param number the quantity of the noun
   * @param singular the singular form of the word
   * @param plural the plural form of the word
   * @param doIncludeNumber whether the result should include the number
   * @return the correct form
   */
  public static String formatQuantity(
      double number, String singular, String plural, boolean doIncludeNumber) {
    String output = number == 1 ? singular : plural;
    return doIncludeNumber ? number + " " + output : output;
  }

  /**
   * Changes a color's opacity.
   *
   * @param color the original color
   * @param opacity the new opacity
   * @return an identical color with the specified opacity
   */
  public static Color changeColorOpacity(Color color, int opacity) {
    return new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity);
  }

  /**
   * Changes a color's opacity.
   *
   * @param color the original color
   * @param opacity the new opacity
   * @return an identical color with the specified opacity
   */
  public static Color changeColorOpacity(Color color, float opacity) {
    return changeColorOpacity(color, Math.round(opacity * 255));
  }
}
