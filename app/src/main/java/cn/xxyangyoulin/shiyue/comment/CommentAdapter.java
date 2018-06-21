package cn.xxyangyoulin.shiyue.comment;

import android.support.annotation.NonNull;

import java.util.List;

import cn.xxyangyoulin.shiyue.base.RecyclerBaseAdapter;
import cn.xxyangyoulin.shiyue.data.bean.PoemWrapper;

public class CommentAdapter extends RecyclerBaseAdapter<PoemWrapper> {

    public CommentAdapter(int itemLayoutId, @NonNull List<PoemWrapper> data) {
        super(itemLayoutId, data);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {
        System.out.println(position);
    }
}
