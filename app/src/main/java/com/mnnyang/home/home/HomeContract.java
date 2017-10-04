package com.mnnyang.home.home;

import com.mnnyang.BasePresenter;
import com.mnnyang.BaseView;

/**
 * Created by mnnyang on 17-10-2.
 */

public interface HomeContract {
    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

    }
}
