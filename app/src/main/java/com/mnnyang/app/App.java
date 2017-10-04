package com.mnnyang.app;

import android.app.Application;
import android.content.Context;

import com.mnnyang.utils.Preferences;
import com.mnnyang.utils.ToastUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by mnnyang on 17-10-2.
 */

public class App extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this.getApplicationContext();
        Preferences.init(context);
        ToastUtils.init(context);
    }
}
