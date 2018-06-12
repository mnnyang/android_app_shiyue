package cn.xxyangyoulin.shiyue.data.api;

import cn.xxyangyoulin.shiyue.data.bean.Poem;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PoemsService {
    @GET("main/")
    Observable<Poem> getPoemByTitle(@Query("title") String title,
                                    @Query("limit") int limit,
                                    @Query("page") int page);
}
