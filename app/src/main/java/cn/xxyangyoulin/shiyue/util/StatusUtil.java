package cn.xxyangyoulin.shiyue.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.xxyangyoulin.shiyue.R;

public class StatusUtil {
    public static class Status {
        public static final int SUCCEED = 0;
        public static final int ERROR = 2;
    }

    public static void status(int status, View content, View statusLayout) {
        switch (status) {
            case Status.SUCCEED:
                statusLayout.setVisibility(View.GONE);
                content.setVisibility(View.VISIBLE);
                break;

            case Status.ERROR:
                statusLayout.setVisibility(View.VISIBLE);
                content.setVisibility(View.GONE);
                break;
        }
    }

    public static void status(int status, View content, View statusLayout, String msg) {
        status(status, content, statusLayout);
        TextView tvMsg = statusLayout.findViewById(R.id.tv_status_msg);
        if (tvMsg != null) {
            tvMsg.setText(msg);
        }
    }


    public static void status(int status, View content, View statusLayout, String msg,
                              String btnName, View.OnClickListener clickListener) {

        status(status, content, statusLayout, msg);

        Button btnStatus = statusLayout.findViewById(R.id.btn_status);
        if (!TextUtils.isEmpty(btnName)) {
            btnStatus.setVisibility(View.VISIBLE);
            btnStatus.setText(btnName);
            btnStatus.setOnClickListener(clickListener);
        } else {
            btnStatus.setVisibility(View.GONE);
            btnStatus.setText("");
            btnStatus.setOnClickListener(null);
        }
    }
}
