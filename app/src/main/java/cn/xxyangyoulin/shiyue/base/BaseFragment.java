package cn.xxyangyoulin.shiyue.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;
import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.util.LogUtil;

/**
 * Fragment 基类
 * Created by mnnyang on 17-4-11.
 */

public abstract class BaseFragment extends Fragment {
    protected View rootView;
    protected Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        LogUtil.i(this, "onCreateView");
        activity = getActivity();
        rootView = inflater.inflate(getLayout(), container, false);
        initView();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        LogUtil.i(this, "onViewCreated");
        initData();
        initListener();
    }

    protected abstract int getLayout();

    public void initView() {

    }

    public void initListener() {

    }

    public void initData() {

    }

    public void close() {
        if (isAdded()) {
            hideInput(getContext(), rootView);
        }
    }


    /**
     * 强制隐藏输入法键盘
     *
     * @param context Context
     * @param view    EditText
     */
    public static void hideInput(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void backToolbar(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });
    }
}
