package com.mnnyang.data.source;

import com.mnnyang.data.source.local.DayPoetryLocalDataSource;
import com.mnnyang.data.source.remote.DayPoetryRemoteDataSource;

/**
 * Created by mnnyang on 17-10-4.
 */

public class Injection {

    public static DayPoetryRepository provideDayPoetryRepository() {
        return DayPoetryRepository.newInstance(DayPoetryRemoteDataSource.newInstance(),
                DayPoetryLocalDataSource.newInstance());
    }

}
