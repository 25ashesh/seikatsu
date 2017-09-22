package solutions.ashesh.seikatsu;

/**
 * Created by Ashesh on 2/20/2017.
 */

public class MainThread {
private boolean isRunning=false;
    public void setRunning(boolean b) {
        isRunning = b;
    }
    public MainThread() {

    }
    public void run() {
        while (isRunning) {
            // Lock the canvas before drawing
            if (Assets.timeWhenHungry>10){
                MainActivity.imageView.setImageResource(R.drawable.creature_inside_egg);}
            else if(Assets.timeWhenHungry>20){
                MainActivity.imageView.setImageResource(R.drawable.creature_eat_1);}
        }
    }
}
