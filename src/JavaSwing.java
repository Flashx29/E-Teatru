import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class JavaSwing {
    public static String[] shows = {"UNIVERSURI PARALELE", "MARIA TĂNASE. O POVESTE", "PROMETEU'22"};
    public static String selectedListItem;
    public static void launchJavaSwing() {
        JFrame frame = new JFrame("E-Teatru"); //creating instance of JFrame

        // START SHOWS LIST
        DefaultListModel<String> l1 = new DefaultListModel<>();
        for (String show : shows) { // list items from shows array
            l1.addElement(show);
        }
        JList<String> showsList = new JList<>(l1);
        showsList.setBounds(100,100, 200,200);
        frame.add(showsList);
        // END SHOWS LIST

        // Create Select Show Button & Show Selected
        JLabel selectedShowLabel = new JLabel("Selected Show:");
        selectedShowLabel.setBounds(75, 350, 300, 30); // x-axis, y-axis, width, height
        frame.add(selectedShowLabel);

        // Get Selected Item from List and replace label with it
        showsList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                if (evt.getValueIsAdjusting())
                    return;
                // System.out.println(showsList.getSelectedValue());
                selectedListItem = showsList.getSelectedValue();
                selectedShowLabel.setText("Selected Show: " + selectedListItem);
            }
        });

        JButton button = new JButton("Next");
        button.setBounds(150, 400, 100, 40); // x-axis, y-axis, width, height
        frame.add(button);
        // END --- Create Select Show Button & Show Selected ---

        // Frame Settings
        frame.setSize(400, 500); // 400 width and 500 height
        frame.setLayout(null); // using no layout managers
        frame.setVisible(true); // making the frame visible
    }
}