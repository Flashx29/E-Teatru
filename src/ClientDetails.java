import java.util.ArrayList;
import java.util.List;

public class ClientDetails {
    public static List<Client> client = new ArrayList<>();

    static class Client {
            private String name;
            private String phone;
            private String show;
            private String date;
            private String theater;
            private String seats;

            public Client(String name, String phone, String show, String date, String theater, String seats) {
                this.name = name;
                this.phone = phone;
                this.show = show;
                this.date = date;
                this.theater = theater;
                this.seats = seats;
            }

            public String getName() {
                return name;
            }

            public String getPhone() {
                return phone;
            }

            public String getShow() {
                return show;
            }

            public String getDate() {
                return date;
            }

            public String getTheater() {
                return theater;
            }

            public String getSeats() {
                return seats;
            }
        }

    public static void addClient(String name, String phone, String show, String date, String theater, String seats) {
        client.add(new Client(name, phone, show, date, theater, seats));
    }

    public static void showClientList() {
        System.out.println("ArrayList items: ");
        for (Client client : client) {
            System.out.println("Name: " + client.getName());
            System.out.println("Phone: " + client.getPhone());
            System.out.println("Show: " + client.getShow());
            System.out.println("Date: " + client.getDate());
            System.out.println("Theater: " + client.getTheater());
            System.out.println("Seats: " + client.getSeats());
            System.out.println("------------- NEXT CLIENT ----------------");
        }
    }
}
