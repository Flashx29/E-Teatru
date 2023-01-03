import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JSONShows {
    public static JSONArray shows;
    public static void convertJSON() throws IOException {
        // Read the JSON file into a string
        File file = new File("src/shows.json");
        FileReader reader = new FileReader(file);
        String jsonString = "";
        int c;
        while ((c = reader.read()) != -1) {
            jsonString += (char) c;
        }
        reader.close();
        // Parse the string into a JSON object
        // System.out.println(json);
        shows = new JSONArray(jsonString);
    }
}
