package com.mnnyang.data.source.local;

import android.os.Bundle;

import com.mnnyang.data.source.DayPoetryDataSource;

import java.util.Calendar;

/**
 * Created by mnnyang on 17-10-4.
 */

public class DayPoetryLocalDataSource implements DayPoetryDataSource {

    private static DayPoetryLocalDataSource instance;

    private DayPoetryLocalDataSource() {
    }

    public static DayPoetryLocalDataSource newInstance() {
        if (instance == null) {
            instance = new DayPoetryLocalDataSource();
        }
        return instance;
    }

    @Override
    public void getDayPoetry(Calendar date, GetTaskCallback callback) {

    }

    @Override
    public void refreshTasks() {

    }
}
