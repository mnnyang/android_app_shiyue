package cn.xxyangyoulin.shiyue.data.http;

import android.text.TextUtils;

import cn.xxyangyoulin.shiyue.app.Constants;

/**
 * 二级路径处理
 */
public class HandleSubpath {
    public static String handle(String urlPath) {
        if (!TextUtils.isEmpty(urlPath)) {

            urlPath = urlPath.trim();

            if (urlPath.startsWith("http:") || urlPath.startsWith("https:")) {
                return urlPath;

            } else if (urlPath.startsWith("/")) {
                return Constants.BASE_URL + urlPath.substring(1, urlPath.length());

            } else {
                return Constants.BASE_URL + urlPath;
            }

        } else {
            return null;
        }
    }
}
