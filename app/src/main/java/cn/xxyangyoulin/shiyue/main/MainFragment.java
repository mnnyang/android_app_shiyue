package cn.xxyangyoulin.shiyue.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.base.BaseLazyFragment;
import cn.xxyangyoulin.shiyue.data.bean.Poem;
import cn.xxyangyoulin.shiyue.main.adapter.MainAdapter;
import cn.xxyangyoulin.shiyue.publish.PublishFragment;
import cn.xxyangyoulin.shiyue.search.SearchFragment;
import cn.xxyangyoulin.shiyue.util.ActivityUtil;

public class MainFragment extends BaseLazyFragment {

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_main, null);
        mRecyclerView = mRootView.findViewById(R.id.recycler_view);
        mToolbar = mRootView.findViewById(R.id.toolbar);
        return mRootView;
    }

    @Override
    protected void initData() {
        initToolbar();
        initRecyclerView();
    }

    private void initToolbar() {
        mToolbar.inflateMenu(R.menu.toolbar_main);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbar_search:
                        search();
                        return true;
                }
                return false;
            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<Poem> poemsLists = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            poemsLists.add(new Poem());
        }

        MainAdapter mainAdapter = new MainAdapter(R.layout.layout_main_item, poemsLists);
        mRecyclerView.setAdapter(mainAdapter);
    }

    private void search() {
        SearchFragment searchFragment = SearchFragment.newInstance();
        ActivityUtil.replaceFragmentToActivity(getFragmentManager(), searchFragment, android.R.id.content);
    }
}
