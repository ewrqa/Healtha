package com.wd.health.utils;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p>项目名称:Dimensions of health</p>
 * <p>包名:com.wd.health.dimensionsofhealth.a.model</p>
 * <p>简述:网络获取工具类</p>
 *
 * @author 张凯涛
 * @date 2022/4/19
 */
public class HttPUtils {

    //设置单利模式
    private static volatile  HttPUtils httPUtils=null;
    private final  ApiServace apiServace;
    public ApiServace getApiServace() {
        return apiServace;
    }
    //对外的无惨构造
    private HttPUtils(Context context){

        //okhttp框架
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //连接和读取超时
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .addInterceptor(new MyInterceptor(context))
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        //连接ok框架
        Retrofit build = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Url.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiServace = build.create(ApiServace.class);
    }
    public  static HttPUtils getHttPUtils(Context context) {
        if(httPUtils==null){
            synchronized (HttPUtils.class){
                if(httPUtils==null){
                     httPUtils = new HttPUtils(context);
                }
            }
        }
        return httPUtils;
    }
}
