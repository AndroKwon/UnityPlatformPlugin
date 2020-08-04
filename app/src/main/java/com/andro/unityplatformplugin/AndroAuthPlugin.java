package com.andro.unityplatformplugin;

import android.util.Log;

import com.andro.unityplatformplugin.message.MessageManager;
import com.andro.unityplatformplugin.message.NativeMessage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

public class AndroAuthPlugin {

    public static String AUTH_INITIALIZE = "andro://authInitialize";
    public static String AUTH_LOGIN = "andro://login";
    public static String AUTH_LOGOUT = "andro://logout";

    private static String tryLoginProvider;
    private static String currentLoginProvider;

    public AndroAuthPlugin()
    {
        MessageManager.addSyncDelegate(AUTH_INITIALIZE, new MessageManager.SyncListener() {
            @Override
            public String onSyncDelegate(String var1, MessageManager.MessageListener var2) {
                return AndroAuthPlugin.this.Initialize(var1, var2);
            }
        });

        MessageManager.addAsyncDelegate(AUTH_LOGIN, new MessageManager.AsyncListener() {
            @Override
            public void onAsyncDelegate(String var1, MessageManager.MessageListener var2) {
                AndroAuthPlugin.this.login(var1, var2);
            }
        });

        MessageManager.addAsyncDelegate(AUTH_LOGOUT, new MessageManager.AsyncListener() {
            @Override
            public void onAsyncDelegate(String var1, MessageManager.MessageListener var2) {
                AndroAuthPlugin.this.logout(var1, var2);
            }
        });
    }

    public String Initialize(final String jsonData, final MessageManager.MessageListener messageListener)
    {
        Log.d("AndroMainActivity", "Initialize called!");

        NativeMessage message = (NativeMessage)(new Gson()).fromJson(jsonData, (new NativeMessage()).getClass());

        try
        {
            Log.d("AndroMainActivity", "Initialize message : " + message.jsonData);

            JSONObject jsonObj = new JSONObject(message.jsonData);
            String provider = jsonObj.optString("providerName");

            AndroAuthProvider authProvider = AuthProviderExtention.GetAuthProvider(provider);

            if(authProvider != null)
                authProvider.initialize(AndroActivity.activity, message.jsonData);

        } catch (JSONException e) {
            e.printStackTrace();

            return "fail";
        }

        return "success";
    }


    public void login(final String jsonData, final MessageManager.MessageListener messageListener)
    {
        Log.d("AndroMainActivity", "login called!");

        NativeMessage message = (NativeMessage)(new Gson()).fromJson(jsonData, (new NativeMessage()).getClass());

        try
        {
            Log.d("AndroMainActivity", "login message : " + message.jsonData);

            JSONObject jsonObj = new JSONObject(message.jsonData);
            tryLoginProvider = jsonObj.optString("providerName");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroAuthProvider authProvider = AuthProviderExtention.GetAuthProvider(tryLoginProvider);

        if(authProvider != null)
        {
            authProvider.login(new AndroAuthProviderCallback() {
                @Override
                public void OnCallback(boolean success, String recvData) {

                    Log.d("AndroMainActivity", "login recv!");

                    NativeMessage message = (NativeMessage)(new Gson()).fromJson(jsonData, (new NativeMessage()).getClass());

                    JsonObject object = new JsonObject();
                    object.addProperty("provider", tryLoginProvider);

                    try {
                        JSONObject recvJsonObj = new JSONObject(recvData);
                        object.addProperty("token", recvJsonObj.optString("token"));
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                    message.jsonData = object.toString();

                    currentLoginProvider = tryLoginProvider;

                    messageListener.onSendMessage(jsonData, message);
                }
            });
        }
    }

    public void logout(final String jsonData, final MessageManager.MessageListener messageListener)
    {
        Log.d("AndroMainActivity", "logout called!");

        AndroAuthProvider authProvider = AuthProviderExtention.GetAuthProvider(tryLoginProvider);

        if(authProvider != null)
        {
            authProvider.logout(new AndroAuthProviderCallback() {
                @Override
                public void OnCallback(boolean success, String recvData) {

                    Log.d("AndroMainActivity", "logout recv!");

                    NativeMessage message = (NativeMessage)(new Gson()).fromJson(jsonData, (new NativeMessage()).getClass());
                    messageListener.onSendMessage(jsonData, message);
                }
            });
        }
    }

}
