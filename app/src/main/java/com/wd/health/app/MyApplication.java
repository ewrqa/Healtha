package com.wd.health.app;

import android.app.Application;

import cn.jpush.im.android.api.JMessageClient;


/**
 * <p>项目名称:Dimensions of health</p>
 * <p>包名:com.wd.health.dimensionsofhealth.a.app</p>
 * <p>简述:sdk初始化</p>
 *
 * @author 张凯涛
 * @date 2022/4/26
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JMessageClient.init(this);
    }
}
