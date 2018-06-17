package cn.xxyangyoulin.shiyue.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;

import java.lang.reflect.Field;

/**
 * 对话框工具
 * Created by mnnyang on 17-4-14.
 */

public class DialogHelper {

    private ProgressDialog progressDialog;
    //方便在回调函数外拿到DialogHelper对象关闭mCustomDialog
    private AlertDialog mCustomDialog;

    /**
     * 不确定不可关闭等待对话框<br>
     */
    public void showProgressDialog(Context context, String title, String msg, boolean canceable) {
        hideCustomDialog();

        progressDialog = ProgressDialog.show(context, title, msg, true, canceable);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * 一般对话框
     */
    public void showNormalDialog(@NonNull Activity activity, @NonNull String title,
                                 @NonNull String massage, @NonNull final DialogListener listener) {
        new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(massage)
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onPositive(dialog, which);
                            }
                        })
                .setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onNegative(dialog, which);
                            }
                        })
                .show();
    }

    /**
     * List对话框
     */
    public void showListDialog(@NonNull Activity activity, @NonNull String title,
                               @NonNull String[] items, @NonNull final DialogListener listener) {

        new AlertDialog.Builder(activity)
                .setTitle(title)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onItemClick(dialog, which);
                    }
                })
                .show();
    }

    /**
     * 自定义弹框
     */
    public void showCustomDialog(@NonNull Context context, View dialogView, String title,
                                 @NonNull final DialogListener listener) {
        mCustomDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onPositive(dialog, which);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onNegative(dialog, which);
                        dialog.dismiss();
                    }
                })
                .setView(dialogView)
                .create();

        try {
            Field field = field = mCustomDialog.getClass().getDeclaredField("mAlert");
            field.setAccessible(true);

            //   获得mAlert变量的值
            Object obj = field.get(mCustomDialog);
            field = obj.getClass().getDeclaredField("mHandler");
            field.setAccessible(true);

            //   修改mHandler变量的值，使用新的ButtonHandler类
            field.set(obj, new ButtonHandler(mCustomDialog));
        } catch (Exception e) {
            e.printStackTrace();
        }

        mCustomDialog.show();
    }

    public void hideCustomDialog() {
        if (mCustomDialog != null && mCustomDialog.isShowing()) {
            mCustomDialog.dismiss();
            mCustomDialog = null;
        }
    }
}