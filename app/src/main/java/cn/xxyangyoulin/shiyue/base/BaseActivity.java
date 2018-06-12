package cn.xxyangyoulin.shiyue.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.util.ActivityUtil;
import cn.xxyangyoulin.shiyue.util.LogUtil;
import cn.xxyangyoulin.shiyue.util.ToastUtils;


/**
 * Created by xxyangyoulin on 17-10-2.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG = getClass().getSimpleName();

    Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(TAG, "onCreate");

        ActivityUtil.addActivity(this);
        ButterKnife.bind(this);
    }

    /**
     * 初始化toolbar功能为返回
     *
     * @param title
     */
    protected void initBackToolbar(String title) {
        toolbar = findViewById(R.id.toolbar);
        if (toolbar == null) {
            LogUtil.e(this, "toolbar is null ");
            return;
        }

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * 跳转
     *
     * @param clzz
     */
    public void gotoActivity(Class clzz) {
        Intent intent = new Intent(this, clzz);
        startActivity(intent);
    }


    public void toast(String msg) {
        ToastUtils.show(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, "onDestroy");
        ActivityUtil.removeActivity(this);
    }
}
