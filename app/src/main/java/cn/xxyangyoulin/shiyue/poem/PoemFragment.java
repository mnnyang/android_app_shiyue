package cn.xxyangyoulin.shiyue.poem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Objects;

import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.base.BaseFragment;
import cn.xxyangyoulin.shiyue.base.FragmentBackHandler;
import cn.xxyangyoulin.shiyue.base.RecyclerBaseAdapter;
import cn.xxyangyoulin.shiyue.comment.CommentActivity;
import cn.xxyangyoulin.shiyue.data.bean.Poem;

public class PoemFragment extends BaseFragment implements FragmentBackHandler, View.OnClickListener {

    Toolbar mToolbar;
    private RecyclerView mRecyclerView;

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
        mToolbar = mRootView.findViewById(R.id.toolbar);
        mRecyclerView = mRootView.findViewById(R.id.recycler_view);

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

    @Override
    public void initData() {
        super.initData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<Poem> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(new Poem());
        }

        LayoutInflater mInflater = LayoutInflater.from(getContext());
        View headerLayout = mInflater.inflate(R.layout.layout_poem_item_header, mRecyclerView, false);
        initHeader(headerLayout);

        PoemAdapter poemAdapter = new PoemAdapter(R.layout.adapter_poem_comment_item, data);
        View footerLayout = View.inflate(getContext(), R.layout.adapter_normal_footer_item_loading, null);
        ((AVLoadingIndicatorView) footerLayout.findViewById(R.id.av_loading)).smoothToShow();

        poemAdapter.setFooter(footerLayout);
        poemAdapter.setHeader(headerLayout);
        mRecyclerView.setAdapter(poemAdapter);

        poemAdapter.setItemClickListener(new RecyclerBaseAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerBaseAdapter.ViewHolder holder) {
                startActivity(new Intent(getContext(), CommentActivity.class));
            }

            @Override
            public void onItemLongClick(View view, RecyclerBaseAdapter.ViewHolder holder) {

            }
        });
    }

    private void initHeader(View headerLayout) {
        View viewById = headerLayout.findViewById(R.id.layout_comment_sort);
        viewById.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_comment_sort:
                popupMenu(v);
                break;
            default:
                break;
        }
    }

    private void popupMenu(View v) {
        if (!isAdded()) {
            return;
        }

        PopupMenu popup = new PopupMenu(Objects.requireNonNull(getContext()), v, Gravity.TOP);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.comment_sort_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sort_time:
                        commentSortByTime();
                        break;
                    case R.id.sort_essence:
                        commentSortByEssence();
                        break;

                    default:
                        break;
                }
                return false;
            }
        });

        popup.show();
    }

    /**
     * 按精华排序
     */
    private void commentSortByEssence() {

    }

    /**
     * 安时间排序
     */
    private void commentSortByTime() {

    }
}
