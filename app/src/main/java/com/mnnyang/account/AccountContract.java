package com.mnnyang.account;

import com.mnnyang.BasePresenter;
import com.mnnyang.BaseView;

/**
 * Created by mnnyang on 17-10-3.
 */

public interface AccountContract {
    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

    }
}
