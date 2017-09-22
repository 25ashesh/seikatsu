package solutions.ashesh.seikatsu;

/**
 * Created by Ashesh on 2/12/2017.
 */
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.graphics.Bitmap;

public class Assets {

    public static MediaPlayer mp;
    public static SoundPool sp;

    public Bitmap background;
    public Bitmap food;
    public Bitmap prefs;
    public Bitmap creatureEgg;
    public Bitmap creature_walk1;
    public Bitmap creature_walk2;
    public Bitmap creature_eat1;
    public Bitmap creature_eat2;

    enum GameState{
        HatchState,
        AdultState,
        Hungry,
        Happy,
        Dead
    }

    static GameState state;
    public static float timeWhenHungry;
    static float happyTimer;
    static float timeWhenDoneEating;
    static float feedingTime=5.00F;
    static float maxHappyTimer = 120.00F;

    static int sound_hatch;
    static int sound_happy;
    static int sound_hungry;
    static int sound_background;

    public static volatile boolean serviceIsProcessing;
    public static boolean isRunning;

}
