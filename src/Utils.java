import javax.swing.*;

public class Utils extends JavaSwing {
    public static JLabel selectedLabel;
    public static void clearFrame() {
        frame.getContentPane().removeAll();
        frame.repaint();
    }

    public static void createSelectedShowLabel(String string) {
        selectedLabel = new JLabel(string);
        selectedLabel.setBounds(75, 350, 300, 30); // x-axis, y-axis, width, height
        frame.add(selectedLabel);
    }

    public static void createPleaseSelectText() {
        JLabel nothingSelected = new JLabel("To continue, please select a show.");
        nothingSelected.setBounds(75, 320, 300, 30);
        frame.add(nothingSelected);
    }
}
