package cn.xxyangyoulin.shiyue.info;

import java.io.File;

import cn.xxyangyoulin.shiyue.base.BasePresenter;
import cn.xxyangyoulin.shiyue.base.BaseView;
import cn.xxyangyoulin.shiyue.data.bean.UserWrapper;

public interface ProfileContracts {
    interface ProfilePresenter extends BasePresenter {
        void stop();

        void uploadAvator(File avatorFile);

        void save(UserWrapper.User user);
    }

    interface ProfileView extends BaseView<ProfilePresenter> {
        void fillDefault(UserWrapper.User user);

        void showErrorInfo(String info);

        void showProgress(String msg);

        void hideProgress();

        void uploadAvator();

        void save();

        void uploadAvatorSucceed();

        void saveSucceed();

        boolean isActive();
    }
}
