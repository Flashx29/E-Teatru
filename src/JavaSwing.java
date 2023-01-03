import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class JavaSwing {
    public static JFrame frame = new JFrame("E-Teatru"); // creating instance of JFrame
    public static String selectedShow;
    public static void launchJavaSwing() {
        // START SHOWS LIST
        DefaultListModel<String> l1 = new DefaultListModel<>();
        for(int i=0; i<JSONShows.shows.length(); i++){ // list items from shows array
            JSONObject show_obj = JSONShows.shows.getJSONObject(i);
            String showTitle = show_obj.getString("title");
            l1.addElement(showTitle);
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
                selectedShow = showsList.getSelectedValue();
                selectedShowLabel.setText("Selected Show: " + selectedShow);
            }
        });

        JButton nextBtn = new JButton("Next");
        nextBtn.setBounds(150, 400, 100, 40); // x-axis, y-axis, width, height
        frame.add(nextBtn);
        nextBtn.addActionListener(new ActionListener() {  // Clear Current frame on 'Next' button click
            public void actionPerformed(ActionEvent ae) {
                if (selectedShow == null) {
                    Utils.createPleaseSelectText();
                    return;
                }
                System.out.println("passed");
                Utils.clearFrame();
                SecondFrame.init();
            }
        });
        // END --- Create Select Show Button & Show Selected ---

        // Frame Settings
        frame.setSize(400, 500); // 400 width and 500 height
        frame.setLayout(null); // using no layout managers
        frame.setVisible(true); // making the frame visible
    }
}