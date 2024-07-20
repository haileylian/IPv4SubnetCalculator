package org.calculator;

/**
 * Class representing the details of a subnet.
 */
public class SubnetDetails {
    private final String ipAddress;
    private final int cidr;
    private final String networkAddress;
    private final String broadcastAddress;
    private final String firstHost;
    private final String lastHost;
    private final int numberOfHosts;

    /**
     * Constructs a SubnetDetails object with the specified parameters.
     *
     * @param ipAddress the IP address in string format
     * @param cidr the CIDR value
     * @param networkAddress the calculated network address
     * @param broadcastAddress the calculated broadcast address
     * @param firstHost the first usable host address
     * @param lastHost the last usable host address
     * @param numberOfHosts the number of usable hosts in the subnet
     */
    public SubnetDetails(String ipAddress, int cidr, String networkAddress, String broadcastAddress, String firstHost, String lastHost, int numberOfHosts) {
        this.ipAddress = ipAddress;
        this.cidr = cidr;
        this.networkAddress = networkAddress;
        this.broadcastAddress = broadcastAddress;
        this.firstHost = firstHost;
        this.lastHost = lastHost;
        this.numberOfHosts = numberOfHosts;
    }

    /**
     * Returns the IP address.
     *
     * @return the IP address
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Returns the CIDR value.
     *
     * @return the CIDR value
     */
    public int getCidr() {
        return cidr;
    }

    /**
     * Returns the network address.
     *
     * @return the network address
     */
    public String getNetworkAddress() {
        return networkAddress;
    }

    /**
     * Returns the broadcast address.
     *
     * @return the broadcast address
     */
    public String getBroadcastAddress() {
        return broadcastAddress;
    }

    /**
     * Returns the first usable host address.
     *
     * @return the first usable host address
     */
    public String getFirstHost() {
        return firstHost;
    }

    /**
     * Returns the last usable host address.
     *
     * @return the last usable host address
     */
    public String getLastHost() {
        return lastHost;
    }

    /**
     * Returns the number of usable hosts in the subnet.
     *
     * @return the number of usable hosts
     */
    public int getNumberOfHosts() {
        return numberOfHosts;
    }

    /**
     * Returns a string representation of the subnet details.
     *
     * @return a formatted string with subnet details
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Address:   %s           %s%n", ipAddress, IPAddress.intToBinaryString(IPAddress.ipToInt(ipAddress))));
        sb.append(String.format("Netmask:   %s = %d    %s%n", IPAddress.intToIp((0xffffffff << (32 - cidr)) & 0xffffffff), cidr, IPAddress.intToBinaryString((0xffffffff << (32 - cidr)) & 0xffffffff)));
        sb.append(String.format("Wildcard:  %s             %s%n", IPAddress.intToIp(~(0xffffffff << (32 - cidr)) & 0xffffffff), IPAddress.intToBinaryString(~(0xffffffff << (32 - cidr)) & 0xffffffff)));

        sb.append("=>\n");
        sb.append(String.format("Network:   %s/%d        %s%n", networkAddress, cidr, IPAddress.intToBinaryString(IPAddress.ipToInt(networkAddress))));
        sb.append(String.format("Broadcast: %s         %s%n", broadcastAddress, IPAddress.intToBinaryString(IPAddress.ipToInt(broadcastAddress))));
        sb.append(String.format("HostMin:   %s           %s%n", firstHost, IPAddress.intToBinaryString(IPAddress.ipToInt(firstHost))));
        sb.append(String.format("HostMax:   %s         %s%n", lastHost, IPAddress.intToBinaryString(IPAddress.ipToInt(lastHost))));
        sb.append(String.format("Hosts/Net: %d%n", numberOfHosts));
        sb.append(String.format("IP Class:  %s%n", IPAddress.getIpClass(ipAddress)));
        return sb.toString();
    }
}