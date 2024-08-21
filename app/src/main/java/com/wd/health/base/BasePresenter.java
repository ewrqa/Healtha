package com.wd.health.base;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:p层的基类</p>
 *
 * @author 张凯涛
 * @date 2022/7/15
 */

public class BasePresenter <View extends  BaseView>{

    //获取view层的数据
    private  View view;

    //获取的方法
    public View getView() {
        return view;
    }
    //绑定布局
    public  void attachView(View view){
        this.view=view;
    }

}
