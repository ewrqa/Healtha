package com.wd.health.presenter.loginandregister;

import android.content.Context;

import androidx.annotation.NonNull;

import com.wd.health.base.BasePresenter;
import com.wd.health.bean.loginandregister.EmailBean;
import com.wd.health.bean.loginandregister.RegisterBean;
import com.wd.health.utils.HttPUtils;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.presenter.loginandregister</p>
 * <p>简述:注册的接口请求方法</p>
 *
 * @author 张凯涛
 * @date 2022/7/17
 */
public class RegisterPresenter extends BasePresenter {
    //注册邮箱的请求
    public  void  getEmil(Context context, String email){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EmailBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull EmailBean emailBean) {
                    if(getView()!=null){
                        getView().onSucceed(emailBean);
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

    //注册的请求
    public  void  getRegister(Context context,String email,String code,String pwd1,String pwd2){
        HashMap<String, Object> map = new HashMap<>();
        map.put("email",email);
        map.put("code",code);
        map.put("pwd1",pwd1);
        map.put("pwd2",pwd2);
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getRegister(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                    if(getView()!=null){
                        getView().onSucceed(registerBean);
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
