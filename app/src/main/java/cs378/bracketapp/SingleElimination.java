package cs378.bracketapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SingleElimination extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.single_elimination_layout);

        Intent activityFromBracketScreen = getIntent();

        //int numOfPlayers = activityFromBracketScreen.getExtras().getInt("numberOfPlayers");

    }
}
