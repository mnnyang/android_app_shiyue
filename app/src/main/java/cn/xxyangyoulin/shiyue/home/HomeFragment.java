package cn.xxyangyoulin.shiyue.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.base.BaseLazyFragment;

public class HomeFragment extends BaseLazyFragment {

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    protected void initData() {

    }
}
