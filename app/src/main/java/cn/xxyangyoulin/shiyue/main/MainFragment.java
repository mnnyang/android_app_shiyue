package cn.xxyangyoulin.shiyue.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.app.Cache;
import cn.xxyangyoulin.shiyue.base.BaseLazyFragment;
import cn.xxyangyoulin.shiyue.base.RecyclerBaseAdapter;
import cn.xxyangyoulin.shiyue.data.bean.BaseBean;
import cn.xxyangyoulin.shiyue.data.bean.DailyWrapper;
import cn.xxyangyoulin.shiyue.data.bean.PoemWrapper;
import cn.xxyangyoulin.shiyue.main.adapter.MainAdapter;
import cn.xxyangyoulin.shiyue.poem.PoemFragment;
import cn.xxyangyoulin.shiyue.search.SearchFragment;
import cn.xxyangyoulin.shiyue.util.ActivityUtil;
import cn.xxyangyoulin.shiyue.util.MoreLoadHelper;
import cn.xxyangyoulin.shiyue.util.StatusUtil;
import cn.xxyangyoulin.shiyue.util.ToastUtils;

public class MainFragment extends BaseLazyFragment implements MainContracts.MainView, RecyclerBaseAdapter.ItemClickListener {

    private MainContracts.MainPresenter mPresenter;

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private MainAdapter mMainAdapter;
    private SwipeRefreshLayout mSwipeRefresh;
    private View mFooterLayout;
    private MoreLoadHelper mMoreLoadHelper;

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
        mSwipeRefresh = mRootView.findViewById(R.id.swipe_refresh);

        return mRootView;
    }


    @Override
    protected void initData() {
        initToolbar();
        initRecyclerView();
        initSwipeRefresh();
        loginListener();

        new MainPresenter(this).start();
    }

    private void initSwipeRefresh() {
        mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent), Color.GRAY);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.swipeRefresh();
            }
        });
    }


    /**
     * 登录监听
     */
    private void loginListener() {

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
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mMainAdapter = new MainAdapter(R.layout.adapter_main_item, Cache.newInstance().getDailyList());
        mFooterLayout = View.inflate(getContext(), R.layout.adapter_normal_footer_item_loading, null);

        /*点击footer执行加载*/
        mFooterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.pullLoad();
            }
        });

        mMoreLoadHelper = new MoreLoadHelper(mFooterLayout);

        mMainAdapter.setFooter(mFooterLayout);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        mPresenter.pullLoad();
                    }
                }
            }
        });

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

    private void openPoemDetail(DailyWrapper.Daily daily) {
        PoemFragment poemFragment = PoemFragment.newInstance(daily);
        ActivityUtil.replaceFragmentToActivity(getFragmentManager(), poemFragment, android.R.id.content);
    }

    @Override
    public void onItemLongClick(View view, RecyclerBaseAdapter.ViewHolder holder) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        /*取消监听*/
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showSucceedPage() {
        StatusUtil.status(StatusUtil.SUCCEED, mRecyclerView,
                mRootView.findViewById(R.id.layout_status));
    }

    @Override
    public void showErrorPage(String msg) {
        StatusUtil.status(StatusUtil.ERROR, mRecyclerView,
                mRootView.findViewById(R.id.layout_status), msg);
    }

    @Override
    public void loadSucceed() {
        mSwipeRefresh.setRefreshing(false);
        mMainAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshSucceed(boolean haveNewData) {
        mSwipeRefresh.setRefreshing(false);

        if (haveNewData) {
            mMainAdapter.notifyDataSetChanged();
            ToastUtils.show("刷新成功！");
        } else {
            ToastUtils.show("已经是最新！");
        }
    }

    @Override
    public void refreshFailed() {
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void showLoadingMore() {
        mMoreLoadHelper.showAnim();
    }

    @Override
    public void pullLoadSucceed(boolean haveNewData) {
        mMoreLoadHelper.showInfo(haveNewData ? "加载成功" : "没有更多数据了");
    }

    @Override
    public void pullLoadFailed() {
        mMoreLoadHelper.showInfo("加载失败！");
    }

    @Override
    public void loadFail() {
        showErrorMsg("加载失败!");
    }

    @Override
    public void showErrorMsg(String msg) {
        ToastUtils.show(msg);
    }

    @Override
    public void setPresenter(MainContracts.MainPresenter presenter) {
        mPresenter = presenter;
    }
}
