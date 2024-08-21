package com.wd.health.presenter.loginandregister;

import android.content.Context;

import androidx.annotation.NonNull;

import com.wd.health.activity.ForgetpasswordActivity;
import com.wd.health.base.BasePresenter;
import com.wd.health.bean.loginandregister.EmailBean;
import com.wd.health.utils.HttPUtils;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.presenter</p>
 * <p>简述:忘记的密码的接口请求方法</p>
 *
 * @author 张凯涛
 * @date 2022/7/17
 */
public class ForgetPasswordPresenter extends BasePresenter {
    //忘记密码获取验证码的请求
    public  void  getForgetCode(Context context,String email){
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

    //重置密码的请求
    public  void getRest(Context context,String email,String pwd1,String pwd2){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getRest(email,pwd1,pwd2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ForgetpasswordActivity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull ForgetpasswordActivity forgetpasswordActivity) {
                        if(getView()!=null){
                            getView().onSucceed(forgetpasswordActivity);
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
