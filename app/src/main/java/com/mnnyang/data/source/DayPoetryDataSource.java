package com.mnnyang.data.source;

import com.mnnyang.data.bean.DayPoetry;

import java.util.Calendar;

/**
 * Created by mnnyang on 17-10-4.
 */

public interface DayPoetryDataSource {
    interface GetTaskCallback {

        void onLoaded(DayPoetry dayPoetry);

        void onFail(Exception e);
    }

    void getDayPoetry(Calendar date, GetTaskCallback callback);

    void refreshTasks();
}
