package com.mnnyang.data.source.remote;

import com.mnnyang.app.Api;
import com.mnnyang.data.bean.DayPoetry;
import com.mnnyang.data.source.DayPoetryDataSource;
import com.mnnyang.data.source.remote.dep.JsonCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Calendar;

import okhttp3.Call;

/**
 * Created by mnnyang on 17-10-4.
 */

public class DayPoetryRemoteDataSource implements DayPoetryDataSource {
    private static DayPoetryRemoteDataSource instence;

    private DayPoetryRemoteDataSource() {
    }

    public static DayPoetryRemoteDataSource newInstance() {
        if (instence == null) {
            instence = new DayPoetryRemoteDataSource();
        }
        return instence;
    }

    @Override
    public void getDayPoetry(Calendar date, final GetTaskCallback callback) {
        OkHttpUtils.get().url(Api.URL_DAY_POETRY)
                .addParams(Api.YEAR, date.get(Calendar.YEAR) + "")
                .addParams(Api.MONTH, date.get(Calendar.MONTH) + "")
                .addParams(Api.DAY, date.get(Calendar.DAY_OF_MONTH) + "")
                .build().execute(new JsonCallback<DayPoetry>(DayPoetry.class) {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onFail(e);
            }

            @Override
            public void onResponse(DayPoetry response, int id) {
                callback.onLoaded(response);
            }
        });
    }

    @Override
    public void refreshTasks() {

    }
}
