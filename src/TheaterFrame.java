import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Objects;

public class TheaterFrame extends JavaSwing {
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
                    showDetailsList.addElement(placeKeyTitle + " - " + placeValueData);
                }
            }
        }
        JList<String> showsList = new JList<>(showDetailsList);
        showsList.setBounds(100,100, 200,200);
        frame.add(showsList);
        // END SHOWS LIST

        // Max Seats Label
        JLabel informMaxSeats = new JLabel("Locuri in sala: 20");
        informMaxSeats.setBounds(75, 315, 300, 30);
        frame.add(informMaxSeats);

        // Get Selected Item from List and Replace Label with it
        Utils.createSelectedShowLabel("Teatru Selectat");
        showsList.addListSelectionListener(evt -> {
            if (evt.getValueIsAdjusting())
                return;
            selectedTheater = showsList.getSelectedValue();
            Utils.selectedLabel.setForeground(Color.BLACK);
            Utils.selectedLabel.setText("Teatru Selectat: " + selectedTheater);
        });

        // Seat Reservation
        JLabel seatsLabel = new JLabel("Locuri:");
        seatsLabel.setBounds(75, 385, 50, 30);
        frame.add(seatsLabel);

        // Make seatsInput accepts only integers
        NumberFormat format = NumberFormat.getIntegerInstance();
        JFormattedTextField seatsInput = new JFormattedTextField(format);
        seatsInput.setBounds(125, 385, 50, 30);
        frame.add(seatsInput);

        // Create Event Listener for Seats Field, get number of seats for the user
        JButton nextBtn = new JButton("Next");
        nextBtn.setBounds(150, 420, 100, 40); // x-axis, y-axis, width, height
        frame.add(nextBtn);
        nextBtn.addActionListener(ae -> {
            int seatsInputAsInteger = 0;
            if (!seatsInput.getText().isEmpty()) {
                seatsInputAsInteger = Integer.parseInt(seatsInput.getText());
            }
            boolean checkSeatsInput = seatsInput.getText().isEmpty() || seatsInputAsInteger == 0 || seatsInputAsInteger > 20;

            if (checkSeatsInput || selectedTheater == null) {
                if (checkSeatsInput) {
                    seatsInput.setText("");
                    seatsLabel.setForeground(Color.RED);

                    if (seatsInputAsInteger > 20) {
                        informMaxSeats.setForeground(Color.RED);
                    }
                }

                if (selectedTheater == null) {
                    Utils.selectedLabel.setText("Selecteaza un teatru mai intai!");
                    Utils.selectedLabel.setForeground(Color.RED);
                }
                return;
            }
            selectedSeatsNumber = seatsInput.getText();
            System.out.println("passed 2");
            Utils.clearFrame();
            SeatsFrame.init();
        });
    }
}
