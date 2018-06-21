package cn.xxyangyoulin.shiyue.data.http;

import android.text.TextUtils;

import java.io.File;

import cn.xxyangyoulin.shiyue.app.Constants;
import cn.xxyangyoulin.shiyue.util.LogUtil;

/**
 * 二级路径处理
 */
public class HandleSubpath {

    public static final String media = "file";

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

    public static String handle(String urlPath, boolean autoFile) {
        if (autoFile && urlPath != null && !urlPath.startsWith(media) && !urlPath.startsWith("/"+media)
                && !(urlPath.startsWith("http:") || urlPath.startsWith("https:"))) {

            if (urlPath.startsWith("/")) {
                urlPath = media + urlPath;
            } else {
                urlPath = media + "/" + urlPath;
            }
        }

        return handle(urlPath);
    }
}
