package com.wd.health.base;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:view的基类</p>
 *
 * @author 张凯涛
 * @date 2022/7/15
 */
public interface BaseView {

    //创建接口

    //创建成功和失败的回调
    void onSucceed(Object object);

    //失败的回调
    void  onError(String message);
}
