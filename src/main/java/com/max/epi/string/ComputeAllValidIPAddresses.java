package com.max.epi.string;

import static com.google.common.base.Preconditions.checkArgument;
import java.util.Objects;

public final class ComputeAllValidIPAddresses {

    private static final int IP4_OCTETS_COUNT = 4;
    private static final int MAX_IP4_OCTET_VALUE = 255;

    private static final int MIN_IP4_STR_LENGTH = IP4_OCTETS_COUNT;
    private static final int MAX_IP4_STR_LENGTH = 12;

    private static final int ZERO_CHARACTER_CODE = '0';

    private ComputeAllValidIPAddresses() {
        throw new AssertionError("Can't instantiate utility-only function");
    }

    /**
     * 7.10. Compute all valid IP addresses.
     * <p>
     * time: O(3^4) ~ O(1)
     * space: O(1)
     */
    public static void printAllIPsRecursiveSolution(String ipStr) {
        checkValidIPString(ipStr);
        printAllIPsRec(ipStr.toCharArray(), 0, new int[IP4_OCTETS_COUNT], 0);
    }

    private static void printAllIPsRec(char[] arr, int index, int[] res, int resIndex) {

        boolean arrEnded = (index == arr.length);
        boolean resEnded = (resIndex == res.length);

        if (arrEnded && resEnded) {
            System.out.println(toIP4String(res));
            return;
        }

        if (arrEnded ^ resEnded) {
            return;
        }

        int singleValue = 0;
        for (int i = index; i < arr.length; ++i) {
            singleValue = 10 * singleValue + (arr[i] - ZERO_CHARACTER_CODE);

            if (singleValue > MAX_IP4_OCTET_VALUE) {
                break;
            }

            res[resIndex] = singleValue;
            printAllIPsRec(arr, i + 1, res, resIndex + 1);
        }
    }

    private static String toIP4String(int[] arr) {

        StringBuilder buf = new StringBuilder(MAX_IP4_STR_LENGTH);

        for (int singleOctetValue : arr) {
            buf.append(singleOctetValue).append(".");
        }

        buf.deleteCharAt(buf.length() - 1);
        return buf.toString();
    }

    private static void checkValidIPString(String ipStr) {
        checkArgument(ipStr != null, "'ipStr' parameter is null");
        checkArgument(ipStr.length() >= MIN_IP4_STR_LENGTH && ipStr.length() <= MAX_IP4_STR_LENGTH,
                      "IP address very small or very big: %s", ipStr);

        for (int i = 0, ipStrLength = ipStr.length(); i < ipStrLength; ++i) {
            if (!Character.isDigit(ipStr.charAt(i))) {
                throw new IllegalArgumentException("'" + ipStr + "' can't be a valid IP address.");
            }
        }
    }


    public static void printAllValidIpsIterativeSolution(String str) {
        Objects.requireNonNull(str, "NULL 'str' detected");

        if (str.length() < 4) {
            throw new IllegalArgumentException("Can't find any valid IPs");
        }

        int first = 0;

        for (int i = 0; i < 3 && i < str.length(); ++i) {

            first = first * 10 + toDigit(str.charAt(i));

            if (first < 256) {
                int second = 0;

                for (int j = i + 1; j <= i + 3 && j < str.length(); ++j) {
                    second = second * 10 + toDigit(str.charAt(j));

                    if (second < 256) {
                        int third = 0;

                        for (int k = j + 1; k <= j + 3 && k < str.length() - 1; ++k) {
                            third = third * 10 + toDigit(str.charAt(k));

                            if (third < 256) {
                                int fourth = toNumber(str, k + 1);

                                if (fourth < 256) {
                                    System.out.printf("%d.%d.%d.%d%n", first, second, third, fourth);
                                }
                            }

                            if (third == 0) {
                                break;
                            }
                        }
                    }

                    if (second == 0) {
                        break;
                    }
                }
            }

            if (first == 0) {
                break;
            }
        }
    }

    private static int toNumber(String str, int from) {

        int digitsCnt = str.length() - from;

        if (digitsCnt < 1 || digitsCnt > 3) {
            return 256;
        }

        if (digitsCnt > 1 && toDigit(str.charAt(from)) == 0) {
            return 256;
        }

        return Integer.parseInt(str.substring(from));
    }

    private static int toDigit(char ch) {
        return ch - '0';
    }

    public static void main(String[] args) {
        printAllIPsRecursiveSolution("19216811");

//        printAllValidIpsIterativeSolution("19216811");

        System.out.println("ComputeAllValidIPAddresses done");
    }


}
