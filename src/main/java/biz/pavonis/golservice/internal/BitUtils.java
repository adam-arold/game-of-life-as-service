package biz.pavonis.golservice.internal;

public class BitUtils {

	private BitUtils() {
	}
	
	/**
	 * Returns an integer converted to a binary array.
	 * @param input
	 * @param length
	 * @return
	 */
	public static boolean[] toBinaryArray(int input, int length) {
		boolean[] bits = new boolean[length];
	    for (int i = length - 1; i >= 0; i--) {
	        bits[i] = (input & (1 << i)) != 0;
	    }
	    return bits;
	}
}
