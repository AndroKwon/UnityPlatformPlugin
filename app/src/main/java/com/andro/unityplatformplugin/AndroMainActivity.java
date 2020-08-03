package com.andro.unityplatformplugin;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.unity3d.player.UnityPlayerActivity;

public class AndroMainActivity extends UnityPlayerActivity {

    protected void onCreate(Bundle var1) {
        super.onCreate(var1);

        AndroActivity.Initialize();
        AndroActivity.activity = this;

        Log.d("AndroMainActivity", "onCreate called!");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        AndroActivity.onActivityResult(requestCode, resultCode, data);
    }
}
