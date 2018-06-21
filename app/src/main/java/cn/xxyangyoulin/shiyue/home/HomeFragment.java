package cn.xxyangyoulin.shiyue.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import cn.xxyangyoulin.shiyue.LoginEvent;
import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.UpdateUserEvent;
import cn.xxyangyoulin.shiyue.app.Cache;
import cn.xxyangyoulin.shiyue.app.Constants;
import cn.xxyangyoulin.shiyue.base.BaseLazyFragment;
import cn.xxyangyoulin.shiyue.data.bean.UserWrapper;
import cn.xxyangyoulin.shiyue.data.http.HandleSubpath;
import cn.xxyangyoulin.shiyue.html5.Html5Activity;
import cn.xxyangyoulin.shiyue.html5.LoginJavaScriptInterface;
import cn.xxyangyoulin.shiyue.profile.ProfileFragment;
import cn.xxyangyoulin.shiyue.setting.SettingFragment;
import cn.xxyangyoulin.shiyue.util.ActivityUtil;
import cn.xxyangyoulin.shiyue.util.LogUtil;
import cn.xxyangyoulin.shiyue.util.StatusUtil;
import cn.xxyangyoulin.shiyue.util.ToastUtils;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class HomeFragment extends BaseLazyFragment implements HomeContracts.HomeView, View.OnClickListener {

    HomeContracts.HomePresenter mHomePresenter;
    private ImageView mivAvator;
    private TextView mTvToolbarTitle;

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
        View viewAvator = mRootView.findViewById(R.id.layout_avator);
        View viewPublish = mRootView.findViewById(R.id.layout_publish);
        View layoutSetting = mRootView.findViewById(R.id.layout_setting);
        mivAvator = mRootView.findViewById(R.id.civ_avator);
        mTvToolbarTitle = mRootView.findViewById(R.id.tv_toolbar_title);

        viewAvator.setOnClickListener(this);
        tvEditProfile.setOnClickListener(this);
        layoutSetting.setOnClickListener(this);
        viewPublish.setOnClickListener(this);

        EventBus.getDefault().register(this);

        new HomePresenter(this).start();
    }

    /**
     * 登录事件
     */
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void loginEvent(LoginEvent event) {
        if (event.getType() == LoginEvent.TYPE_LOGIN) {
            Toast.makeText(mContext, "登录完成", Toast.LENGTH_SHORT).show();
            StatusUtil.status(StatusUtil.SUCCEED,
                    mRootView.findViewById(R.id.layout_content),
                    mRootView.findViewById(R.id.layout_status));

            fillUserInfo(Cache.newInstance().getUser());

        } else if (event.getType() == LoginEvent.TYPE_LOGOUT) {
            mHomePresenter.updatePageView();
        }
    }

    /**
     * 用户资料更新事件
     */
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void updateUserEvent(UpdateUserEvent event) {
        LogUtil.e(this, "更新事件");
        mHomePresenter.updateUserInfo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_avator:
            case R.id.iv_edit_user_profile:
                openProfileFragment();
                break;
            case R.id.layout_setting:
                openSettingFragment();
                break;
            case R.id.layout_publish:
                System.out.println("发送");
                break;
            default:
                break;
        }
    }

    /**
     * 打开设置
     */
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


    @Override
    public void fillUserInfo(UserWrapper.User user) {
        if (user == null) {
            user = new UserWrapper.User();
        }

        RequestOptions options = new RequestOptions()
                .centerInside()
                .optionalCircleCrop()
                .placeholder(R.drawable.image_default_avator);

        String handle = HandleSubpath.handle(user.getAvator(),true);
        LogUtil.e(this,"home="+handle);
        Glide.with(this)
                .applyDefaultRequestOptions(options)
                .load(handle)
                .into(mivAvator);

        mTvToolbarTitle.setText(user.getNick_name()!=null &&!user.getNick_name().trim().isEmpty() ?
                user.getNick_name() : getString(R.string.app_name));
    }

    @Override
    public void showErrorInfo(String msg) {
        ToastUtils.show(msg);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showHomePage() {
        StatusUtil.status(StatusUtil.SUCCEED, mRootView.findViewById(R.id.layout_content),
                mRootView.findViewById(R.id.layout_status));
    }

    @Override
    public void showPleaseLoginPage() {
        StatusUtil.status(StatusUtil.ERROR,
                mRootView.findViewById(R.id.layout_content),
                mRootView.findViewById(R.id.layout_status),
                "尚未登录", "登录", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityUtil.openLogonPage();
                    }
                });
    }

    @Override
    public void setPresenter(HomeContracts.HomePresenter presenter) {
        mHomePresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*取消监听*/
        EventBus.getDefault().unregister(this);
    }

}
