import java.io.IOException;

public class MainTeatru {

    public static void main(String[] args) {
        // initialize frontend for java in java swing
        try {
            JSONShows.convertJSON();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JavaSwing.launchJavaSwing();
    }
}
