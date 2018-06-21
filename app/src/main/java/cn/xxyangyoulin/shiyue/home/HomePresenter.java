package cn.xxyangyoulin.shiyue.home;

import android.text.TextUtils;

import cn.xxyangyoulin.shiyue.app.Cache;
import cn.xxyangyoulin.shiyue.data.api.UserInfoService;
import cn.xxyangyoulin.shiyue.data.bean.UserWrapper;
import cn.xxyangyoulin.shiyue.data.http.Client;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContracts.HomePresenter {

    private HomeContracts.HomeView mView;
    private Disposable mDisposable;

    public HomePresenter(HomeContracts.HomeView view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        updatePageView();
    }

    @Override
    public void updatePageView() {
        UserWrapper.User user = Cache.newInstance().getUser();

        if (user != null && !Cache.newInstance().getSessionId().isEmpty()) {

            if (mView.isActive()) {
                mView.showHomePage();
            }

            if (user != null) {
                mView.fillUserInfo(user);
            } else {
                updateUserInfo();
            }
            //TODO 加载缓存 信息版本比对，过时才调用更新

        } else {
            if (mView.isActive()) {
                mView.showPleaseLoginPage();
                mView.fillUserInfo(null);
            }
        }
    }

    @Override
    public void updateUserInfo() {
        mDisposable = Client.getInstance()
                .create(UserInfoService.class)
                .getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserWrapper>() {
                    @Override
                    public void accept(UserWrapper userWrapper) throws Exception {

                        /*缓存用户信息*/
                        if (userWrapper != null && userWrapper.getCode() == 1) {
                            Cache.newInstance().setUser(userWrapper.getData());
                        }

                        if (mView.isActive()) {
                            if (userWrapper != null) {
                                if (userWrapper.getCode() == 1 && userWrapper.getData() != null) {
                                    mView.fillUserInfo(userWrapper.getData());
                                }
                            } else {
                                mView.showErrorInfo("加载失败！");
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (mView.isActive()) {
                            mView.showErrorInfo("加载失败！");
                        }
                    }
                });
    }
}
