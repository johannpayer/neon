package flamesdev.neon.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;

public class GeneralUtils {
	public enum OSType {
		Windows, MacOS, Linux, Other
	}

	private static OSType detectedOS;

	public static OSType getOSType() {
		if (detectedOS == null) {
			String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
			if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0))
				detectedOS = OSType.MacOS;
			else if (OS.indexOf("win") >= 0)
				detectedOS = OSType.Windows;
			else if (OS.indexOf("nux") >= 0)
				detectedOS = OSType.Linux;
			else
				detectedOS = OSType.Other;
		}
		return detectedOS;
	}

	public static boolean withinRange(double value, double min, double max) {
		return min <= value && value <= max;
	}

	public static double constrain(double value, double min, double max) {
		return Math.min(Math.max(value, min), max);
	}

	public static List<String> readFile(File f) {
		try {
			String s;
			FileReader fr = new FileReader(f.getAbsolutePath());
			BufferedReader br = new BufferedReader(fr);
			br.ready();
			ArrayList<String> strings = new ArrayList<String>();
			while ((s = br.readLine()) != null)
				strings.add(s);
			br.close();
			return strings;
		} catch (Exception ex) {
			return null;
		}
	}

	public static boolean saveFile(String path, List<String> data) {
		try {
			FileWriter fw = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.flush();
			for (String line : data) {
				bw.write(line);
				bw.newLine();
			}
			bw.close();
			fw.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public static double getMin(Double[] numbers) {
		double min = Double.MAX_VALUE;
		for (Double num : numbers)
			if (num != null && num < min)
				min = num;
		return min;
	}

	public static double getMax(Double[] numbers) {
		double max = Double.MIN_VALUE;
		for (Double num : numbers)
			if (num != null && num > max)
				max = num;
		return max;
	}

	public static double getAverage(double[] numbers) {
		return getTotal(numbers) / numbers.length;
	}

	public static double getTotal(double[] numbers) {
		double total = 0;
		for (int i = 0; i < numbers.length; i++)
			total += numbers[i];
		return total;
	}

	public static BufferedImage loadImage(String path) throws IOException {
		return ImageIO.read(new File(path));
	}
}