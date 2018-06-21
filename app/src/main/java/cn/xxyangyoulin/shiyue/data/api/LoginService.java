package cn.xxyangyoulin.shiyue.data.api;

import cn.xxyangyoulin.shiyue.data.bean.BaseBean;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface LoginService {
    @GET("user/logout/")
    Observable<BaseBean> logout();
}
