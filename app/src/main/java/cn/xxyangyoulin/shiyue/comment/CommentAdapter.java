package cn.xxyangyoulin.shiyue.comment;

import android.support.annotation.NonNull;

import java.util.List;

import cn.xxyangyoulin.shiyue.base.RecyclerBaseAdapter;
import cn.xxyangyoulin.shiyue.data.bean.Poem;

public class CommentAdapter extends RecyclerBaseAdapter<Poem> {

    public CommentAdapter(int itemLayoutId, @NonNull List<Poem> data) {
        super(itemLayoutId, data);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {
        System.out.println(position);
    }
}
