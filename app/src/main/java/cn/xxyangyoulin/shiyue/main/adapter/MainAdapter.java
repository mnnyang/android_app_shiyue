package cn.xxyangyoulin.shiyue.main.adapter;

import android.support.annotation.NonNull;

import java.util.List;

import cn.xxyangyoulin.shiyue.base.RecyclerBaseAdapter;
import cn.xxyangyoulin.shiyue.data.bean.Poem;

public class MainAdapter extends RecyclerBaseAdapter<Poem> {

    public MainAdapter(int itemLayoutId, @NonNull List<Poem> data) {
        super(itemLayoutId, data);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {

    }
}
