package com.example.prozone.network;

/*import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;*/

public class SmsSender {

    private static final String ACCOUNT_ID = "XXXXXXXXXXX";
    private static final String AUTH_TOKEN = "ibfdis984031";
    private static final String TAG = "SmsSender";

    public SmsSender() {
    }

    /*public void sendMessage(String to, String from){
        Twilio.init(ACCOUNT_ID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber(to),
                        new PhoneNumber(from),
                        "Welcome to Planta!")
                .create();
    }*/
}
