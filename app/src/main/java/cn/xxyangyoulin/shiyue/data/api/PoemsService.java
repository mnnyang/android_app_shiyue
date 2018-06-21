package cn.xxyangyoulin.shiyue.data.api;

import cn.xxyangyoulin.shiyue.data.bean.DailyWrapper;
import cn.xxyangyoulin.shiyue.data.bean.PoemWrapper;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PoemsService {
    @GET("main/")
    Observable<PoemWrapper> getPoemByTitle(@Query("title") String title,
                                           @Query("limit") int limit,
                                           @Query("page") int page);

    @GET("main/rand_poems/?r_type=rand")
    Observable<PoemWrapper> getRandPoems(@Query("count") int count);

    @GET("main/rand_poems/?r_type=daily")
    Observable<DailyWrapper> getDailyPoems(@Query("page") int page,
                                           @Query("limit") int limit);

    @GET("main/rand_poems/?r_type=udaily")
    Observable<DailyWrapper> getDailyPoems(@Query("ltime") String ltime);

    @GET("main/rand_poems/?r_type=mdaily")
    Observable<DailyWrapper> getMoreDaily(@Query("ltime") String ltime);

}
