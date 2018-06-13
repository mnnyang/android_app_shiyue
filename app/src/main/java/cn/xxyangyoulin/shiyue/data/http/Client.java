package cn.xxyangyoulin.shiyue.data.http;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import cn.xxyangyoulin.shiyue.BuildConfig;
import cn.xxyangyoulin.shiyue.app.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static cn.xxyangyoulin.shiyue.util.ActivityUtil.checkNotNull;


public class Client {

    private static Client mClient;
    private Retrofit mRetrofit;

    private Client() {
        mRetrofit = createRetrofit();
    }

    public static Client getInstance() {
        if (mClient == null) {
            synchronized (Client.class) {
                if (mClient == null) {
                    mClient = new Client();
                }
            }
        }
        return mClient;
    }

    /**
     * 创建相应的服务接口
     */
    public <T> T create(Class<T> service) {
        checkNotNull(service, "service is null");
        return mRetrofit.create(service);
    }

    private Retrofit createRetrofit() {
        //初始化OkHttp
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(9, TimeUnit.SECONDS)    //设置连接超时 9s
                .readTimeout(10, TimeUnit.SECONDS);      //设置读取超时 10s

        if (BuildConfig.DEBUG) { // 判断是否为debug
            // 如果为 debug 模式，则添加日志拦截器
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        // 返回 Retrofit 对象
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(builder.build()) // 传入请求客户端
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换工厂
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 添加RxJava2调用适配工厂
                .build();
    }
}
