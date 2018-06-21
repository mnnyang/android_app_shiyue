package cn.xxyangyoulin.shiyue.app;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import cn.xxyangyoulin.shiyue.data.bean.DailyWrapper;
import cn.xxyangyoulin.shiyue.data.bean.UserWrapper;
import cn.xxyangyoulin.shiyue.util.LogUtil;
import cn.xxyangyoulin.shiyue.util.Preferences;
import cn.xxyangyoulin.shiyue.util.SerializableUtil;

public class Cache {

    private static Cache mCache;


    private String cookie;
    private String sessionId;

    private UserWrapper.User mUser;

    private List<DailyWrapper.Daily> mDailyList;

    {
        mDailyList = new ArrayList<>();
    }


    public static Cache newInstance() {
        if (mCache == null) {
            synchronized (Cache.class) {
                if (mCache == null) {
                    mCache = new Cache();
                }
            }
        }

        return mCache;
    }

    public String getCookie() {
        if (!TextUtils.isEmpty(cookie)) {
            return cookie;
        }

        cookie = Preferences.getString(Constants.preference_cookie, "");
        return cookie;
    }

    public Cache setCookie(String cookie) {
        if (cookie == null) cookie = "";
        this.cookie = cookie;
        LogUtil.d(this, "setCookie-->" + cookie);
        Preferences.putString(Constants.preference_cookie, cookie);

        /*获取Cookie*/
        String[] strings = this.cookie.split(";");

        /*解析sessionId*/
        for (String string : strings) {
            if (string.startsWith("sessionid=")) {
                String sessionId = string.replace("sessionid=", "");

                LogUtil.e(this, "setCookie-sessionid="+sessionId);
                Cache.newInstance().setSessionId(sessionId);
                break;
            }
        }

        return this;
    }

    public String getSessionId() {

        if (!TextUtils.isEmpty(sessionId)) {
            LogUtil.e(this, "session-->" + sessionId);
            return sessionId;
        }
        sessionId = Preferences.getString(Constants.preference_session_id, "");
        return sessionId;
    }

    public Cache setSessionId(String sessionId) {
        LogUtil.e(this, "setSessionId-->" + sessionId);
        this.sessionId = sessionId;
        Preferences.putString(Constants.preference_session_id, sessionId);

        return this;
    }

    public UserWrapper.User getUser() {
        if (mUser != null) {
            LogUtil.d(this, "getUser by cache-->" + mUser);
            return mUser;
        }

        Object o = new SerializableUtil().get(UserWrapper.User.class);
        if (o != null) {
            mUser = (UserWrapper.User) o;
            LogUtil.d(this, "getUser by file-->" + mUser);
        }

        return mUser;
    }

    public Cache setUser(@NonNull UserWrapper.User user) {
        mUser = user;
        SerializableUtil serializableUtil = new SerializableUtil();

        if (user != null) {
            boolean result = serializableUtil.put(user);
            LogUtil.d(this, "setUser result-->" + result);
        } else {
            serializableUtil.clear(UserWrapper.User.class);
        }

        return this;
    }

    public List<DailyWrapper.Daily> getDailyList() {
        return mDailyList;
    }
}
