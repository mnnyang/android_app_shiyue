package cn.xxyangyoulin.shiyue.main;

import cn.xxyangyoulin.shiyue.base.BasePresenter;
import cn.xxyangyoulin.shiyue.base.BaseView;

public interface MainContracts {
    interface MainPresenter extends BasePresenter {

        void load(int count);

        void swipeRefresh();

        void pullLoad();
    }

    interface MainView extends BaseView<MainContracts.MainPresenter> {

        boolean isActive();

        void showSucceedPage();

        void showErrorPage(String msg);

        void loadSucceed();

        void showErrorMsg(String msg);

        void refreshSucceed(boolean haveNewData);

        void refreshFailed();

        void showLoadingMore();
        void pullLoadSucceed(boolean haveNewData);

        void pullLoadFailed();

        void loadFail();


    }
}
