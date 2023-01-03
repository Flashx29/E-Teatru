import org.json.JSONObject;

import javax.swing.*;
import java.util.Iterator;
import java.util.Objects;

public class SecondFrame extends JavaSwing {
    public static void init() {
        JLabel currentShow = new JLabel("Alege Teatrul pentru opera: " + selectedShow);
        currentShow.setBounds(50, 50, 350, 20);
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
    }
}
