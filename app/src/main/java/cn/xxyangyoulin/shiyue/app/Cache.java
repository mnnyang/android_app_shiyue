package cn.xxyangyoulin.shiyue.app;

import android.os.Bundle;

public class Cache {

    private static Cache mCache;

    public String tempCookie = "";

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
}
