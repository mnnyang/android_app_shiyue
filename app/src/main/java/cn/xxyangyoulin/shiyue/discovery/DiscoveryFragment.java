package cn.xxyangyoulin.shiyue.discovery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.app.Constants;
import cn.xxyangyoulin.shiyue.base.BaseLazyFragment;
import cn.xxyangyoulin.shiyue.data.bean.Poem;
import cn.xxyangyoulin.shiyue.publish.PublishFragment;
import cn.xxyangyoulin.shiyue.search.SearchFragment;
import cn.xxyangyoulin.shiyue.util.ActivityUtil;
import cn.xxyangyoulin.shiyue.util.StatusUtil;

public class DiscoveryFragment extends BaseLazyFragment implements PopupMenu.OnMenuItemClickListener {

    private BroadcastReceiver mLoginBroadcastReceiver;
    private LocalBroadcastManager mBroadcastManager;

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

        loginListener();
    }

    /**
     * 登录监听
     */
    private void loginListener() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.INTENT_LOGIN_COMPLETED);
        mLoginBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                StatusUtil.status(StatusUtil.Status.SUCCEED,
                        mRootView.findViewById(R.id.layout_content),
                        mRootView.findViewById(R.id.layout_state));
            }
        };

        mBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        mBroadcastManager.registerReceiver(mLoginBroadcastReceiver, intentFilter);
    }

    private void initToolbar() {
//        mToolbar.inflateMenu(R.menu.toolbar_discovery);
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

        DisCoveryAdapter disCoveryAdapter = new DisCoveryAdapter(R.layout.adapter_discovery_item_vertical, poemsLists);

        View headerLayout = View.inflate(getContext(), R.layout.layout_discovery_item_header, null);
        View footerLayout = View.inflate(getContext(), R.layout.adapter_normal_footer_item_loading, null);

        headerListener(headerLayout);

        ((AVLoadingIndicatorView) footerLayout.findViewById(R.id.av_loading)).smoothToShow();
        disCoveryAdapter.setHeader(headerLayout);
        disCoveryAdapter.setFooter(footerLayout);
        mRecyclerView.setAdapter(disCoveryAdapter);
    }

    private void headerListener(View headerLayout) {
        headerLayout.findViewById(R.id.iv_publish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPublish();
            }
        });

        headerLayout.findViewById(R.id.iv_filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupHeaderMenu(v);
            }
        });

        headerLayout.findViewById(R.id.layout_header).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPublish();
            }
        });
    }

    private void openPublish() {
        PublishFragment fragment = PublishFragment.newInstance();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, fragment);
        transaction.commit();
    }

    private void popupHeaderMenu(View v) {
        //创建弹出式菜单对象（最低版本11）
        PopupMenu popup = new PopupMenu(mContext, v);//第二个参数是绑定的那个view
        //获取菜单填充器
        MenuInflater inflater = popup.getMenuInflater();
        //填充菜单
        inflater.inflate(R.menu.filter_menu, popup.getMenu());
        //绑定菜单项的点击事件
        popup.setOnMenuItemClickListener(DiscoveryFragment.this);
        //显示(这一行代码不要忘记了)
        popup.show();
    }

    private void search() {
        SearchFragment searchFragment = SearchFragment.newInstance();
        ActivityUtil.replaceFragmentToActivity(getFragmentManager(), searchFragment, android.R.id.content);
    }

    private void publish() {
        PublishFragment publishFragment = PublishFragment.newInstance();
        ActivityUtil.replaceFragmentToActivity(getFragmentManager(), publishFragment, android.R.id.content);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        return false;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        /*取消监听*/
        mBroadcastManager.unregisterReceiver(mLoginBroadcastReceiver);
    }
}
