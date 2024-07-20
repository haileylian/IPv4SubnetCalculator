package org.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents a GUI application for calculating subnet details.
 * It extends JFrame to create a window where the user can input an IP address and CIDR value,
 * and it will display the calculated subnet details.
 */
public class SubnetCalculatorApp extends JFrame {
    /**
     * A JTextField component for user to input IP address and CIDR value.
     */
    private JTextField ipField;

    /**
     * A JTextArea component to display the calculated subnet details.
     */
    private JTextArea resultArea;

    /**
     * A JButton component that triggers the calculation of subnet details when clicked.
     */
    private JButton calculateButton;

    /**
     * A JButton component that clears the input field and result area when clicked.
     */
    private JButton clearButton;

    /**
     * An instance of SubnetCalculator to perform subnet calculations.
     */
    private SubnetCalculator subnetCalculator;

    /**
     * Constructor for the SubnetCalculatorApp class.
     * Initializes the subnetCalculator and sets up the JFrame.
     */
    public SubnetCalculatorApp() {
        subnetCalculator = new SubnetCalculator();
        setTitle("IPv4 Subnet Calculator");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    /**
     * Initializes the components of the JFrame.
     * This includes the text field for input, the text area for results, and the calculate and clear buttons.
     */
    private void initComponents() {
        ipField = new JTextField();
        resultArea = new JTextArea();
        calculateButton = new JButton("Calculate");
        clearButton = new JButton("Clear");

        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        calculateButton.addActionListener(new CalculateButtonListener());
        clearButton.addActionListener(new ClearButtonListener());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Enter IP address with CIDR (e.g., 192.168.1.1/24):"), BorderLayout.NORTH);
        panel.add(ipField, BorderLayout.CENTER);
        panel.add(calculateButton, BorderLayout.EAST);
        panel.add(clearButton, BorderLayout.WEST);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
    }

    /**
     * This class represents an action listener for the calculate button.
     * When the button is clicked, it validates the input, calculates the subnet details, and displays the results.
     */
    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = ipField.getText();
            try {
                String[] parts = input.split("/");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid input format. Expected format: IP/CIDR");
                }
                String ipAddress = parts[0];
                int cidr = Integer.parseInt(parts[1]);
                String subnetMask = IPAddress.intToIp((0xffffffff << (32 - cidr)) & 0xffffffff);

                if (!IPAddress.isValidIPAddress(ipAddress) || cidr < 0 || cidr > 32 || !IPAddress.isValidSubnetMask(cidr)) {
                    throw new IllegalArgumentException("Invalid IP address, CIDR value, or subnet mask.");
                }

                SubnetDetails subnetDetails = subnetCalculator.calculateSubnetDetails(ipAddress, cidr);
                resultArea.append(subnetDetails.toString() + "\n\n");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * This class represents an action listener for the clear button.
     * When the button is clicked, it clears the input field and the result area.
     */
    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ipField.setText("");
            resultArea.setText("");
        }
    }
}