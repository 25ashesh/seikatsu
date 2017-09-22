package solutions.ashesh.seikatsu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class prefereces extends AppCompatActivity implements View.OnClickListener{
private Button startNewGame;
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefereces);

        startNewGame = (Button) findViewById(R.id.btnNewGame);
        startNewGame.setOnClickListener(this);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnNewGame:
                Log.v("startnewgame","clicked clicked");

                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("new game")
                        .setMessage("Are you sure to start a new game?")
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                mHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startNewGameScreen();
                                    }
                                },1000);
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
        }
    }

    private void startNewGameScreen(){
        Intent prefsIntent = new Intent(prefereces.this,initial.class);
        startActivity(prefsIntent);
        finish();
    }
}
