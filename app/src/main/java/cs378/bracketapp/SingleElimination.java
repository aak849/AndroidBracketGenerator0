package cs378.bracketapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SingleElimination extends Activity{

    public String[] players;
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getBundleExtra("playerBundle");
        int selectedNumber = extras.getInt("numberOfPlayers");
        players = extras.getStringArray("playerNames");
        Log.d(TAG, "selectedNumber =" + selectedNumber);

        if(selectedNumber == 16) {
            setContentView(R.layout.bracket16);
        }

        else if(selectedNumber == 8)
            setContentView(R.layout.bracket8);
        else if(selectedNumber == 4)
            setContentView(R.layout.bracket4);
        else
            setContentView(R.layout.single_elimination_layout);

        TextView textView;
        StringBuilder textViewName = new StringBuilder();
        textViewName.append("textView");
        for(int i = 0; i < selectedNumber; i++)
        {
            textViewName.append("" + i);
            int id = getResources().getIdentifier(textViewName.toString(), "id", getPackageName());
            if (id != 0) {
                 textView = (TextView) findViewById(id);
                 textView.setText(players[i]);
            }
                textViewName.deleteCharAt(textViewName.length()-1);
            if(i>9)
                textViewName.deleteCharAt(textViewName.length()-1);
        }
        //Intent activityFromBracketScreen = getIntent();
        //if(extras != null) {
           //selectedNumber = extras.getString("selected");
        //    TextView tv = (TextView)findViewById(R.id.single_elimination_title);
            //tv.setText(selectedNumber);
        //}

        //int numOfPlayers = activityFromBracketScreen.getExtras().getInt("numberOfPlayers");

    }
}
