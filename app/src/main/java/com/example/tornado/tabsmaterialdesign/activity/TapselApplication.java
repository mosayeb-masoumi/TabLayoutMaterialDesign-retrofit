package com.example.tornado.tabsmaterialdesign.activity;

import android.app.Application;

import ir.tapsell.sdk.Tapsell;
import ir.tapsell.sdk.TapsellConfiguration;

/**
 * Created by Tornado on 8/8/2018.
 */

public class TapselApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TapsellConfiguration config = new TapsellConfiguration(this);
        config.setPermissionHandlerMode(TapsellConfiguration.PERMISSION_HANDLER_DISABLED);
//        Tapsell.initialize(this, config, BuildConfig.tapsellSampleAppKey);
        Tapsell.initialize(this,config,"msrgamoorlefmbnkbjsdodtlmerqjmpihbglgfdshpioepqgjqjpseiafdcdhmjeoglhng");
    }
}
