import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class JavaSwing {
    public static JFrame frame = new JFrame("E-Teatru"); // creating instance of JFrame
    public static String selectedShow;
    public static String selectedTheater;
    public static String selectedSeatsNumber;
    public static String selectedUserSeats;
    public static String userName;
    public static String userPhone;
    public static Integer userMoney = 0;
    public static Integer userReset;

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

        // Create Next Button & Show Selected
        Utils.createSelectedShowLabel("Spectacol Selectat:");
        // Get Selected Item from List and replace label with it
        showsList.addListSelectionListener(evt -> {
            if (evt.getValueIsAdjusting())
                return;
            selectedShow = showsList.getSelectedValue();
            Utils.selectedLabel.setForeground(Color.BLACK);
            Utils.selectedLabel.setText("Spectacol Selectat: " + selectedShow);
        });

        JButton nextBtn = new JButton("Next");
        nextBtn.setBounds(150, 400, 100, 40); // x-axis, y-axis, width, height
        frame.add(nextBtn);
        // Clear Current frame on 'Next' button click
        nextBtn.addActionListener(ae -> {
            if (selectedShow == null) {
                Utils.selectedLabel.setText("Selecteaza un spectacol mai intai!");
                Utils.selectedLabel.setForeground(Color.RED);
                return;
            }
            System.out.println("passed");
            Utils.clearFrame();
            SecondFrame.init();
        });
        // END --- Create Next Button & Show Selected ---

        // Frame Settings
        frame.setSize(400, 500); // 400 width and 500 height
        frame.setLayout(null); // using no layout managers
        frame.setLocationRelativeTo(null); // center JFRAME on screen/monitor center
        frame.setVisible(true); // making the frame visible
    }
}