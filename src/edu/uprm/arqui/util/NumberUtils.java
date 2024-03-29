package edu.uprm.arqui.util;

/**
 * Created by eduardobreijo on 9/4/14.
 */
public class NumberUtils {

    private static final int INT_BITS = 32;

    /**
     * Gets the signed value for a specified bits. The most significant bit, starts with index 0
     * Indexes start and end are inclusive. They go from 0 through bitsToUse-1.
     *
     * @param value     number to get the value
     * @param start     starting bit
     * @param end       ending bit
     * @param bitsToUse resolution of number to use, maximum is 32
     *                  if it is less than 32, it will take the least significant bits.
     * @return the signed number
     */
    public static int getSignedValueOf(int value, int start, int end, int bitsToUse) {
        if (bitsToUse > INT_BITS || start < 0 || end >= bitsToUse || start > end) {
            throw new IndexOutOfBoundsException(String.format("index %d start and %d end are out of bounds", start, end));
        }
        final int normalizedIndex = INT_BITS - bitsToUse;
        start += normalizedIndex;
        end += normalizedIndex;
        int number = (value << start) >> start;
        number >>= INT_BITS - end - 1;
        return number;
    }

    /**
     * Gets the unsigned value for a specified bits. The most significant bit, starts with index 0
     * Indexes start and end are inclusive. They go from 0 through bitsToUse-1.
     *
     * @param value     number to get the value
     * @param start     starting bit
     * @param end       ending bit
     * @param bitsToUse resolution of number to use, maximum is 32
     *                  if it is less than 32, it will take the least significant bits.
     * @return the unsigned number
     */
    public static int getUnsignedValueOf(int value, int start, int end, int bitsToUse) {
        if (bitsToUse > INT_BITS || start < 0 || end >= bitsToUse || start > end) {
            throw new IndexOutOfBoundsException(String.format("index %d start and %d end are out of bounds", start, end));
        }
        final int normalizedIndex = INT_BITS - bitsToUse;
        start += normalizedIndex;
        end += normalizedIndex;
        int number = (value << start) >>> start;
        number >>>= INT_BITS - end - 1;
        return number;
    }


    /**
     * Verify if it is a valid integer
     *
     * @param number the String to check if it is valid
     * @param radix
     * @return true if it is valid, false otherwise
     */
    public static boolean isValidNumber(String number, int radix) {
        try {
            Integer.parseInt(number, radix);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Convert an integer value into its hexadecimal representation
     *
     * @param value  the integer value
     * @param digits the number of hex digits in the string
     * @return the hexadecimal representation of the int value
     */
    public static String intToHexString(int value, int digits) {
        int maximum = (1 << digits * 4) - 1;
        String format = String.format("%%0%dX", digits);
        return String.format(format, value & maximum);
    }
}
