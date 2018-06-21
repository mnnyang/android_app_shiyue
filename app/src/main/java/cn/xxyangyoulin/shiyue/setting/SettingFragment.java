package cn.xxyangyoulin.shiyue.setting;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import cn.xxyangyoulin.shiyue.LoginEvent;
import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.app.Cache;
import cn.xxyangyoulin.shiyue.base.BaseFragment;
import cn.xxyangyoulin.shiyue.base.FragmentBackHandler;
import cn.xxyangyoulin.shiyue.util.DialogHelper;
import cn.xxyangyoulin.shiyue.util.ToastUtils;
import de.greenrobot.event.EventBus;

public class SettingFragment extends BaseFragment implements SettingContracts.SettingView, FragmentBackHandler, View.OnClickListener {

    private SettingContracts.SettingPresenter mPresenter;

    Toolbar mToolbar;
    private View mViewLogout;
    private DialogHelper mDialogHelper;

    public static SettingFragment newInstance() {

        Bundle args = new Bundle();

        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initView() {
        mToolbar = mRootView.findViewById(R.id.toolbar);
        mViewLogout = mRootView.findViewById(R.id.layout_logout);

        mToolbar.inflateMenu(R.menu.toolbar_publish);
    }

    @Override
    public void initListener() {
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbar_close:
                        close();
                        return true;
                }
                return false;
            }
        });

        mViewLogout.setOnClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        new SettingPresenter(this).start();
    }

    public void close() {
        super.close();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(0, R.anim.anim_fragment_down);
        transaction.hide(SettingFragment.this);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public boolean onBackPressed() {
        close();
        return true;
    }

    @Override
    public void logoutSucceed(String msg) {
        ToastUtils.show(msg);
        EventBus.getDefault().post(new LoginEvent().setType(LoginEvent.TYPE_LOGOUT));

        close();
    }

    @Override
    public void logoutFailed(String msg) {
        ToastUtils.show("注销失败！");
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showProgress(String title, String msg, boolean cancelable) {
        if (mDialogHelper == null) {
            mDialogHelper = new DialogHelper();
        } else {
            mDialogHelper.hideProgressDialog();
        }

        mDialogHelper.showProgressDialog(getContext(), title, msg, cancelable);
    }

    @Override
    public void hideProgress() {
        if (mDialogHelper != null) {
            mDialogHelper.hideProgressDialog();
        }
    }

    @Override
    public void setPresenter(SettingContracts.SettingPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_logout:
                mPresenter.logout();
                break;


        }
    }
}
