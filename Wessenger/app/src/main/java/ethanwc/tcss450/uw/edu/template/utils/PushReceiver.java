package ethanwc.tcss450.uw.edu.template.utils;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import ethanwc.tcss450.uw.edu.template.Main.MainActivity;
import ethanwc.tcss450.uw.edu.template.Messenger.MessagingHomeActivity;
import ethanwc.tcss450.uw.edu.template.R;
import me.pushy.sdk.Pushy;

import static android.app.ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
import static android.app.ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE;

public class PushReceiver extends BroadcastReceiver {

    public static final String RECEIVED_NEW_MESSAGE = "new message from pushy";
//    public static

    private static final String CHANNEL_ID = "1";

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("");


        //the following variables are used to store the information sent from Pushy
        //In the WS, you define what gets sent. You can change it there to suit your needs
        //Then here on the Android side, decide what to do with the message you got

        //for the lab, the WS is only sending chat messages so the type will always be msg
        //for your project, the WS need to send different types of push messages.
        //perform so logic/routing based on the "type"
        //feel free to change the key or type of values. You could use numbers like HTTP: 404 etc
//        Log.e("MESSAGETYPE", typeOfMessage + " alalala");
//        System.out.println("type of msg =========>  "+typeOfMessage);
        MessagingHomeActivity ho = new MessagingHomeActivity();
//System.out.println("----------<>"+context.getString(R.string.new_user_first_name));
//        System.out.println("-----"+ho.getEmail());

        //The WS sent us the name of the sender
        String sender = intent.getStringExtra("sender");
        String typeOfMessage = intent.getStringExtra("type");
        String messageText = intent.getStringExtra("message");
        String receiver = intent.getStringExtra("receiver");
        String msgtype = "" + intent.getIntExtra( "msgtype", 0);
        System.out.println("The email receiver---->"+receiver);

        ActivityManager.RunningAppProcessInfo appProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(appProcessInfo);

        if (appProcessInfo.importance == IMPORTANCE_FOREGROUND || appProcessInfo.importance == IMPORTANCE_VISIBLE) {
            //app is in the foreground so send the message to the active Activities
            Log.d("Wessenger", "Message received in foreground:"+messageText);

            Log.e("MESSAGETYPE", "1 or 0: " + msgtype);
            //create an Intent to broadcast a message to other parts of the app.
            Intent i = new Intent(RECEIVED_NEW_MESSAGE);
            i.putExtra("SENDER", sender);
            i.putExtra("MESSAGE", messageText);
            i.putExtra("TYPE", typeOfMessage);
            i.putExtra("Receiver",receiver);
            i.putExtra("MsgType", msgtype);
            i.putExtras(intent.getExtras());

            context.sendBroadcast(i);




        } else {
            if (typeOfMessage.equals("msg")) {
            //app is in the background so create and post a notification
            Log.d("Wessenger", "Message received in background: " + messageText);
            System.out.println("Background------->");
            Intent i = new Intent(context, MainActivity.class);
            i.putExtras(intent.getExtras());

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                    i, PendingIntent.FLAG_UPDATE_CURRENT);


                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.ic_message_black_24dp)
                        .setContentTitle("Message from: " + sender)
                        .setContentText(messageText)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent);

                //research more on notifications the how to display them
                //https://developer.android.com/guide/topics/ui/notifiers/notifications


                // Automatically configure a Notification Channel for devices running Android O+
                Pushy.setNotificationChannel(builder, context);

                // Get an instance of the NotificationManager service
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

                // Build the notification and display it
                notificationManager.notify(1, builder.build());
            } else {
                Log.d("Wessenger", "Message received in background: " + messageText);
                System.out.println("Background------->");
                Intent i = new Intent(context, MainActivity.class);
                i.putExtras(intent.getExtras());

                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                        i, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.ic_message_black_24dp)
                        .setContentTitle("Contact invitation from: " + sender)
                        .setContentText(messageText)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent);

                //research more on notifications the how to display them
                //https://developer.android.com/guide/topics/ui/notifiers/notifications


                // Automatically configure a Notification Channel for devices running Android O+
                Pushy.setNotificationChannel(builder, context);

                // Get an instance of the NotificationManager service
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

                // Build the notification and display it
                notificationManager.notify(1, builder.build());
            }

        }

    }
}
