import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ThirdFrame extends JavaSwing {
    public static void init() {
        int seatsAsked = Integer.parseInt(selectedSeatsNumber);
        int maxSeats = 20;

        ArrayList<Integer> currentSeats = new ArrayList<Integer>();
        for (int i = 0; i <= maxSeats; i++) {
            JButton btn = new JButton(Integer.toString(i));
            frame.add(btn);
            int seat = i;
            btn.addActionListener(ae -> {
                // check if current seats array includes selected seat
                boolean add = !currentSeats.contains(seat);

                // If number of selected seats is great than desired seats number return null
                if (currentSeats.size() == Integer.parseInt(selectedSeatsNumber)) {
                    btn.setOpaque(false);
                    return;
                }

                // check if an element is in the ArrayList
                if (add) {
                    currentSeats.add(seat);
                    btn.setOpaque(true);
                    btn.setBackground(Color.GREEN);
                } else {
                    currentSeats.remove(Integer.valueOf(seat));
                    btn.setBackground(Color.WHITE);
                }
             });
        }

        // setting grid layout of 3 rows and 3 columns
        frame.setLayout(new GridLayout(3, 3));
        frame.setSize(400, 500);
        frame.setVisible(true);
    }
}

