package cn.xxyangyoulin.shiyue.publish;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.base.BaseFragment;
import cn.xxyangyoulin.shiyue.base.FragmentBackHandler;

public class PublishFragment extends BaseFragment implements FragmentBackHandler, View.OnClickListener {

    Toolbar mToolbar;
    private View mLayoutEmnojiPanel;
    private ImageView mIvEmoji;
    private EditText mEtContent;
    private ImageView mIvHeader;
    private EditText mEtTitle;
    private View mIvClose;

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
        mToolbar = mRootView.findViewById(R.id.toolbar);
        mLayoutEmnojiPanel = mRootView.findViewById(R.id.layout_emoji_panel);
        mIvEmoji = mRootView.findViewById(R.id.iv_emoji);
        mIvHeader = mRootView.findViewById(R.id.iv_header);
        mIvClose = mRootView.findViewById(R.id.iv_close);
        mEtTitle = mRootView.findViewById(R.id.et_title);
        mEtContent = mRootView.findViewById(R.id.et_content);
//        mToolbar.inflateMenu(R.menu.toolbar_publish);
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

        mIvClose.setOnClickListener(this);
        mIvHeader.setOnClickListener(this);

        mIvEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideInput(getContext(), v);
                showEmojiPanel();
            }
        });

        mEtContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideEmojiPanel();
            }
        });
    }

    private void showEmojiPanel() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLayoutEmnojiPanel.setVisibility(View.VISIBLE);
            }
        }, 300);
    }

    private void hideEmojiPanel() {
        mLayoutEmnojiPanel.setVisibility(View.GONE);
    }


    @Override
    public void close() {
        super.close();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(0, R.anim.anim_fragment_down);
        transaction.remove(PublishFragment.this);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public boolean onBackPressed() {
        close();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_header:
                switchHeader();
                break;
            case R.id.iv_close:
                onBackPressed();
                break;
        }
    }

    /**
     * 切换是否需要标题
     */
    private void switchHeader() {
        mEtTitle.setVisibility(mEtTitle.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
    }
}
