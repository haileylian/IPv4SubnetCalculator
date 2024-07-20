package org.calculator;

/**
 * Class responsible for calculating subnet details given an IP address and CIDR notation.
 */
public class SubnetCalculator {

    /**
     * Default constructor for the SubnetCalculator class.
     */
    public SubnetCalculator() {
    }

    /**
     * Calculates the subnet details for a given IP address and CIDR value.
     *
     * @param ipAddress the IP address in string format
     * @param cidr the CIDR value
     * @return a SubnetDetails object containing the calculated subnet information
     * @throws IllegalArgumentException if the IP address is invalid or CIDR value is out of range
     */
    public SubnetDetails calculateSubnetDetails(String ipAddress, int cidr) {
        int subnetMask = (0xffffffff << (32 - cidr)) & 0xffffffff;
        int ip = IPAddress.ipToInt(ipAddress);

        int network = ip & subnetMask;
        int broadcast = network | ~subnetMask;

        String networkAddress = IPAddress.intToIp(network);
        String broadcastAddress = IPAddress.intToIp(broadcast);
        String firstHost = IPAddress.intToIp(network + 1);
        String lastHost = IPAddress.intToIp(broadcast - 1);
        int numberOfHosts = broadcast - network - 1;

        return new SubnetDetails(ipAddress, cidr, networkAddress, broadcastAddress, firstHost, lastHost, numberOfHosts);
    }
}