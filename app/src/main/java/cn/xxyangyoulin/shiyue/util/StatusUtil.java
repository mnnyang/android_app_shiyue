package cn.xxyangyoulin.shiyue.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.xxyangyoulin.shiyue.R;

public class StatusUtil {
    public static final int SUCCEED = 0;
    public static final int ERROR = 2;

    public static void status(int status, View succeedLayout, View statusLayout) {
        switch (status) {
            case SUCCEED:
                statusLayout.setVisibility(View.GONE);
                succeedLayout.setVisibility(View.VISIBLE);
                break;

            case ERROR:
                statusLayout.setVisibility(View.VISIBLE);
                succeedLayout.setVisibility(View.GONE);
                break;
        }
    }

    public static void status(int status, View succeedLayout, View statusLayout, String msg) {
        status(status, succeedLayout, statusLayout);

        if (statusLayout == null) {
            return;
        }
        TextView tvMsg = statusLayout.findViewById(R.id.tv_status_msg);
        if (tvMsg != null) {
            tvMsg.setText(msg);
        }
    }


    public static void status(int status, View succeedLayout, View statusLayout, String msg,
                              String btnName, View.OnClickListener clickListener) {

        status(status, succeedLayout, statusLayout, msg);

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
