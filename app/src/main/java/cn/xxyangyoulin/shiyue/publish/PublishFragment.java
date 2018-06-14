package cn.xxyangyoulin.shiyue.publish;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
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
        mLayoutEmnojiPanel = rootView.findViewById(R.id.layout_emoji_panel);
        mIvEmoji = rootView.findViewById(R.id.iv_emoji);
        mIvHeader = rootView.findViewById(R.id.iv_header);
        mEtTitle = rootView.findViewById(R.id.et_title);
        mEtContent = rootView.findViewById(R.id.et_content);
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
        transaction.hide(PublishFragment.this);
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
        }
    }

    /**
     * 切换是否需要标题
     */
    private void switchHeader() {
        mEtTitle.setVisibility(mEtTitle.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
    }
}
