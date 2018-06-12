package cn.xxyangyoulin.shiyue.app;

import android.app.Application;
import android.content.Context;

import cn.xxyangyoulin.shiyue.util.Preferences;
import cn.xxyangyoulin.shiyue.util.ToastUtils;

public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initUtils(getApplicationContext());
    }

    private void initUtils(Context context) {
        Preferences.init(context);
        ToastUtils.init(context);
    }
}
