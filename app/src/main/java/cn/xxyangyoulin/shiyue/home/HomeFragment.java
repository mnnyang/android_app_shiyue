package cn.xxyangyoulin.shiyue.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.base.BaseLazyFragment;
import cn.xxyangyoulin.shiyue.info.ProfileFragment;
import cn.xxyangyoulin.shiyue.setting.SettingFragment;

public class HomeFragment extends BaseLazyFragment implements View.OnClickListener {

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home, null);
        return mRootView;
    }

    @Override
    protected void initData() {
        TextView tvEditProfile = mRootView.findViewById(R.id.iv_edit_user_profile);
        View layoutSetting = mRootView.findViewById(R.id.layout_setting);
        tvEditProfile.setOnClickListener(this);
        layoutSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_edit_user_profile:
                openProfileFragment();
                break;
            case R.id.layout_setting:
                openSettingFragment();
                break;
            default:
                break;
        }
    }

    private void openSettingFragment() {
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                SettingFragment.newInstance()).commit();
    }

    private void openProfileFragment() {
        ProfileFragment profileFragment = ProfileFragment.newInstance();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, profileFragment);
        transaction.commit();
    }
}
