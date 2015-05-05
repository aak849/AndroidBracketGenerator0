package cs378.bracketapp;

import android.app.Application;

import java.util.List;
import java.util.Map;

/**
 * Created by Development on 5/3/15.
 */
public class GlobalState extends Application {
    private String uid;
    private Map<String, Object> userBrackets;

    public int getButtonsCreated() {
        return buttonsCreated;
    }

    public void setButtonsCreated(int buttonsCreated) {
        this.buttonsCreated = buttonsCreated;
    }

    private int buttonsCreated;



    private List<String> bracketKeys;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Map<String, Object> getUserBrackets() {
        return userBrackets;
    }

    public void setUserBrackets(Map<String, Object> userBrackets) {
        this.userBrackets = userBrackets;
    }

    public List<String> getBracketKeys() {
        return bracketKeys;
    }

    public void setBracketKeys(List<String> bracketKeys) {
        this.bracketKeys = bracketKeys;
    }



}
