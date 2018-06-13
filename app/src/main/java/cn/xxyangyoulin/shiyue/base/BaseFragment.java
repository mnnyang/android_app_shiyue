package cn.xxyangyoulin.shiyue.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
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


}
