package cn.xxyangyoulin.shiyue.setting;

import cn.xxyangyoulin.shiyue.base.BasePresenter;
import cn.xxyangyoulin.shiyue.base.BaseView;

public interface SettingContracts {
    interface SettingPresenter extends BasePresenter {
        void logout();
    }

    interface SettingView extends BaseView<SettingPresenter> {
        void logoutSucceed(String msg);

        void logoutFailed(String msg);

        boolean isActive();

        void showProgress(String title,String msg,boolean cancelable);
        void hideProgress();
    }
}
