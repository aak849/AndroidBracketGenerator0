package cs378.bracketapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GlobalState globalState = (GlobalState) getApplicationContext();
        Log.d(TAG, globalState.getUid() + "AliAlisli");
        String uid = globalState.getUid();
        if(globalState.getUid() != null)
        if(!globalState.getUid().equalsIgnoreCase("nouid"))
            generatePreviousBracketButtons(uid);

//        Firebase.setAndroidContext(this);


    }

    public void generatePreviousBracketButtons(String uid) {
        Log.d(TAG, "prepared to generate buttons");

        Firebase userRef = new Firebase("https://androidbracket.firebaseio.com/users/" + uid +"/brackets");
        final GlobalState globalState = (GlobalState) getApplicationContext();
        //Map<String, Object> userBrackets = globalState.getUserBrackets();

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Map<String, Object> usersBrackets = (Map<String, Object>) snapshot.getValue();
                globalState.setUserBrackets(usersBrackets);
                List<String> list = new ArrayList<String>();
                for(String key : usersBrackets.keySet())
                {
                    list.add(key);
                    //BracketObject bracket = (BracketObject)o;
                    //Map<String, Object> eachBracket = (Map<String, Object>) usersBrackets.get(key);
                    //Log.d(TAG, "This thing works yuhhhh " + eachBracket.get("numPlayers"));

                }
                globalState.setBracketKeys(list);
                TableLayout tl = (TableLayout) findViewById(R.id.previousBrackets);
                int buttonsCreated = globalState.getButtonsCreated();
                int begin = 0;
                int end = usersBrackets.size();

                if(buttonsCreated != 0 && end != 0)
                    begin = end - 1;

                if(end != 0) {
                    while (begin < end) {
                            TableRow tr = new TableRow(getApplicationContext());
                            Button b = new Button(getApplicationContext());
                            b.setText("Bracket #" + begin);

                            int num = begin;
                            b.setId(begin);//Integer.parseInt(list.get(begin)));
                            b.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    // TODO Auto-generated method stub
                                    //System.out.println("v.getid is:- " + v.getId());
                                    Log.d(TAG, "On click listener working");
                                    final GlobalState globalState = (GlobalState) getApplicationContext();
                                    Map<String, Object> userBrackets = globalState.getUserBrackets();
                                    List<String> bracketKeys = globalState.getBracketKeys();
                                    Intent getSingleEliminationIntent = new Intent(getApplicationContext(), SingleElimination.class);
                                    LinearLayout layout = (LinearLayout) findViewById(R.id.player_layout);
                                    Bundle playerBundle = new Bundle();
//                                // Places all of the player names into a String Array
                                    Map<String, Object> thisBracket = (Map<String, Object>) userBrackets.get(bracketKeys.get(v.getId()));
                                    List<String> players = (List<String>) thisBracket.get("playerNames");
                                    int numPlayers = players.size();//Integer.parseInt((String)thisBracket.get("numPlayers"));
                                    String[] playerNames = new String[numPlayers];
                                    for (int i = 0; i < numPlayers; i++) {
//                                    //EditText tempText = (EditText) layout.getChildAt(i);
                                        playerNames[i] = players.get(i);//tempText.getText().toString();
//                                    //players.add(playerNames[i]);
                                    }
                                    playerBundle.putStringArray("playerNames", playerNames);
                                    playerBundle.putInt("numberOfPlayers", numPlayers);
                                    getSingleEliminationIntent.putExtra("playerBundle", playerBundle);
                                    startActivity(getSingleEliminationIntent);

                                }
                            });


                            tr.addView(b);
                            tl.addView(tr);
                            begin++;
                            buttonsCreated++;
                    }


                    for (String key : usersBrackets.keySet()) {
                        //BracketObject bracket = (BracketObject)o;
                        Map<String, Object> eachBracket = (Map<String, Object>) usersBrackets.get(key);
                        Log.d(TAG, "This thing works yuhhhh " + eachBracket.get("numPlayers"));

                    }
                    System.out.println(snapshot.getValue());
                }
                globalState.setButtonsCreated(buttonsCreated);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d(TAG, "The read failed: " + firebaseError.getMessage());
            }
        });
//        Map<String, Object> userBrackets = globalState.getUserBrackets();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//        mTlayout = (TableLayout) findViewById(R.id.mTlayout);
//
//        int i = 0;
//        while (i < mTextofButton.length) {
//            if (i % 3 == 0) {
//                tr = new TableRow(this);
//                mTlayout.addView(tr);
//            }
//            Button btn = new Button(this);
//            btn.setText(mTextofButton[i]);
//            btn.setId(i);
//            btn.setOnClickListener(new OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    // TODO Auto-generated method stub
//                    System.out.println("v.getid is:- " + v.getId());
//                }
//            });
//            tr.addView(btn);
//            i++;
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onGetBracketClick(View view) {
        Intent getBracketScreenIntent = new Intent(this, BracketScreen.class);

        final int result = 1;
        Bundle loginBundle = getIntent().getBundleExtra("loginBundle");
        getBracketScreenIntent.putExtra("loginBundle", loginBundle);

        startActivity(getBracketScreenIntent);

    }

    public void onGetHelpClick(View view) {
        Intent getHelpScreenIntent = new Intent(this, HelpScreen.class);

        startActivity(getHelpScreenIntent);

    }
}
