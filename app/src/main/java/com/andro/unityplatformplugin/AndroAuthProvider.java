package com.andro.unityplatformplugin;

import android.content.Context;
import android.content.Intent;

public interface AndroAuthProvider {

    void initialize(Context context, String jsonData);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void login(AndroAuthProviderCallback callback);

    void logout(AndroAuthProviderCallback callback);
}
