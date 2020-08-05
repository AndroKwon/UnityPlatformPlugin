package com.andro.unityplatformplugin.unitymessage;

import android.util.Log;

import com.andro.unityplatformplugin.message.MessageManager;
import com.andro.unityplatformplugin.message.NativeMessage;
import com.google.gson.Gson;

public class UnityMessageReceiver {

    public static String getSync(String jsonData) {
        if (jsonData.isEmpty()) {
            return "";
        } else {
            final UnityMessage message = (UnityMessage)(new Gson()).fromJson(jsonData, (new UnityMessage()).getClass());

            String returnValue = MessageManager.getSyncDelegate(message.scheme).onSyncDelegate(jsonData, new MessageManager.MessageListener() {
                public void onSendMessage(String sendData, NativeMessage Message){

                    Log.d("AndroMainActivity", "SyncSendUnityJson : " + sendData + ", Message : " + Message);

                    UnityMessage sendMessage = (UnityMessage)(new Gson()).fromJson(sendData, (new UnityMessage()).getClass());

                    sendMessage.jsonData = Message.jsonData;
                    sendMessage.extraData = Message.extraData;
                    sendMessage.error = Message.error;

                    UnityMessageSender.getInstance().sendMessage(sendMessage);
                }
            });

            return returnValue;
        }
    }

    public static void getAsync(String jsonData) {
        if (jsonData.isEmpty()) {
            return;
        } else {
            UnityMessage message = (UnityMessage)(new Gson()).fromJson(jsonData, (new UnityMessage()).getClass());

            MessageManager.getAsyncDelegate(message.scheme).onAsyncDelegate(jsonData, new MessageManager.MessageListener() {
                public void onSendMessage(String sendData, NativeMessage Message) {

                    Log.d("AndroMainActivity", "AsyncSendUnityJson : " + sendData + ", Message : " + Message);

                    UnityMessage sendMessage = (UnityMessage)(new Gson()).fromJson(sendData, (new UnityMessage()).getClass());

                    sendMessage.jsonData = Message.jsonData;
                    sendMessage.extraData = Message.extraData;
                    sendMessage.error = Message.error;

                    UnityMessageSender.getInstance().sendMessage(sendMessage);
                }
            });
        }
    }
}
