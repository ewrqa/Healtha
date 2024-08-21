package com.wd.health.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:fragment的基类</p>
 *
 * @author 张凯涛
 * @date 2022/7/15
 */
public abstract class BaseFragment <P extends BasePresenter>extends Fragment implements BaseView{

    //当前用户可见
    private  boolean isUserHint;
    //是否已经创建过
    protected  boolean isViewlint;
    //是否已经加载过
    protected  boolean isDataLoad;

    private  P presenter;
    //获取的方法
    public P getPresenter() {
        return presenter;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = View.inflate(getContext(), getLayout(), null);

        initView(inflate);


        return  inflate;

    }
    //activity的初始化
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter=initPresenter();

        if(presenter!=null){
            getPresenter().attachView(this);
        }
        initData();
    }

//    //当前用户可见的状态下调用懒加载
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        isUserHint=isVisibleToUser;
//        if(isUserHint){
//            loadData();
//        }
//    }


    protected  abstract  P initPresenter();

    protected   abstract  void  initData();

    protected  abstract  void initView(View view);

    protected  abstract  int getLayout();

    //懒加载的方法
//    void loadData(){
//        if(isViewlint&&isUserHint&&!isDataLoad) {
//            initData();
//            isDataLoad = true;
//        }
//    }
}
