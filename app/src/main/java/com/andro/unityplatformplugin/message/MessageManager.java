package com.andro.unityplatformplugin.message;

import java.util.HashMap;
import java.util.Map;

public class MessageManager {

    public interface MessageListener {
        void onSendMessage(String var1, NativeMessage var2);
    }

    public interface AsyncListener {
        void onAsyncDelegate(String var1, MessageListener var2);
    }

    public interface SyncListener {
        String onSyncDelegate(String var1, MessageListener var2);
    }

    private static Map<String, MessageManager.SyncListener> syncDelegateMap = new HashMap();
    private static Map<String, MessageManager.AsyncListener> asyncDelegateMap = new HashMap();
    private static MessageManager instance;

    private static MessageManager initialize() {
        if (instance == null) {
            instance = new MessageManager();
        }

        return instance;
    }

    public static void addSyncDelegate(String scheme, MessageManager.SyncListener event) {
        initialize();

        syncDelegateMap.put(scheme, event);
    }

    public static MessageManager.SyncListener getSyncDelegate(String scheme) {
        initialize();

        return (MessageManager.SyncListener)syncDelegateMap.get(scheme);
    }

    public static void addAsyncDelegate(String scheme, MessageManager.AsyncListener event) {
        initialize();

        asyncDelegateMap.put(scheme, event);
    }

    public static MessageManager.AsyncListener getAsyncDelegate(String scheme) {
        initialize();

        return (MessageManager.AsyncListener)asyncDelegateMap.get(scheme);
    }
}
