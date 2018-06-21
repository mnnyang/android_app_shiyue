package cn.xxyangyoulin.shiyue.main.adapter;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import cn.xxyangyoulin.shiyue.R;
import cn.xxyangyoulin.shiyue.base.RecyclerBaseAdapter;
import cn.xxyangyoulin.shiyue.data.bean.DailyWrapper;
import cn.xxyangyoulin.shiyue.data.bean.PoemWrapper;
import cn.xxyangyoulin.shiyue.util.LogUtil;
import cn.xxyangyoulin.shiyue.util.Lunar;

public class MainAdapter extends RecyclerBaseAdapter<DailyWrapper.Daily> {

    private Calendar calendar = Calendar.getInstance();

    public MainAdapter(int itemLayoutId, @NonNull List<DailyWrapper.Daily> data) {
        super(itemLayoutId, data);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {
        DailyWrapper.Daily daily = getData().get(position);
        try {
            String time = daily.getDailytime().substring(0, 10)
                    .replace("-", "/");
            holder.setText(R.id.tv_gongli, time);

            calendar.setTime(Lunar.customFormat.parse(time));
            Lunar lunar = new Lunar(calendar);

            holder.setText(R.id.tv_nongli, lunar.toMonthDayString());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(this, e.getMessage());
        }

        holder.setText(R.id.tv_poetry_title,
                daily.getTitle().replace("|", "\n"));
        holder.setText(R.id.tv_poetry,
                daily.getContent().replace("|", "\n"));
        holder.setText(R.id.tv_author,
                daily.getAuthor());
        holder.setText(R.id.tv_comment_count,
                String.valueOf(daily.getCcount()));
    }
}
