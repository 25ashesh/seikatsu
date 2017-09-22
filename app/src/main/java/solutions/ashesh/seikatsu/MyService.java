package solutions.ashesh.seikatsu;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyService extends MyIntentService{
    public static final String ACTION_START ="solutions.ashesh.seikatsu.START";
    public static final String ACTION_KILL_NOTIFICATION="solutions.ashesh.seikatsu.KILL_NOTIFICATION";

    final int NOTIFICATION_ID=1;

    NotificationManager mNotificationManager;

    Notification mNotification = null;

    public MyService() {
        super("MyService");
    }
    @Override
    public void onCreate(){
        super.onCreate();

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Assets.serviceIsProcessing=false;
    }@Override
    public void onDestroy(){
        mNotificationManager=null;
        super.onDestroy();
    }
    @Override
    protected void onHandleIntent(Intent intent){
        String action = intent.getAction();

        if (action.equals(ACTION_START)){
            processStart();
        }
        if (action.equals(ACTION_KILL_NOTIFICATION)){
            processStopNotify();
        }
    }
    private void processStart(){
        float curTime;

        if(Assets.serviceIsProcessing){
            Log.i("Project Logging","service is already proccessing");
            return;

        }
        //busy wait until time when hungry is reached
        Assets.serviceIsProcessing=true;
        do{
            curTime=System.nanoTime()/1000000000f;
        } while(curTime<Assets.timeWhenHungry);
        Assets.serviceIsProcessing=false;

        Intent intent = new Intent(this,MainActivity.class);

        Notify();

    }
    private void Notify(){
        Intent resultIntent = new Intent(this,MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                this,
                0,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent)
                .setSmallIcon(R.drawable.notificationicon)
                .setContentTitle("Seikatsu")
                .setContentText("Feed me");

        //sets an id for the notification
        int mNotificationid=999;
        //gets an nstance of the notification manager service
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //builds the notification and issues it
        manager.notify(mNotificationid,mBuilder.build());
    }
    private void processStopNotify(){
        mNotificationManager.cancel(NOTIFICATION_ID);
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
