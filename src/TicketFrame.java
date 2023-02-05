import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class TicketFrame extends Print {
    public static String showDate;
    public static String showTheater;
    public static void init() {
        // Get Date from Selected Theather
        // & Split selected Theater to get date & theater
        showDate = selectedTheater.split("-")[1];
        showTheater = selectedTheater.split("-")[0];

        // Save this User
        ClientDetails.addClient(userName, userPhone, selectedShow, showDate, showTheater, selectedUserSeats);
        ClientDetails.showClientList();

        JLabel title = new JLabel("Bilet");
        title.setFont(new Font("Serif", Font.PLAIN, 24));
        title.setBounds(45, 20, 70, 50);
        frame.add(title);

        JLabel ticketShow = new JLabel("Spectacol: " + selectedShow);
        ticketShow.setBounds(45, 70, 350, 20);
        frame.add(ticketShow);

        JLabel ticketTheater = new JLabel("Teatrul: " + showTheater);
        ticketTheater.setBounds(45, 100, 350, 20);
        frame.add(ticketTheater);

        JLabel ticketDate = new JLabel("Data si Ora: " + showDate);
        ticketDate.setBounds(45, 130, 350, 20);
        frame.add(ticketDate);

        JLabel ticketSeats = new JLabel("Locurile: " + selectedUserSeats);
        ticketSeats.setBounds(45, 160, 350, 20);
        frame.add(ticketSeats);

        JButton finish = new JButton("Finish");
        finish.setBounds(150, 420, 100, 40);
        frame.add(finish);
        finish.addActionListener(ae -> {
            // Reset to First Screen
            System.out.println("Finished.");
            Utils.clearFrame();

            if (userRest != 0) {
                returnUserRest();
            } else {
                loadingMessage();
            }
            // Timeout in Java Swing to show User Rest on Interface
            Timer timer = new Timer(2500, arg0 -> {
                Utils.clearFrame();
                JavaSwing.launchJavaSwing();
                Utils.resetToDefault();
            });
            timer.setRepeats(false); // Only execute once
            timer.start();
        });


        JButton printBtn = new JButton("Printeaza Biletul");
        printBtn.setBounds(125, 370, 150, 40); // x-axis, y-axis, width, height
        frame.add(printBtn);
        printBtn.addActionListener(ae -> {
            print();
        });
    }

    public static void returnUserRest() {
        JLabel rest = new JLabel("Se returneaza restul in valoare de " + userRest);
        rest.setFont(new Font("Serif", Font.PLAIN, 20));
        rest.setBounds(50, 200, 350, 40);
        frame.add(rest);
    }

    public static void loadingMessage() {
        JLabel loadingMessage = new JLabel("Alegeti spectacolul la care vreti sa mergeti");
        loadingMessage.setFont(new Font("Serif", Font.PLAIN, 18));
        loadingMessage.setBounds(50, 200, 350, 40);
        frame.add(loadingMessage);
    }
}
