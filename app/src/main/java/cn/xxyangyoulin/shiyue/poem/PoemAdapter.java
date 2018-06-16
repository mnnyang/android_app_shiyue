package cn.xxyangyoulin.shiyue.poem;

import android.support.annotation.NonNull;

import java.util.List;

import cn.xxyangyoulin.shiyue.base.RecyclerBaseAdapter;
import cn.xxyangyoulin.shiyue.data.bean.Poem;

public class PoemAdapter extends RecyclerBaseAdapter<Poem> {

    public PoemAdapter(int itemLayoutId, @NonNull List<Poem> data) {
        super(itemLayoutId, data);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {
        System.out.println(position);
    }
}
