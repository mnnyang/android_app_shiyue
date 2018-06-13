package cn.xxyangyoulin.shiyue.publish;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.base.BaseFragment;
import cn.xxyangyoulin.shiyue.base.FragmentBackHandler;

public class PublishFragment extends BaseFragment implements FragmentBackHandler {

    Toolbar mToolbar;

    public static PublishFragment newInstance() {

        Bundle args = new Bundle();

        PublishFragment fragment = new PublishFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_publish;
    }

    @Override
    public void initView() {
        mToolbar = rootView.findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.toolbar_publish);
    }

    @Override
    public void initListener() {
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbar_close:
                        close();
                        return true;
                }
                return false;
            }
        });
    }

    public void close() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(0, R.anim.anim_fragment_down);
        transaction.hide(PublishFragment.this);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public boolean onBackPressed() {
        close();
        return true;
    }
}
