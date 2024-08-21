package com.wd.health.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <p>项目名称:Dimensions of health</p>
 * <p>包名:com.wd.health.dimensionsofhealth.a.model</p>
 * <p>简述:自定义拦截器</p>
 * @author 张凯涛
 * @date 2022/4/20
 */
public class MyInterceptor implements Interceptor {

    private Context context;

    public MyInterceptor(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        //sp获取
        SharedPreferences user = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        String sessionId = user.getString("sessionId", "");
        int userId = user.getInt("userId", 0);
        //获取存储
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        //凭证jhi
        builder.addHeader("userId",userId+"");
        builder.addHeader("sessionId",sessionId);
        return chain.proceed(builder.build());
    }
}
