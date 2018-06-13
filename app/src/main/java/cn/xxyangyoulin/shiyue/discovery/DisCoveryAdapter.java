package cn.xxyangyoulin.shiyue.discovery;

import android.support.annotation.NonNull;

import java.util.List;

import cn.xxyangyoulin.shiyue.base.RecyclerBaseAdapter;
import cn.xxyangyoulin.shiyue.data.bean.Poem;

public class DisCoveryAdapter extends RecyclerBaseAdapter<Poem> {

    public DisCoveryAdapter(int itemLayoutId, @NonNull List<Poem> data) {
        super(itemLayoutId, data);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {

    }
}
