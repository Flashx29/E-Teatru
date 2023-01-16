import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ThirdFrame extends JavaSwing {
    public static void init() {
        int seats = Integer.parseInt(selectedSeatsNumber);
        ArrayList<Integer> currentSeats = new ArrayList<Integer>();
        for (int i = 0; i <= seats; i++) {
            JButton btn = new JButton(Integer.toString(i));
            frame.add(btn);
            int finalI = i;
            btn.addActionListener(ae -> {
                boolean add = !currentSeats.contains(finalI);
                // check if an element is in the ArrayList

                if (add) {
                    currentSeats.add(finalI);
                    btn.setOpaque(true);
                    btn.setBackground(Color.GREEN);
                } else {
                    currentSeats.remove(Integer.valueOf(finalI));
                    btn.setBackground(Color.WHITE);
                }

                System.out.println(currentSeats);
             });
            }

        // setting grid layout of 3 rows and 3 columns
        frame.setLayout(new GridLayout(3, 3));
        frame.setSize(400, 500);
        frame.setVisible(true);
    }
}

