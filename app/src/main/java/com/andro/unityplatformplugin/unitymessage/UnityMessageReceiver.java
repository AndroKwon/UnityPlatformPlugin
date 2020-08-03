package com.andro.unityplatformplugin.unitymessage;

import com.andro.unityplatformplugin.message.MessageManager;
import com.andro.unityplatformplugin.message.NativeMessage;
import com.google.gson.Gson;

public class UnityMessageReceiver {

    public static String getSync(String jsonData) {
        if (jsonData.isEmpty()) {
            return "";
        } else {
            UnityMessage message = (UnityMessage)(new Gson()).fromJson(jsonData, (new UnityMessage()).getClass());

            String returnValue = MessageManager.getSyncDelegate(message.scheme).onSyncDelegate(jsonData, new MessageManager.MessageListener() {
                public void onSendMessage(String sendJsonData, NativeMessage message) {
                    UnityMessage sendMessage = (UnityMessage)(new Gson()).fromJson(sendJsonData, (new UnityMessage()).getClass());
                    UnityMessageSender.getInstance().sendMessage(sendMessage.gameObjectName, sendMessage.responseMethodName, message);
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
                public void onSendMessage(String sendJsonData, NativeMessage message) {
                    UnityMessage sendMessage = (UnityMessage)(new Gson()).fromJson(sendJsonData, (new UnityMessage()).getClass());
                    UnityMessageSender.getInstance().sendMessage(sendMessage.gameObjectName, sendMessage.responseMethodName, message);
                }
            });
        }
    }
}
