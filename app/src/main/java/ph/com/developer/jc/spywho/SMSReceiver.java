package ph.com.developer.jc.spywho;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * Created by john_dongalen on 7/15/2016.
 */
public class SMSReceiver extends BroadcastReceiver {

    private String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                if (messages.length > -1) {
                    Log.wtf("SmsReceiver", "Message recieved: " + messages[0].getMessageBody());
                    Log.wtf("SmsReceiver", "Contact Number: " + messages[0].getOriginatingAddress());
                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(messages[0].getTimestampMillis());
                    Log.wtf("SmsReceiver", "Date: " + formatter.format(calendar.getTime()));
                    Log.wtf("SmsReceiver", "Sim ID: " + messages[0].getIndexOnSim());
                    Log.wtf("SmsReceiver", "Receiver: " + messages[0].getDisplayOriginatingAddress());
                }
            }
        }
    }
}
