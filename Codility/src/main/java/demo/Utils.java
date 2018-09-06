package demo;

/**
 * @author ajinnair
 *
 */
public class Utils {

	/*
	 * Checks if string can be used as an Integer or not
	 * */
	public static boolean isValidInt(String str) {
		{
			try {
				Integer.parseInt(str);
				return true;
			}

			catch (NumberFormatException er) {
				return false;
			}
		}

	}
}
