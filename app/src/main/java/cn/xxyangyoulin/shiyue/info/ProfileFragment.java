package cn.xxyangyoulin.shiyue.info;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.base.BaseFragment;
import cn.xxyangyoulin.shiyue.base.FragmentBackHandler;

public class ProfileFragment extends BaseFragment implements FragmentBackHandler {

    Toolbar mToolbar;

    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_info;
    }

    @Override
    public void initView() {
        mToolbar = rootView.findViewById(R.id.toolbar);
        backToolbar(mToolbar);
        mToolbar.inflateMenu(R.menu.toolbar_profile);
    }

    @Override
    public void initListener() {
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbar_save:
                        save();
                        return true;
                }
                return false;
            }
        });
    }

    private void save() {

    }

    @Override
    public void close() {
        super.close();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(0, R.anim.anim_fragment_down);
        transaction.hide(ProfileFragment.this);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public boolean onBackPressed() {
        close();
        return true;
    }
}
