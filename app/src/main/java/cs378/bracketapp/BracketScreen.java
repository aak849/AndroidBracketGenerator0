package cs378.bracketapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BracketScreen extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.draw_layout);

        Intent activityFromMain = getIntent();

        //int numOfPlayers = activityFromMain.getExtras().getInt("numberOfPlayers");
    }

    public void onSendSingleElimination(View view) {
        Intent getSingleEliminationIntent = new Intent(this, SingleElimination.class);

        getSingleEliminationIntent.putExtra("numberOfPlayers", R.id.number_of_players_single_elimination);

        startActivity(getSingleEliminationIntent);

    }
}
