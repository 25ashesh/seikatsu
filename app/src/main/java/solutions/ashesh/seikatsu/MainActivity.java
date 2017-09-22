package solutions.ashesh.seikatsu;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private Handler mHandler = new Handler();
    private Button prefsButton;
    private Button foodButton;
    public static ImageView imageView;
    private MainThread t = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView1);
        prefsButton = (Button) findViewById(R.id.prefsButton);
        foodButton=(Button) findViewById(R.id.foodButton);
        prefsButton.setOnClickListener(this);
        foodButton.setOnClickListener(this);
    }

    @Override
    protected void onResume(){
        float curTime = System.nanoTime()/1000000000f;
        Assets.timeWhenHungry=curTime+5;
        Assets.isRunning=true;

        Assets.state= Assets.GameState.HatchState;
        //Kill the notification, if any
        Intent intent = new Intent(this,MainActivity.class);
        intent.setAction(MyService.ACTION_KILL_NOTIFICATION);
        startService(intent);

        //call super class version
        super.onResume();

        t.setRunning(true);

//        if (Assets.timeWhenHungry>10){
//            imageView.setImageResource(R.drawable.creature_inside_egg);}
//        else if(Assets.timeWhenHungry>20){
//            imageView.setImageResource(R.drawable.creature_eat_1);}

    }

    @Override
    protected void onPause(){
        if (!isFinishing()){


            Intent intent = new Intent(this,MyService.class);
            intent.setAction(MyService.ACTION_START);
            startService(intent);
        }
        super.onPause();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prefsButton:
                Log.v("prefsButton", "clicked clicked");
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        goToPrefs();
                    }
                }, 1000);

                break;
            case R.id.foodButton:
                imageView.setImageResource(R.drawable.creature_eat_2);

                if (Assets.timeWhenHungry > 30) {
                    imageView.setImageResource(R.drawable.creature_walk_1);
                    imageView.setImageResource(R.drawable.creature_walk_2);
                }
                break;

        }
    }

    private void goToPrefs(){
        Intent prefsIntent = new Intent(MainActivity.this,prefereces.class);
        startActivity(prefsIntent);
    }
}

