package cn.xxyangyoulin.shiyue.discovery;

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

public class DiscoveryFragment extends BaseLazyFragment {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    public static DiscoveryFragment newInstance() {

        Bundle args = new Bundle();

        DiscoveryFragment fragment = new DiscoveryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_discovery, null);
        mToolbar = mRootView.findViewById(R.id.toolbar);
        mRecyclerView = mRootView.findViewById(R.id.recycler_view);
        return mRootView;
    }

    @Override
    protected void initData() {
        initToolbar();
        initRecyclerView();
    }

    private void initToolbar() {
        mToolbar.inflateMenu(R.menu.toolbar_discovery);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbar_publish:
                        publish();
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

        DisCoveryAdapter disCoveryAdapter = new DisCoveryAdapter(R.layout.layout_discovery_item, poemsLists);
        mRecyclerView.setAdapter(disCoveryAdapter);
    }

    private void search() {
        SearchFragment searchFragment = SearchFragment.newInstance();
        ActivityUtil.replaceFragmentToActivity(getFragmentManager(), searchFragment, android.R.id.content);
    }

    private void publish() {
        PublishFragment publishFragment = PublishFragment.newInstance();
        ActivityUtil.replaceFragmentToActivity(getFragmentManager(), publishFragment, android.R.id.content);
    }
}
