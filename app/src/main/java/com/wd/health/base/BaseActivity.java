package com.wd.health.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:activity的基类</p>
 *
 * @author 张凯涛
 * @date 2022/7/15
 */
public abstract class BaseActivity <P extends  BasePresenter> extends AppCompatActivity implements BaseView {

    //初始化创建
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        presenter=initPresenter();
        if(presenter!=null){
            getPresenter().attachView(this);
        }
        initView();
        initDate();
    }
    //创建p层对象
    private  P presenter;

    public P getPresenter() {
        return presenter;
    }

    protected  abstract  P initPresenter();
    //逻辑代码
    protected  abstract  void initDate();
    protected  abstract  void initView();
    //连接布局
    protected  abstract  int getLayout();
}
