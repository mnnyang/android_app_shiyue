package com.mnnyang.app;

import android.app.Application;
import android.content.Context;

import com.mnnyang.data.source.remote.dep.HttpInterceptor;
import com.mnnyang.utils.Preferences;
import com.mnnyang.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

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

        initOkHttpUtils();
    }

    private void initOkHttpUtils() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new HttpInterceptor())
                .addInterceptor(new LoggerInterceptor("okhttp-oo", true))
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
