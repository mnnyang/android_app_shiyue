package cn.xxyangyoulin.shiyue.util;

import android.view.View;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import java.io.PipedReader;

import cn.xxyangyoulin.shiyue.R;

public class MoreLoadHelper {
    private AVLoadingIndicatorView animView;
    private final TextView mTvInfo;
    public static String NO_MORE = "没有更多了";

    public MoreLoadHelper(View footer) {
        animView = footer.findViewById(R.id.av_loading);
        mTvInfo = footer.findViewById(R.id.iv_info);

        showInfo(NO_MORE);
    }

    public void showInfo(String info) {
        animView.hide();
        animView.setVisibility(View.GONE);
        mTvInfo.setVisibility(View.VISIBLE);
        mTvInfo.setText(info);
    }

    public void showAnim() {
        animView.setVisibility(View.VISIBLE);
        animView.show();
        mTvInfo.setVisibility(View.GONE);
        mTvInfo.setText("");
    }


}
