import javax.swing.*;

public class Utils extends JavaSwing {
    public static void clearFrame() {
        frame.getContentPane().removeAll();
        frame.repaint();
    }

    public static void createPleaseSelectText() {
        JLabel nothingSelected = new JLabel("To continue, please select a show.");
        nothingSelected.setBounds(5, 300, 300, 30);
        frame.add(nothingSelected);
    }
}
