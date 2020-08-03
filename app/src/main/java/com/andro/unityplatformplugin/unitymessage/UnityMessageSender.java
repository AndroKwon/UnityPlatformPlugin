package com.andro.unityplatformplugin.unitymessage;

import com.andro.unityplatformplugin.message.NativeMessage;
import com.google.gson.Gson;
import com.unity3d.player.UnityPlayer;

public class UnityMessageSender {

    private static UnityMessageSender instance;
    private String gameObjectName;
    private String responseMethodName;
    private NativeMessage message;

    public UnityMessageSender() {
    }

    public static UnityMessageSender getInstance() {
        if (instance == null) {
            instance = new UnityMessageSender();
        }

        return instance;
    }

    private void setGameObjectName(String gameObjectName) {
        this.gameObjectName = gameObjectName;
    }

    private void setResponseMethodName(String responseMethodName) {
        this.responseMethodName = responseMethodName;
    }

    private void setMessage(NativeMessage message) {
        this.message = message;
    }

    private void sendMessage() {
        if (!this.gameObjectName.isEmpty() && !this.responseMethodName.isEmpty()) {
            if (this.message == null) {
                return;
            }

            String jsonString = (new Gson()).toJson(this.message);
            UnityPlayer.UnitySendMessage(this.gameObjectName, this.responseMethodName, jsonString);
        }
    }

    public void sendMessage(String gameObjectName, String responseMethodName, NativeMessage message) {
        this.setGameObjectName(gameObjectName);
        this.setResponseMethodName(responseMethodName);
        this.setMessage(message);
        this.sendMessage();
    }
}
