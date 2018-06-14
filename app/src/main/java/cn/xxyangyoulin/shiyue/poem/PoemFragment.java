package cn.xxyangyoulin.shiyue.poem;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.base.BaseFragment;
import cn.xxyangyoulin.shiyue.base.FragmentBackHandler;

public class PoemFragment extends BaseFragment implements FragmentBackHandler {

    Toolbar mToolbar;

    public static PoemFragment newInstance() {

        Bundle args = new Bundle();

        PoemFragment fragment = new PoemFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_poem;
    }

    @Override
    public void initView() {
        mToolbar = rootView.findViewById(R.id.toolbar);
        backToolbar(mToolbar);
        mToolbar.inflateMenu(R.menu.toolbar_poem);
    }

    @Override
    public void initListener() {
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbar_share:
                        share();
                        return true;
                }
                return false;
            }
        });
    }

    private void share() {
        Toast.makeText(activity, "分享", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void close() {
        super.close();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(0, R.anim.anim_fragment_down);
        transaction.hide(PoemFragment.this);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public boolean onBackPressed() {
        close();
        return true;
    }
}
