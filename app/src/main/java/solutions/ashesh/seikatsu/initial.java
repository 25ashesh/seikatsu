package solutions.ashesh.seikatsu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

public class initial extends AppCompatActivity {
private Handler handleS = new Handler();
    boolean quit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        quit=false;

        handleS.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!quit)
                    splashScreen();
            }
        },3000);
    }
    private void splashScreen(){
        Intent intent = new Intent(initial.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed(){
        quit=true;
        super.onBackPressed();
    }
}
