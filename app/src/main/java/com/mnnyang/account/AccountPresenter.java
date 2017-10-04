package com.mnnyang.account;

/**
 * Created by mnnyang on 17-10-3.
 */

public class AccountPresenter implements AccountContract.Presenter {
    private AccountContract.View mAccountView;

    public AccountPresenter(AccountContract.View accountView) {
        mAccountView = accountView;
        mAccountView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
