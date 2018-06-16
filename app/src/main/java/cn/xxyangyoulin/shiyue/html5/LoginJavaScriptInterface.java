package cn.xxyangyoulin.shiyue.html5;

import android.app.Activity;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;

import java.io.Serializable;

import cn.xxyangyoulin.shiyue.data.bean.BaseBean;
import cn.xxyangyoulin.shiyue.data.bean.LoginBean;
import cn.xxyangyoulin.shiyue.util.ActivityUtil;
import cn.xxyangyoulin.shiyue.util.LogUtil;
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
            Activity topActivity = ActivityUtil.getTopActivity();
            if (topActivity != null && topActivity instanceof Html5Activity) {
                topActivity.finish();
            }
        } else {
            ToastUtils.show(baseBean.getMsg());
        }
    }
}
