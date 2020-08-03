package com.andro.unityplatformplugin;

import android.app.Activity;
import android.content.Intent;

public class AndroActivity {
    public static Activity activity;

    public static int REQUEST_AUTH_GOOGLE = 1;
    public static int REQUEST_AUTH_FACEBOOK = 2;
    public static int REQUEST_CODE = 0;

    private static AndroAuthPlugin androAuthPlugin;
    private static AndroPlugin androPlugin;

    public static void Initialize()
    {
        androAuthPlugin = new AndroAuthPlugin();
        androPlugin = new AndroPlugin();
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(REQUEST_CODE == REQUEST_AUTH_GOOGLE)
        {
            AndroAuthProvider provider = AuthProviderExtention.GetAuthProvider("google");
            if (provider != null)
                provider.onActivityResult(requestCode, resultCode, data);
        }
        else if(REQUEST_CODE == REQUEST_AUTH_FACEBOOK)
        {
            AndroAuthProvider provider = AuthProviderExtention.GetAuthProvider("facebook");
            if (provider != null)
                provider.onActivityResult(requestCode, resultCode, data);
        }
    }
}
