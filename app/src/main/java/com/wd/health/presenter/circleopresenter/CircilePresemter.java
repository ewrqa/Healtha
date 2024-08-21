package com.wd.health.presenter.circleopresenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.wd.health.base.BasePresenter;
import com.wd.health.bean.circleoffriends.CircleCommentBean;
import com.wd.health.bean.circleoffriends.CircleCommentInputBean;
import com.wd.health.bean.circleoffriends.CircleMessageBean;
import com.wd.health.bean.circleoffriends.CircleofFragmentMessageBean;
import com.wd.health.bean.circleoffriends.CollectCircleBean;
import com.wd.health.utils.HttPUtils;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.presenter.circleopresenter</p>
 * <p>简述:病友圈列表</p>
 *
 * @author 张凯涛
 * @date 2022/7/27
 */
public class CircilePresemter extends BasePresenter {
    //列表请求数据
    public  void getCircle(Context context,int departmentId,int page,int count){
        HashMap<String, Object> map = new HashMap<>();
        map.put("departmentId",departmentId);
        map.put("page",page);
        map.put("count",count);
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getCircleMessage(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CircleofFragmentMessageBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CircleofFragmentMessageBean circleofFragmentMessageBean) {
                        if(getView()!=null){
                            getView().onSucceed(circleofFragmentMessageBean);
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


    //病友圈的详情
    public  void  getCircleMessage(Context context,int sickCircleId){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getCircleParticulars(sickCircleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CircleMessageBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CircleMessageBean circleMessageBean) {
                        if(getView()!=null){
                            getView().onSucceed(circleMessageBean);
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

    //用户收藏
    public  void getCollectCirce(Context context,int sickCircleId){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getCollectCircle(sickCircleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CollectCircleBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CollectCircleBean collectCircleBean) {
                        if(getView()!=null){
                            getView().onSucceed(collectCircleBean);
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

    //病友圈评论列表
    public  void  getCircleComment(Context context,int sickCircleId,int page,int count){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getCircleComment(sickCircleId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CircleCommentBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull CircleCommentBean circleCommentBean) {
                        if(getView()!=null){
                            getView().onSucceed(circleCommentBean);
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

    //用户评论病友圈
    public void  getCircleCommentInput(Context context,int sickCircleId,String content){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getCircleInput(sickCircleId,content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CircleCommentInputBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull CircleCommentInputBean circleCommentInputBean) {
                        if(getView()!=null){
                            getView().onSucceed(circleCommentInputBean);
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
