package cn.xxyangyoulin.shiyue.data.api;

import java.util.Map;

import cn.xxyangyoulin.shiyue.data.bean.BaseBean;
import cn.xxyangyoulin.shiyue.data.bean.UserWrapper;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface UserInfoService {
    @GET("user/user_info")
    Observable<UserWrapper> getUserInfo();

    @Multipart
    @POST("user/user_info")
    Observable<BaseBean> updateUserInfo(@Query("type") int type, @Part MultipartBody.Part avator);

    @FormUrlEncoded
    @POST("user/user_info")
    Observable<BaseBean> updateUserInfo(@FieldMap Map<String, String> params);
}
