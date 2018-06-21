package cn.xxyangyoulin.shiyue.html5;

import android.app.Activity;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;

import java.io.Serializable;

import cn.xxyangyoulin.shiyue.LoginEvent;
import cn.xxyangyoulin.shiyue.app.Cache;
import cn.xxyangyoulin.shiyue.data.bean.BaseBean;
import cn.xxyangyoulin.shiyue.data.bean.UserWrapper;
import cn.xxyangyoulin.shiyue.util.ActivityUtil;
import cn.xxyangyoulin.shiyue.util.LogUtil;
import cn.xxyangyoulin.shiyue.util.ToastUtils;
import de.greenrobot.event.EventBus;

public class LoginJavaScriptInterface implements Serializable {

    @JavascriptInterface
    public void toast(String s) {
        ToastUtils.show(s);
    }

    @JavascriptInterface
    public void accessSucceed(String json) {

        LogUtil.i(this, json);


        if (!TextUtils.isEmpty(json)) {
            json = json.replace("&lt;", "<")
                    .replace("&gt;", ">")
                    .replace("&quot;", "\"");
        }

        Gson gson = new Gson();
        BaseBean baseBean = gson.fromJson(json, BaseBean.class);

        if (baseBean.getCode() == 1) {

            UserWrapper userWrapper = gson.fromJson(json, UserWrapper.class);
            ToastUtils.show("登录成功！" + userWrapper.getData().getUsername());

            /*缓存用户数据*/
            Cache.newInstance().setUser(userWrapper.getData());

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
        EventBus.getDefault().post(new LoginEvent().setType(LoginEvent.TYPE_LOGIN));
    }
}
