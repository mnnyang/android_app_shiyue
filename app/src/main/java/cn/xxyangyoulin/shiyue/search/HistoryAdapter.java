package cn.xxyangyoulin.shiyue.search;

import android.support.annotation.NonNull;

import java.util.List;

import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.base.RecyclerBaseAdapter;

public class HistoryAdapter extends RecyclerBaseAdapter<String> {

    public HistoryAdapter(int itemLayoutId, @NonNull List<String> data) {
        super(itemLayoutId, data);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {
        System.out.println(position);
        holder.setText(R.id.tv_history,getData().get(position));
    }
}
