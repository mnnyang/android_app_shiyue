package com.mnnyang.home.home;

import android.content.Context;
import android.support.annotation.NonNull;

import static com.mnnyang.utils.MainUtils.checkNotNull;


/**
 * Created by mnnyang on 17-10-2.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mHomeView;
    private Context mContext;

    public HomePresenter(@NonNull Context context, @NonNull HomeContract.View homeView) {
        mHomeView = checkNotNull(homeView, "tasksView cannot be null!");
        mHomeView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
