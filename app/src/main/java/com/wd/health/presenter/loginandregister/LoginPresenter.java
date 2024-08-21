package com.wd.health.presenter.loginandregister;

import android.content.Context;

import androidx.annotation.NonNull;

import com.wd.health.base.BasePresenter;
import com.wd.health.bean.loginandregister.LoginBean;
import com.wd.health.utils.HttPUtils;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.presenter</p>
 * <p>简述:登录的接口请求方法</p>
 *
 * @author 张凯涛
 * @date 2022/7/16
 */
public class LoginPresenter extends BasePresenter {

    //创建登录的请求
    public  void  getLogin(Context context,String email,String pwd){
        HashMap<String, Object> map = new HashMap<>();
        map.put("email",email);
        map.put("pwd",pwd);
        HttPUtils
                .getHttPUtils(context)
                .getApiServace()
                .getLogin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        if(getView()!=null){
                            getView().onSucceed(loginBean);
                        }
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }



}
