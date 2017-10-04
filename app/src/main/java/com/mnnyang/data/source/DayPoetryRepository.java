package com.mnnyang.data.source;

import com.mnnyang.data.source.local.DayPoetryLocalDataSource;
import com.mnnyang.data.source.remote.DayPoetryRemoteDataSource;

import java.util.Calendar;

/**
 * Created by mnnyang on 17-10-4.
 */

public class DayPoetryRepository implements DayPoetryDataSource {

    private static DayPoetryRepository mInstance;
    private final DayPoetryRemoteDataSource mRemoteDataSource;
    private final DayPoetryLocalDataSource mLocalDataSource;

    private DayPoetryRepository(
            DayPoetryRemoteDataSource remoteDataSource,
            DayPoetryLocalDataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    public static DayPoetryRepository newInstance(
            DayPoetryRemoteDataSource remoteDataSource,
            DayPoetryLocalDataSource localDataSource) {
        if (mInstance == null) {
            mInstance = new DayPoetryRepository(remoteDataSource, localDataSource);
        }
        return mInstance;
    }

    @Override
    public void getDayPoetry(Calendar date, GetTaskCallback callback) {
        //1.内存中找
        //2.数据库找
        //3.远程数据
        mRemoteDataSource.getDayPoetry(date,callback);
    }


    @Override
    public void refreshTasks() {

    }
}
