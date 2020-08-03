package com.andro.unityplatformplugin;

import android.os.Environment;
import android.os.StatFs;

import com.andro.unityplatformplugin.message.MessageManager;
import com.andro.unityplatformplugin.message.NativeMessage;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Locale;

public class AndroPlugin {

    public static String DEVICE_INITIALIZE = "andro://initialize";
    public static String DEVICE_GET_LANGUAGE = "andro://getLanguage";
    public static String DEVICE_GET_COUNTRY = "andro://getCountry";
    public static String DEVICE_GET_STORAGE_FREE_SPACE = "andro://getDeviceStorageFreeSpace";

    public AndroPlugin()
    {
        MessageManager.addSyncDelegate(DEVICE_INITIALIZE, new MessageManager.SyncListener() {
            @Override
            public String onSyncDelegate(String var1, MessageManager.MessageListener var2) {
                return AndroPlugin.this.initialize(var1, var2);
            }
        });

        MessageManager.addSyncDelegate(DEVICE_GET_LANGUAGE, new MessageManager.SyncListener() {
            @Override
            public String onSyncDelegate(String var1, MessageManager.MessageListener var2) {
                return AndroPlugin.this.getLanguage(var1, var2);
            }
        });

        MessageManager.addSyncDelegate(DEVICE_GET_COUNTRY, new MessageManager.SyncListener() {
            @Override
            public String onSyncDelegate(String var1, MessageManager.MessageListener var2) {
                return AndroPlugin.this.getCountry(var1, var2);
            }
        });

        MessageManager.addSyncDelegate(DEVICE_GET_STORAGE_FREE_SPACE, new MessageManager.SyncListener() {
            @Override
            public String onSyncDelegate(String var1, MessageManager.MessageListener var2) {
                return AndroPlugin.this.getDeviceStorageSpace(var1, var2);
            }
        });
    }

    public String initialize(final String jsonData, final MessageManager.MessageListener messageListener)
    {
        NativeMessage message = (NativeMessage)(new Gson()).fromJson(jsonData, (new NativeMessage()).getClass());
        return "";
    }

    public String  getLanguage(final String jsonData, final MessageManager.MessageListener messageListener)
    {
        return Locale.getDefault().getDisplayLanguage();
    }

    public String getCountry(final String jsonData, final MessageManager.MessageListener messageListener)
    {
        return Locale.getDefault().getCountry();
    }

    public String getDeviceStorageSpace(final String jsonData, final MessageManager.MessageListener messageListener)
    {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return Long.toString(stat.getAvailableBytes());
    }
}
