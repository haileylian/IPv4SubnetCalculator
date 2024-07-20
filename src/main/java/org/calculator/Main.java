import org.calculator.SubnetCalculatorApp;

import javax.swing.*; /**
 * The main method that launches the application.
 * It creates an instance of SubnetCalculatorApp and makes it visible.
 */
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        SubnetCalculatorApp frame = new SubnetCalculatorApp();
        frame.setVisible(true);
    });
}