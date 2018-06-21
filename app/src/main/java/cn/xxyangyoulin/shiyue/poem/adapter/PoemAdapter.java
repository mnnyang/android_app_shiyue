package cn.xxyangyoulin.shiyue.poem.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.base.RecyclerBaseAdapter;
import cn.xxyangyoulin.shiyue.data.bean.CommentWrapper;
import cn.xxyangyoulin.shiyue.data.bean.PoemWrapper;
import cn.xxyangyoulin.shiyue.data.http.HandleSubpath;
import cn.xxyangyoulin.shiyue.util.LogUtil;

public class PoemAdapter extends RecyclerBaseAdapter<CommentWrapper.Comment> {

    private Fragment mFragment;

    private RequestOptions options = new RequestOptions()
            .centerInside()
            .optionalCircleCrop()
            .placeholder(R.drawable.image_default_avator);

    public PoemAdapter(Fragment fragment, int itemLayoutId, @NonNull List<CommentWrapper.Comment> data) {
        super(itemLayoutId, data);
        mFragment = fragment;
    }

    @Override
    protected void convert(ViewHolder holder, int position) {
        CommentWrapper.Comment comment = getData().get(position);
        holder.setText(R.id.tv_author, comment.getNick_name());

        if (comment.getTitle().trim().isEmpty()) {
            holder.getView(R.id.tv_title).setVisibility(View.GONE);
        } else {
            holder.getView(R.id.tv_title).setVisibility(View.VISIBLE);
            holder.setText(R.id.tv_title, comment.getTitle());
        }
        holder.setText(R.id.tv_content, comment.getContent());
        holder.setText(R.id.tv_author, comment.getNick_name());
        holder.setText(R.id.tv_datetime, comment.getAdd_time());
        holder.setText(R.id.tv_comment_count, String.valueOf(comment.getSubcount()));

        ImageView ivAvator = holder.getView(R.id.iv_avator);

        String handle = HandleSubpath.handle(comment.getAvator(), true);
        LogUtil.e(this, handle);

        Glide.with(mFragment)
                .applyDefaultRequestOptions(options)
                .load(handle)
                .into(ivAvator);
    }
}
