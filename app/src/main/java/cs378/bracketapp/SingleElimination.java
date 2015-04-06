package cs378.bracketapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SingleElimination extends Activity{

    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String selectedNumber = extras.getString("selected");

        if(selectedNumber.equalsIgnoreCase("16")) {
            Log.d(TAG, "selectedNumber =" + selectedNumber);
            setContentView(R.layout.bracket16);
        }

        else if(selectedNumber.equalsIgnoreCase("8"))
            setContentView(R.layout.bracket8);
        else
            setContentView(R.layout.single_elimination_layout);

        //Intent activityFromBracketScreen = getIntent();
        //if(extras != null) {
           //selectedNumber = extras.getString("selected");
        //    TextView tv = (TextView)findViewById(R.id.single_elimination_title);
            //tv.setText(selectedNumber);
        //}

        //int numOfPlayers = activityFromBracketScreen.getExtras().getInt("numberOfPlayers");

    }
}
