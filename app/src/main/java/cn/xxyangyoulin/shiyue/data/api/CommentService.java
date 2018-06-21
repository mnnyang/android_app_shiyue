package cn.xxyangyoulin.shiyue.data.api;


import java.util.Map;

import cn.xxyangyoulin.shiyue.data.bean.BaseBean;
import cn.xxyangyoulin.shiyue.data.bean.CommentWrapper;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CommentService {
    @GET("main/comment/")
    Observable<CommentWrapper> getComments(@Query("type") int type,
                                           @Query("tid") int tid,
                                           @Query("page") int page,
                                           @Query("limit") int limit,
                                           @Query("order") String order);

    @FormUrlEncoded
    @POST("main/comment/")
    Observable<BaseBean> putComment(@FieldMap Map<String, String> params);
}
