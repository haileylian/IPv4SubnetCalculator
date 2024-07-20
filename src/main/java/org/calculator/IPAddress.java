package org.calculator;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Utility class for IP address conversions and validations.
 */
public class IPAddress {

    /**
     * Default constructor for the IPAddress class.
     */
    public IPAddress() {}

    /**
     * Converts an IP address in string format to an integer.
     *
     * @param ipAddress the IP address in string format
     * @return the IP address as an integer
     * @throws IllegalArgumentException if the IP address is invalid
     */
    public static int ipToInt(String ipAddress) {
        try {
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            byte[] bytes = inetAddress.getAddress();
            int result = 0;
            for (byte b : bytes) {
                result = (result << 8) | (b & 0xFF);
            }
            return result;
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException("Invalid IP address.", e);
        }
    }

    /**
     * Converts an integer to an IP address in string format.
     *
     * @param ip the IP address as an integer
     * @return the IP address in string format
     * @throws IllegalArgumentException if the IP integer is invalid
     */
    public static String intToIp(int ip) {
        try {
            byte[] bytes = new byte[] {
                    (byte) (ip >>> 24),
                    (byte) (ip >>> 16),
                    (byte) (ip >>> 8),
                    (byte) ip
            };
            InetAddress inetAddress = InetAddress.getByAddress(bytes);
            return inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException("Invalid IP integer.", e);
        }
    }

    /**
     * Validates if the given string is a valid IP address.
     *
     * @param ip the IP address in string format
     * @return true if the IP address is valid, false otherwise
     */
    public static boolean isValidIPAddress(String ip) {
        try {
            InetAddress.getByName(ip);
            return true;
        } catch (UnknownHostException ex) {
            return false;
        }
    }

    /**
     * Validates if the given CIDR value is valid.
     *
     * @param cidr the CIDR value
     * @return true if the CIDR value is between 0 and 32, inclusive, false otherwise
     */
    public static boolean isValidSubnetMask(int cidr) {
        return cidr >= 0 && cidr <= 32;
    }

    /**
     * Determines the class of the given IP address.
     *
     * @param ipAddress the IP address in string format
     * @return the class of the IP address (A, B, C, D, or E)
     */
    public static String getIpClass(String ipAddress) {
        int firstOctet = Integer.parseInt(ipAddress.split("\\.")[0]);
        if (firstOctet >= 1 && firstOctet <= 126) {
            return "A";
        } else if (firstOctet >= 128 && firstOctet <= 191) {
            return "B";
        } else if (firstOctet >= 192 && firstOctet <= 223) {
            return "C";
        } else if (firstOctet >= 224 && firstOctet <= 239) {
            return "D";
        } else {
            return "E";
        }
    }

    /**
     * Converts an IP address in integer format to a binary string representation.
     *
     * @param ip the IP address as an integer
     * @return the binary string representation of the IP address
     */
    public static String intToBinaryString(int ip) {
        return String.format("%8s.%8s.%8s.%8s",
                Integer.toBinaryString((ip >> 24) & 0xff),
                Integer.toBinaryString((ip >> 16) & 0xff),
                Integer.toBinaryString((ip >> 8) & 0xff),
                Integer.toBinaryString(ip & 0xff)).replace(' ', '0');
    }
}