package cn.xxyangyoulin.shiyue.html5;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;

import java.io.Serializable;

import cn.xxyangyoulin.shiyue.app.Cache;
import cn.xxyangyoulin.shiyue.app.Constants;
import cn.xxyangyoulin.shiyue.app.app;
import cn.xxyangyoulin.shiyue.data.bean.BaseBean;
import cn.xxyangyoulin.shiyue.data.bean.LoginBean;
import cn.xxyangyoulin.shiyue.util.ActivityUtil;
import cn.xxyangyoulin.shiyue.util.LogUtil;
import cn.xxyangyoulin.shiyue.util.Preferences;
import cn.xxyangyoulin.shiyue.util.ToastUtils;

public class LoginJavaScriptInterface implements Serializable {

    @JavascriptInterface
    public void toast(String s) {
        ToastUtils.show(s);
    }

    @JavascriptInterface
    public void accessSucceed(String json) {
        LogUtil.i(this, json);
        Gson gson = new Gson();
        BaseBean baseBean = gson.fromJson(json, BaseBean.class);

        if (baseBean.getCode() == 1) {

            LoginBean loginBean = gson.fromJson(json, LoginBean.class);
            ToastUtils.show("登录成功！" + loginBean.getData().getUsername());

            /*解析sessionId*/
            String[] strings = Cache.newInstance().tempCookie.split(";");
            for (String string : strings) {
                if (string.startsWith("sessionid=")) {
                    String sessionId = string.replace("sessionid=", "");

                    Preferences.putString(Constants.preference_session_id, sessionId);
                    ToastUtils.show(sessionId);
                    break;
                }
            }
            /*通知登录完成*/
            sendLoginCompleted();

            /*关闭登录页面*/
            Activity topActivity = ActivityUtil.getTopActivity();
            if (topActivity != null && topActivity instanceof Html5Activity) {
                topActivity.finish();
            }
        } else {
            ToastUtils.show(baseBean.getMsg());
        }
    }

    /*通知登录完成*/
    private void sendLoginCompleted() {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(app.getContext());
        Intent intent = new Intent(Constants.INTENT_LOGIN_COMPLETED);
        localBroadcastManager.sendBroadcast(intent);
    }
}
