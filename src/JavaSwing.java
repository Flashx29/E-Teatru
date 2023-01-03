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
                    createPleaseSelectText();
                    return;
                }
                System.out.println("passed");
                clearFrame();
                secondFrameItems();
            }
        });
        // END --- Create Select Show Button & Show Selected ---

        // Frame Settings
        frame.setSize(400, 500); // 400 width and 500 height
        frame.setLayout(null); // using no layout managers
        frame.setVisible(true); // making the frame visible
    }

    public static void secondFrameItems() {
        JLabel currentShow = new JLabel("Alege Teatrul pentru opera: " + selectedShow);
        currentShow.setBounds(50, 50, 350, 20);
        frame.add(currentShow);

        // START SHOWS LIST
        DefaultListModel<String> showDetailsList = new DefaultListModel<>();
        for(int i = 0; i < JSONShows.shows.length(); i++){ // list items from shows array
            JSONObject show_obj = JSONShows.shows.getJSONObject(i);
            String showTitle = show_obj.getString("title");
            if (Objects.equals(showTitle, selectedShow)) {
                JSONObject showPlayingAt = show_obj.getJSONObject("showPlayingAt");
                Iterator<String> showPlayAtKeys = showPlayingAt.keys();
                while(showPlayAtKeys.hasNext()) {
                    String placeKeyTitle = showPlayAtKeys.next();
                    String placeValueData = showPlayingAt.getString(placeKeyTitle);
                    showDetailsList.addElement(placeKeyTitle + ": " + placeValueData);
                }
            }
        }
        JList<String> showsList = new JList<>(showDetailsList);
        showsList.setBounds(100,100, 200,200);
        frame.add(showsList);
        // END SHOWS LIST

    }

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