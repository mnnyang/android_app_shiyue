package cn.xxyangyoulin.shiyue.setting;

import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import cn.xxyangyoulin.shiyue.app.Cache;
import cn.xxyangyoulin.shiyue.app.app;
import cn.xxyangyoulin.shiyue.data.api.LoginService;
import cn.xxyangyoulin.shiyue.data.bean.BaseBean;
import cn.xxyangyoulin.shiyue.data.http.Client;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SettingPresenter implements SettingContracts.SettingPresenter {
    private SettingContracts.SettingView mView;

    public SettingPresenter(SettingContracts.SettingView view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void logout() {
        mView.showProgress("退出中", "请稍等..", false);

        Disposable subscribe = Client.getInstance()
                .create(LoginService.class)
                .logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {

                        clearUserData();

                        if (mView.isActive()) {
                            mView.hideProgress();
                            if (baseBean.getCode() == 1) {
                                mView.logoutSucceed(baseBean.getMsg());
                            } else {
                                mView.logoutFailed(baseBean.getMsg());
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();

                        if (mView.isActive()) {
                            mView.hideProgress();

                            mView.logoutFailed("注销失败");
                        }
                    }
                });
    }

    private void clearUserData() {
        Cache.newInstance().setUser(null).setSessionId("").setCookie("");
        clearWebViewCache();
    }

    // 清除cookie即可彻底清除缓存
    public void clearWebViewCache() {
        CookieSyncManager.createInstance(app.getContext());
        CookieManager.getInstance().removeAllCookie();
    }

    @Override
    public void start() {

    }
}
