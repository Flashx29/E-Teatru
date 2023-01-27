import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class TicketFrame extends Print {
    public static String showDate;
    public static String showTheater;
    public static void init() {
        // Get Date from Selected Theather
        // & Split selected Theater to get date & theater
        showDate = selectedTheater.split("-")[1];
        showTheater = selectedTheater.split("-")[0];

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
            JavaSwing.launchJavaSwing();
        });


        JButton printBtn = new JButton("Printeaza Biletul");
        printBtn.setBounds(125, 370, 150, 40); // x-axis, y-axis, width, height
        frame.add(printBtn);
        printBtn.addActionListener(ae -> {
            print();
        });
    }
}
