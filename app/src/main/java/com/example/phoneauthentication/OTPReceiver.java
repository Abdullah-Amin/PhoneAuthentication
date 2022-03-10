package com.example.phoneauthentication;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.EditText;

import androidx.annotation.RequiresApi;

public class OTPReceiver extends BroadcastReceiver {

    @SuppressLint("StaticFieldLeak")
    public static EditText OTPEditText;

    public void setOTPEditText(EditText OTPEditText) {
        OTPReceiver.OTPEditText = OTPEditText;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for(SmsMessage smsMessage : smsMessages){
            String smsMessageBody = smsMessage.getMessageBody();
            String otp = smsMessageBody.split(" ")[0];
            OTPEditText.setText(otp);
        }
    }
}
