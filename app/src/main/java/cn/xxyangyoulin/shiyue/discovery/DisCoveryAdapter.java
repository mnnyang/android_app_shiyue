package cn.xxyangyoulin.shiyue.discovery;

import android.support.annotation.NonNull;

import java.util.List;

import cn.xxyangyoulin.shiyue.base.RecyclerBaseAdapter;
import cn.xxyangyoulin.shiyue.data.bean.PoemWrapper;

public class DisCoveryAdapter extends RecyclerBaseAdapter<PoemWrapper> {

    public DisCoveryAdapter(int itemLayoutId, @NonNull List<PoemWrapper> data) {
        super(itemLayoutId, data);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {

    }
}
