package com.wd.health.presenter.video;

import android.content.Context;

import androidx.annotation.NonNull;

import com.wd.health.base.BasePresenter;
import com.wd.health.bean.BarrageBean;
import com.wd.health.bean.userbean.UserMoneyBean;
import com.wd.health.bean.video.BuyVideoBean;
import com.wd.health.bean.video.CollectVideoBean;
import com.wd.health.bean.video.DeleteCollectVideoBean;
import com.wd.health.bean.video.VideoBean;
import com.wd.health.utils.HttPUtils;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.presenter.video</p>
 * <p>简述:视频页面的p层请求</p>
 *
 * @author 张凯涛
 * @date 2022/7/26
 */
public class Videopresenter extends BasePresenter {

    //根据不同的状态播放不同的视频
    public void  getVideo(Context context,int categoryId){
        HashMap<String, Object> map = new HashMap<>();
        map.put("categoryId",categoryId);
        map.put("page",1);
        map.put("count",15);
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getVideo(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideoBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull VideoBean videoBean) {
                        if(getView()!=null){
                            getView().onSucceed(videoBean);
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

    //点击购买视频的请求
    public  void  getBuyVideo(Context context,int videoId,int price){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getBuy(videoId,price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BuyVideoBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BuyVideoBean buyVideoBean) {
                        if(getView()!=null){
                            getView().onSucceed(buyVideoBean);
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
    //用户金钱余额的请求
    public  void  getuserMoney(Context context){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getUserMoeny()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserMoneyBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UserMoneyBean userMoneyBean) {
                        if(getView()!=null){
                            getView().onSucceed(userMoneyBean);
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

    //用户收藏视频
    public  void  getCollectVideo(Context context,int videoId){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getCollectVideoBean(videoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CollectVideoBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull CollectVideoBean collectVideoBean) {
                            if(getView()!=null){
                                getView().onSucceed(collectVideoBean);
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

    //用户取消视频
     public  void  getDelelteVideo(Context context,int videoId){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getDleteVideoBean(videoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeleteCollectVideoBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull DeleteCollectVideoBean deleteCollectVideoBean) {
                    if(getView()!=null){
                        getView().onSucceed(deleteCollectVideoBean);
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

   //显示弹幕
   public void  getshowDanmu(Context context,int videoId){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getBrrage(videoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BarrageBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BarrageBean barrageBean) {
                        if(getView()!=null){
                            getView().onSucceed(barrageBean);
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



