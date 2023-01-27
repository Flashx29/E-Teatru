import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class ThirdFrame extends JavaSwing {
    public static ArrayList<Integer> currentSeats = new ArrayList<Integer>();
    public static void init() {
        int seatsAsked = Integer.parseInt(selectedSeatsNumber);
        int maxSeats = 20;

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
                    // set user seats and transform integer array into a string
                    selectedUserSeats = currentSeats.stream().map(Object::toString).collect(Collectors.joining(", "));
                    createPaymentPanel();
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

    public static Integer getTicketPrice() {
        for (int i = 0;i <JSONShows.shows.length();i++) {
            JSONObject show_obj = JSONShows.shows.getJSONObject(i);
            String showTitle = show_obj.getString("title");
            if (Objects.equals(showTitle, selectedShow)) {
                // return ticket price for selectedShow as integer
                return Integer.parseInt(show_obj.getString("ticket"));
            }
        }
        return 0;
    }

    public static void createPaymentPanel() {
        JTextField name = new JTextField(5);
        JTextField phoneNumber = new JTextField(5);
        JTextField moneyInput = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.setSize(200, 300);
        myPanel.setLayout((new BoxLayout (myPanel, BoxLayout.Y_AXIS)));

        // Show Number of Tickets & Price for a ticket
        myPanel.add(new JLabel("Pret per bilet: " + getTicketPrice()));
        myPanel.add(new JLabel("Numar de locuri dorite: " + selectedSeatsNumber));

        // Calculate price according to number of selected seats
        int finalSeatsPrice = Integer.parseInt(selectedSeatsNumber) * getTicketPrice();
        myPanel.add(new JLabel("Total pret pentru bilete: " + String.valueOf(finalSeatsPrice)));
        myPanel.add(Box.createVerticalStrut(10)); // a spacer

        // User Info
        myPanel.add(new JLabel("Nume:"));
        myPanel.add(name);
        myPanel.add(Box.createVerticalStrut(10)); // a spacer
        myPanel.add(new JLabel("Telefon:"));
        myPanel.add(phoneNumber);

        // Ask user to input his money
        myPanel.add(Box.createVerticalStrut(10)); // a spacer
        myPanel.add(new JLabel("Introdu banii:"));
        myPanel.add(moneyInput);


        int result = JOptionPane.showConfirmDialog(
                null, myPanel,
                "Te rog introdu urmatoarele date:",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_CANCEL_OPTION) {
            cancelPayment();
        }

        if (result == JOptionPane.OK_OPTION) {
            userName = name.getText();
            userPhone = phoneNumber.getText();
            userMoney = Integer.parseInt(moneyInput.getText());
            checkUserPayment(userMoney, finalSeatsPrice);
        }
    }

    public static void checkUserPayment(int userMoney, int finalSeatsPrice) {   // Check seats price with user input money
        userReset = userMoney - finalSeatsPrice;
        if (userMoney < finalSeatsPrice) {
            System.out.println("din nou prea putin");
            paymentNotFulfilled(finalSeatsPrice);
            return;
        }
        System.out.println("passed 3, init ticket frame");
        Utils.clearFrame();
        TicketFrame.init();
    }
    public static void paymentNotFulfilled(int finalSeatsPrice) {
        JPanel pricePanel = new JPanel();
        pricePanel.setSize(200, 300);
        pricePanel.setLayout((new BoxLayout (pricePanel, BoxLayout.Y_AXIS)));

        pricePanel.add(new JLabel("Suma introdusa nu este corecta"));
        pricePanel.add(Box.createVerticalStrut(10)); // a spacer
        pricePanel.add(new JLabel("Suma introdusa:" + userMoney));
        pricePanel.add(new JLabel("Suma totala si ceruta:" + finalSeatsPrice));

        // Try again on money
        pricePanel.add(Box.createVerticalStrut(10)); // a spacer
        pricePanel.add(new JLabel("Introdu banii:"));
        JTextField moneyInput = new JTextField(5);
        pricePanel.add(moneyInput);

        int paymentResult = JOptionPane.showConfirmDialog(
                null, pricePanel,
                "Reincearca plata:",
                JOptionPane.OK_CANCEL_OPTION);

        if (paymentResult == JOptionPane.OK_CANCEL_OPTION) {
            cancelPayment();
        }

        if (paymentResult == JOptionPane.OK_OPTION) {
            userMoney = Integer.parseInt(moneyInput.getText());
            checkUserPayment(userMoney, finalSeatsPrice);
        }
    }

    public static void cancelPayment() {
        // reset third frame if user cancels
        System.out.println("ThirdFrame reset");
        Utils.clearFrame();
        ThirdFrame.init();
        currentSeats.clear();
    }
}

