import org.json.JSONObject;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Objects;

public class SecondFrame extends JavaSwing {
    public static void init() {
        JLabel currentShow = new JLabel("Alege Teatrul pentru opera: " + selectedShow);
        currentShow.setBounds(40, 50, 350, 20);
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

        // Get Selected Item from List and Replace Label with it
        Utils.createSelectedShowLabel("Teatru Selectat");
        showsList.addListSelectionListener(evt -> {
            if (evt.getValueIsAdjusting())
                return;
            selectedTheater = showsList.getSelectedValue();
            Utils.selectedLabel.setText("Teatru Selectat: " + selectedTheater);
        });

        // Seat Reservation
        JLabel seatsLabel = new JLabel("Locuri:");
        seatsLabel.setBounds(75, 385, 50, 30);
        frame.add(seatsLabel);
        JTextField seats = new JTextField();
        seats.setBounds(125, 385, 50, 30);
        frame.add(seats);
        // Create Event Listener for Seats Field, get number of seats for the user
        JButton nextBtn = new JButton("Next");
        nextBtn.setBounds(150, 420, 100, 40); // x-axis, y-axis, width, height
        frame.add(nextBtn);
        nextBtn.addActionListener(ae -> {
            if (seats.getText().length() == 0) {
                return;
            }
            selectedSeatsNumber = seats.getText();
            System.out.println("passed 2");
            Utils.clearFrame();
            ThirdFrame.init();
        });
    }
}
