package cs378.bracketapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

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
                TableLayout tl = (TableLayout) findViewById(R.id.previousBrackets);

                int begin = 1;
                int end = usersBrackets.size();
                if(end != 0) {
                    while (begin <= end) {
                        TableRow tr = new TableRow(getApplicationContext());
                        Button b = new Button(getApplicationContext());
                        b.setText("Bracket #" + begin);
                        b.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                //System.out.println("v.getid is:- " + v.getId());
                                Log.d(TAG, "On click listener working");
                            }
                        });


                        tr.addView(b);
                        tl.addView(tr);
                        begin++;
                    }
                    for (String key : usersBrackets.keySet()) {
                        //BracketObject bracket = (BracketObject)o;
                        Map<String, Object> eachBracket = (Map<String, Object>) usersBrackets.get(key);
                        Log.d(TAG, "This thing works yuhhhh " + eachBracket.get("numPlayers"));

                    }
                    System.out.println(snapshot.getValue());
                }
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
