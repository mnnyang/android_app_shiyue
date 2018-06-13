package cn.xxyangyoulin.shiyue.search;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.base.BaseFragment;
import cn.xxyangyoulin.shiyue.base.FragmentBackHandler;
import cn.xxyangyoulin.shiyue.widget.TagContainerView;

public class SearchFragment extends BaseFragment implements FragmentBackHandler {

    private TagContainerView mTagContainerView;
    private ImageView mIvExit;

    public static SearchFragment newInstance() {

        Bundle args = new Bundle();

        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void initView() {
        super.initView();
        mTagContainerView = rootView.findViewById(R.id.hot_search_container);
        mIvExit = rootView.findViewById(R.id.iv_exit);
    }

    List<String> hotList = Arrays.asList("李白", "白居易",
            "满江红", "西江月", "杜甫", "李清照", "苏轼");

    @Override
    public void initData() {

        for (int i = 0; i < hotList.size(); i++) {
            TextView textView = (TextView) View.inflate(getContext(), R.layout.layout_hot_item, null);
            textView.setText(hotList.get(i));
            mTagContainerView.addView(textView);
        }
    }

    @Override
    public void initListener() {
        mIvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void close() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(0, R.anim.anim_fragment_down);
        transaction.hide(SearchFragment.this);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public boolean onBackPressed() {
        close();
        return true;
    }
}
