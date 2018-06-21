package cn.xxyangyoulin.shiyue.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import cn.xxyangyoulin.shiyue.app.Constants;
import cn.xxyangyoulin.shiyue.app.app;
import cn.xxyangyoulin.shiyue.html5.Html5Activity;
import cn.xxyangyoulin.shiyue.html5.LoginJavaScriptInterface;

/**
 * Created by mnnyang on 17-11-8.
 */

public class ActivityUtil {
    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static Activity getTopActivity() {
        return activities.get(activities.size() - 1);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static <T> T checkNotNull(T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }

    /**
     * 跳转到登录界面
     */
    public static void openLogonPage() {
        Context context = app.getContext();
        if (context == null) {
            return;
        }

        try {
            Intent intent = new Intent(context, Html5Activity.class);
            Bundle value = new Bundle();
            value.putString("url", Constants.LOGIN_URL);
            value.putString("title", "登录");

            value.putSerializable("javascript", new LoginJavaScriptInterface());
            intent.putExtra("bundle", value);
            context.startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public static void replaceFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                                 @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager, "");
        checkNotNull(fragment, "");
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commit();
    }
}
