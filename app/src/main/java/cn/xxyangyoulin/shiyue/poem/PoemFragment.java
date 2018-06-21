package cn.xxyangyoulin.shiyue.poem;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.app.Cache;
import cn.xxyangyoulin.shiyue.app.Constants;
import cn.xxyangyoulin.shiyue.base.BaseFragment;
import cn.xxyangyoulin.shiyue.base.FragmentBackHandler;
import cn.xxyangyoulin.shiyue.base.RecyclerBaseAdapter;
import cn.xxyangyoulin.shiyue.comment.CommentActivity;
import cn.xxyangyoulin.shiyue.data.bean.CommentWrapper;
import cn.xxyangyoulin.shiyue.data.bean.DailyWrapper;
import cn.xxyangyoulin.shiyue.data.bean.UserWrapper;
import cn.xxyangyoulin.shiyue.poem.adapter.PoemAdapter;
import cn.xxyangyoulin.shiyue.util.DialogHelper;
import cn.xxyangyoulin.shiyue.util.LogUtil;
import cn.xxyangyoulin.shiyue.util.MoreLoadHelper;
import cn.xxyangyoulin.shiyue.util.ToastUtils;

public class PoemFragment extends BaseFragment implements PoemContracts.PoemView, FragmentBackHandler, View.OnClickListener {

    private PoemContracts.PoemPresenter mPresenter;

    private String mCommentSort = "time";
    private int OnceCount = 2;

    Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ArrayList<CommentWrapper.Comment> mCommentList = new ArrayList<>();
    ;
    private MoreLoadHelper mMoreLoadHelper;
    private DailyWrapper.Daily mDaily;
    private PoemAdapter mPoemAdapter;
    private TextView mTvTitle;
    private TextView mTvAuthor;
    private TextView mTvContent;
    private TextView mTvCommentCount;
    private LinearLayoutManager mLinearLayoutManager;
    private EditText mEtInput;
    private ImageView mIvSend;
    private DialogHelper mDialogHelper;

