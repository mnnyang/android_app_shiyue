package cn.xxyangyoulin.shiyue.comment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.base.BaseActivity;
import cn.xxyangyoulin.shiyue.data.bean.Poem;
import cn.xxyangyoulin.shiyue.poem.PoemAdapter;

import static cn.xxyangyoulin.shiyue.app.app.getContext;

public class CommentActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);


        handleIntent(getIntent());
        initRecyclerView();
    }


    /**
     * 处理intent
     *
     * @param intent
     */
    private void handleIntent(Intent intent) {
        initBackToolbar("评论（234）");
    }

    private void initRecyclerView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<Poem> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(new Poem());
        }


        CommentAdapter commentAdapter = new CommentAdapter(R.layout.adapter_comment_item, data);
        View footerLayout = View.inflate(this, R.layout.adapter_normal_footer_item_loading, null);
        ((AVLoadingIndicatorView) footerLayout.findViewById(R.id.av_loading)).smoothToShow();

        commentAdapter.setFooter(footerLayout);
        mRecyclerView.setAdapter(commentAdapter);
    }
}
