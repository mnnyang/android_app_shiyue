package cn.xxyangyoulin.shiyue.search;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.base.BaseFragment;
import cn.xxyangyoulin.shiyue.base.FragmentBackHandler;
import cn.xxyangyoulin.shiyue.widget.TagContainerView;

public class SearchFragment extends BaseFragment implements FragmentBackHandler {

    private TagContainerView mTagContainerView;
    private ImageView mIvExit;
    private RecyclerView mRecyclerViewHistory;
    private ArrayList<String> mHistoryList;
    private EditText mEtSearch;

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
        mEtSearch = mRootView.findViewById(R.id.et_search);
        mTagContainerView = mRootView.findViewById(R.id.hot_search_container);
        mRecyclerViewHistory = mRootView.findViewById(R.id.recycler_view_history);
        mIvExit = mRootView.findViewById(R.id.iv_exit);
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

        initSearchHistory();
    }


    @Override
    public void initListener() {
        mIvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Toast.makeText(activity, "fuck", Toast.LENGTH_SHORT).show();

                // 先隐藏键盘
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideInput(getContext(), mEtSearch);
                }
                String keyWord = mEtSearch.getText().toString().trim();
                return false;
            }
        });
    }

    /**
     * 搜索历史
     */
    private void initSearchHistory() {
        mRecyclerViewHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        mHistoryList = new ArrayList<>();

        /**模拟数据*/
        for (int i = 0; i < 5; i++) {
            mHistoryList.add("搜索历史模拟");
        }

        HistoryAdapter historyAdapter = new HistoryAdapter(R.layout.adapter_search_history, mHistoryList);
        mRecyclerViewHistory.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        mRecyclerViewHistory.setAdapter(historyAdapter);
    }


    public void close() {
        super.close();
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
