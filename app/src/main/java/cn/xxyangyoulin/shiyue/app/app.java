package cn.xxyangyoulin.shiyue.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import cn.xxyangyoulin.shiyue.util.Preferences;
import cn.xxyangyoulin.shiyue.util.ToastUtils;

public class app extends Application {

    private static Typeface mTypeFace;
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initUtils();

        if (mTypeFace == null) {
            mTypeFace = Typeface.createFromAsset(getAssets(), "fonts/方正中宋简体.ttf");
        }
    }

    private void initUtils() {
        Preferences.init(mContext);
        ToastUtils.init(mContext);
    }

    public static Typeface getTypeFace() {

        return mTypeFace;
    }
}