    public static PoemFragment newInstance(Serializable object) {

        Bundle args = new Bundle();
        args.putSerializable(Constants.KEY_POEM_ARGS, object);

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
        mEtInput = mRootView.findViewById(R.id.et_input);
        mIvSend = mRootView.findViewById(R.id.iv_send);

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

        mPoemAdapter.setItemClickListener(new RecyclerBaseAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerBaseAdapter.ViewHolder holder) {
                startActivity(new Intent(getContext(), CommentActivity.class));
            }

            @Override
            public void onItemLongClick(View view, RecyclerBaseAdapter.ViewHolder holder) {

            }
        });

        mIvSend.setOnClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        initRecyclerView();

        new PoemPresenter(this).start();

        handleBundle(getArguments());
    }

    private void handleBundle(Bundle arguments) {
        Object obj = arguments.getSerializable(Constants.KEY_POEM_ARGS);

        if (obj instanceof DailyWrapper.Daily) {
            mDaily = (DailyWrapper.Daily) obj;
            fillDefault();

            LogUtil.e(this, mDaily.getTid() + "");
            initCommentList();
        } else {
            LogUtil.e(this, "I can't handle the arguments");
        }
    }

    private void initCommentList() {
        mCommentList.clear();
        mPoemAdapter.notifyDataSetChanged();
        mPresenter.loadComments(mDaily.getType(),
                mDaily.getTid(), mCommentSort, OnceCount, 1);
    }

    private void initRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        LayoutInflater mInflater = LayoutInflater.from(getContext());
        View headerLayout = mInflater.inflate(R.layout.layout_poem_item_header, mRecyclerView, false);
        initHeader(headerLayout);

        View footerLayout = View.inflate(getContext(), R.layout.adapter_normal_footer_item_loading, null);
        mMoreLoadHelper = new MoreLoadHelper(footerLayout);

        footerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMore();
            }
        });

        mPoemAdapter = new PoemAdapter(this, R.layout.adapter_poem_comment_item, mCommentList);

        mPoemAdapter.setFooter(footerLayout);
        mPoemAdapter.setHeader(headerLayout);
        mRecyclerView.setAdapter(mPoemAdapter);

        moreLoadListener();


    }

    private void moreLoadListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = mLinearLayoutManager.getChildCount();
                    int totalItemCount = mLinearLayoutManager.getItemCount();
                    int pastVisibleItems = mLinearLayoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        loadMore();
                    }
                }
            }
        });
    }

    @Override
    public void loadMore() {
        int page = mCommentList.isEmpty() ? 1 : mCommentList.size() / OnceCount + 1;
        mPresenter.loadComments(mDaily.getType(), mDaily.getTid(),
                mCommentSort, OnceCount, page);
    }

    private void initHeader(View headerLayout) {
        View viewById = headerLayout.findViewById(R.id.layout_comment_sort);
        mTvTitle = headerLayout.findViewById(R.id.tv_title);
        mTvAuthor = headerLayout.findViewById(R.id.tv_author);
        mTvContent = headerLayout.findViewById(R.id.tv_content);
        mTvCommentCount = headerLayout.findViewById(R.id.tv_comment_count);
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
            case R.id.iv_send:
                sendComment();
                break;
            default:
                break;
        }
    }

    /**
     * 发送评论
     */
    private void sendComment() {
        if (mDaily == null || mDaily.getTid() == 0) {
            ToastUtils.show("为止错误 data=null");
            return;
        }

        UserWrapper.User user = Cache.newInstance().getUser();
        if (user == null || user.getId() == 0) {
            ToastUtils.show("请先登录");
            return;
        }

        String commentInput = getCommentInput();
        if (TextUtils.isEmpty(commentInput)) {
            ToastUtils.show("不能为空");
            return;
        }

        /*关闭键盘*/
        hideInput(getContext(), mEtInput);

        CommentWrapper.Comment comment = new CommentWrapper.Comment();
        comment.setUid(user.getId());
        comment.setPid(0);
        comment.setTid(mDaily.getTid());
        comment.setType(mDaily.getType());
        comment.setContent(commentInput);

        mPresenter.putComment(comment);
    }

    private String getCommentInput() {
        return mEtInput.getText().toString().trim();
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
        if (mDaily != null && !mCommentSort.equals("hot")) {
            mCommentSort = "hot";
            initCommentList();
        }
    }

    /**
     * 安时间排序
     */
    private void commentSortByTime() {
        if (mDaily != null && !mCommentSort.equals("time")) {
            mCommentSort = "time";
            initCommentList();
        }
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public int getLastCommentId() {
        int startId = 0;
        if (mCommentList.size() != 0) {
            startId = mCommentList.get(mCommentList.size() - 1).getId();
        }
        return startId;
    }

    @Override
    public void fillDefault() {
        if (mDaily != null) {
            mTvTitle.setText(mDaily.getTitle());
            mTvAuthor.setText(mDaily.getAuthor());
            mTvContent.setText((mDaily.getType() == 0 || mDaily.getType() == 1) ?
                    mDaily.getContent().replace("|", "\n") :
                    mDaily.getContent());

            //TODO 使用推荐
            mTvCommentCount.setText("评论(" + mDaily.getCcount() + ")");
        }
    }

    @Override
    public void startLoadComments() {
        mMoreLoadHelper.showAnim();
    }

    @Override
    public void loadCommentsSucceed(ArrayList<CommentWrapper.Comment> comments) {
        mCommentList.addAll(comments);

        if (mCommentList.isEmpty()) {
            mMoreLoadHelper.showInfo("还没有人评论哦");
            return;
        }

        if (comments == null || comments.isEmpty()) {
            mMoreLoadHelper.showInfo(MoreLoadHelper.NO_MORE);
            return;
        }

        mMoreLoadHelper.showInfo("点击加载更多");
        mPoemAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadCommentsFailed() {
        mMoreLoadHelper.showInfo("加载失败！");
    }

    @Override
    public void startPutComment() {

        openCommentDialog();
    }

    private void openCommentDialog() {
        if (mDialogHelper == null) {
            mDialogHelper = new DialogHelper();
        } else {
            mDialogHelper.hideProgressDialog();
        }

        mDialogHelper.showProgressDialog(getContext(),
                "请稍等", "评论中", false);
    }

    @Override
    public void putCommentSucceed() {
        if (mDialogHelper != null) {
            mDialogHelper.hideProgressDialog();

        }
        ToastUtils.show("评论成功");
        initCommentList();

    }

    @Override
    public void putCommentFailed() {
        if (mDialogHelper != null) {
            mDialogHelper.hideProgressDialog();

        }
        ToastUtils.show("评论失败");
    }

    @Override
    public void setPresenter(PoemContracts.PoemPresenter presenter) {
        mPresenter = presenter;
    }
}
