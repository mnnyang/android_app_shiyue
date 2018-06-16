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
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.base.BaseLazyFragment;
import cn.xxyangyoulin.shiyue.base.RecyclerBaseAdapter;
import cn.xxyangyoulin.shiyue.data.bean.Poem;
import cn.xxyangyoulin.shiyue.main.adapter.MainAdapter;
import cn.xxyangyoulin.shiyue.poem.PoemFragment;
import cn.xxyangyoulin.shiyue.search.SearchFragment;
import cn.xxyangyoulin.shiyue.util.ActivityUtil;

public class MainFragment extends BaseLazyFragment implements RecyclerBaseAdapter.ItemClickListener {

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private MainAdapter mMainAdapter;

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
            Poem baseBean = new Poem();
            baseBean.setCode(i);
            poemsLists.add(baseBean);
        }

        mMainAdapter = new MainAdapter(R.layout.adapter_main_item, poemsLists);
        View footerLayout = View.inflate(getContext(), R.layout.adapter_normal_footer_item_loading, null);
        ((AVLoadingIndicatorView) footerLayout.findViewById(R.id.av_loading)).smoothToShow();
        mMainAdapter.setFooter(footerLayout);

        mMainAdapter.setItemClickListener(this);

        mRecyclerView.setAdapter(mMainAdapter);
    }

    private void search() {
        SearchFragment searchFragment = SearchFragment.newInstance();
        ActivityUtil.replaceFragmentToActivity(getFragmentManager(), searchFragment, android.R.id.content);
    }

    @Override
    public void onItemClick(View view, RecyclerBaseAdapter.ViewHolder holder) {
        openPoemDetail(mMainAdapter.getData().get(holder.getAdapterPosition()));
    }

    private void openPoemDetail(Poem poem) {
        Toast.makeText(mContext, poem.getCode()+"", Toast.LENGTH_SHORT).show();
        PoemFragment poemFragment = PoemFragment.newInstance();
        ActivityUtil.replaceFragmentToActivity(getFragmentManager(), poemFragment, android.R.id.content);
    }

    @Override
    public void onItemLongClick(View view, RecyclerBaseAdapter.ViewHolder holder) {

    }
}
