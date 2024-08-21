package com.wd.health.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.wd.health.base.BasePresenter;
import com.wd.health.bean.HealthInFormtionMessageBean;
import com.wd.health.bean.HealthInformationBean;
import com.wd.health.bean.HomeHealthMessageBean;
import com.wd.health.bean.HomeHealthMessageClrearCollectBean;
import com.wd.health.bean.HomeHealthMessageCollectBean;
import com.wd.health.bean.HomePageXbannerBean;
import com.wd.health.bean.doctor.OfficeBean;
import com.wd.health.utils.HttPUtils;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.presenter</p>
 * <p>简述:首页的presenter</p>
 *
 * @author 张凯涛
 * @date 2022/7/15
 */
public class HomePapgePresenter extends BasePresenter {

    //创建请求轮播图的方法
    public  void  getXbanner(Context context){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getXbanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomePageXbannerBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull HomePageXbannerBean homePageXbannerBean) {
                        if(getView()!=null){
                                getView().onSucceed(homePageXbannerBean);
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
    //首页问诊咨询的请求
    public  void  getConsult(Context context){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getOffice()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OfficeBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull OfficeBean officeBean) {
                        if(getView()!=null){
                            getView().onSucceed(officeBean);
                        }
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                    @Override
                    public void onComplete() {

                    }
                });
    };

    //问诊咨询的tab导航栏的文本请求
    public  void  getwenzhenzixun(Context context){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getHealthIn()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HealthInformationBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull HealthInformationBean healthInformationBean) {
                    if(getView()!=null){
                        getView().onSucceed(healthInformationBean);
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
    //健康资讯的数据请求
    public   void gewenzhenzixunmessage(Context context,int plateId){
        HashMap<String, Object> map = new HashMap<>();
        map.put("plateId",plateId);
        map.put("page",1);
        map.put("count",5);
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getHealthInFormationMessage(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HealthInFormtionMessageBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HealthInFormtionMessageBean healthInFormtionMessageBean) {
                        if(getView()!=null){
                            getView().onSucceed(healthInFormtionMessageBean);
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
    //健康资讯板块
    public  void  getjiankangzixun(Context context,int plateId){
        HashMap<String, Object> map = new HashMap<>();
        map.put("plateId",plateId);
        map.put("page",1);
        map.put("count",15);
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getHealthInFormationMessage(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HealthInFormtionMessageBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HealthInFormtionMessageBean healthInFormtionMessageBean) {
                        if(getView()!=null){
                            getView().onSucceed(healthInFormtionMessageBean);
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
    //健康资讯的详情页
    public  void  getHelthMessage(Context context,int infoId){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getHomeMessageHTMl(infoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeHealthMessageBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HomeHealthMessageBean homeHealthMessageBean) {
                        if(getView()!=null){
                            getView().onSucceed(homeHealthMessageBean);
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

    //健康资讯的收藏
    public  void  getHealthMessageShoucang(Context context,int infoId){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getShoucang(infoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeHealthMessageCollectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull HomeHealthMessageCollectBean homeHealthMessageShoucangBean) {
                        if(getView()!=null){
                            getView().onSucceed(homeHealthMessageShoucangBean);
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

    //用户取消收藏
    public  void  getHealthMessageQuxiaoShoucang(Context context,int infoId){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getQuxiaoshoucang(infoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeHealthMessageClrearCollectBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HomeHealthMessageClrearCollectBean homeHealthMessageQuxiaoShoucangBean) {
                        if(getView()!=null){
                            getView().onSucceed(homeHealthMessageQuxiaoShoucangBean);
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
