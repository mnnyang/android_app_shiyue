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
        if (!TextUtils.isEmpty(Cache.newInstance().tempCookie)) {
            if (mView.isActive()) {
                mView.showHomePage();
            } else {
                mView.showPleaseLoginPage();
            }
        }

        //TODO 加载缓存 信息版本比对，过时才调用网咯更新
        uploadUserInfo();
    }

    @Override
    public void uploadUserInfo() {
        mDisposable = Client.getInstance()
                .create(UserInfoService.class)
                .getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserWrapper>() {
                    @Override
                    public void accept(UserWrapper userWrapper) throws Exception {
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
