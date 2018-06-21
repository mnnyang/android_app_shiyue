package cn.xxyangyoulin.shiyue.home;

import cn.xxyangyoulin.shiyue.base.BasePresenter;
import cn.xxyangyoulin.shiyue.base.BaseView;
import cn.xxyangyoulin.shiyue.data.bean.UserWrapper;

public interface HomeContracts {
    interface HomePresenter extends BasePresenter {
        void updateUserInfo();
        void updatePageView();
    }

    interface HomeView extends BaseView<HomePresenter> {
        void fillUserInfo(UserWrapper.User user);
        void showErrorInfo(String msg);
        boolean isActive();
        void showHomePage();
        void showPleaseLoginPage();
    }
}
