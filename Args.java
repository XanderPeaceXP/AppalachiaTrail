/**
 * Example of using command line arguments to seed a random number generator 
 * and redirect keyboard input like it was manually entered but is actually 
 * the content of a file.
 * 
 * java Args seed < filename
 * 
 * seed is an integer
 * filename contains the contents you want to input
 * < denotes redirected input, you can also use > to redirect output to a file
 * 
 * You can omit either/both but then the generated numbers will be unpredictable 
 * or the program will wait for keyboard input.
 * 
 * @author Adrian Veliz
 */

import java.util.*;
public class Args{
	public static Scanner console = null;
	public static Random rand = null;

	static {
		try {
			if (System.getProperty("sun.java.command").contains(" ")) {
				String[] args = System.getProperty("sun.java.command").split(" ");
				if (args.length > 1) {
					rand = new Random(Long.parseLong(args[1]));
				}
			}
		} catch (Exception e) {}

		if (System.console() == null) {
			console = new Scanner(System.in);
		}
	}
}
