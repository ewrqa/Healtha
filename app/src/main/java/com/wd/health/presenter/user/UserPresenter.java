package com.wd.health.presenter.user;

import android.content.Context;

import androidx.annotation.NonNull;

import com.wd.health.base.BasePresenter;
import com.wd.health.bean.circleoffriends.EndInquiryBean;
import com.wd.health.bean.userbean.RechargeBean;
import com.wd.health.bean.userbean.SingInBean;
import com.wd.health.bean.userbean.UserBuyVideoBean;
import com.wd.health.bean.userbean.UserCollcetMessageBean;
import com.wd.health.bean.userbean.UserCollectCircleBean;
import com.wd.health.bean.userbean.UserCollectLost;
import com.wd.health.bean.userbean.UserCollectVideoBean;
import com.wd.health.bean.userbean.UserDoctorConsultBean;
import com.wd.health.bean.userbean.UserFinshConsultBean;
import com.wd.health.bean.userbean.UserHistoryConsultBean;
import com.wd.health.bean.userbean.UserMessageAddBean;
import com.wd.health.bean.userbean.UserMessageBean;
import com.wd.health.bean.userbean.UserMessageDelete;
import com.wd.health.bean.userbean.UserMessageImageBean;
import com.wd.health.bean.userbean.UserMoneyBean;
import com.wd.health.bean.userbean.UserMoneyMessageBean;
import com.wd.health.utils.HttPUtils;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.presenter.user</p>
 * <p>简述:用户页面的接口请求方法</p>
 *
 * @author 张凯涛
 * @date 2022/7/18
 */
public class UserPresenter extends BasePresenter {
    //用户签到的请求
    public  void  getSingin(Context context){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getSingin()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SingInBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SingInBean singInBean) {
                        if(getView()!=null){
                            getView().onSucceed(singInBean);
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
    //用户查看当前问诊咨询
    public  void  getUserDoctorConsult(Context context){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getUserDoctorConsult()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserDoctorConsultBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull UserDoctorConsultBean userDoctorConsultBean) {
                        if(getView()!=null){
                            getView().onSucceed(userDoctorConsultBean);
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
    //用户查看历史问诊
    public  void  getUserHistoryConsult(Context context,int page,int count){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getUserHistoryConsultBean(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserHistoryConsultBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull UserHistoryConsultBean userHistoryConsultBean) {
                        if(getView()!=null){
                            getView().onSucceed(userHistoryConsultBean);
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
    //用户结束问诊
    public  void  getUserFinsih(Context context,int recordId){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getUserFinishConsult(recordId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserFinshConsultBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UserFinshConsultBean userFinshConsultBean) {
                            if(getView()!=null){
                                getView().onSucceed(userFinshConsultBean);
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
    //用户查询自己档案
    public  void  getUserMessage(Context context){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getUserMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserMessageBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UserMessageBean userMessageBean) {
                            if(getView()!=null){
                                getView().onSucceed(userMessageBean);
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
    //删除用户档案
    public  void  getUserMessagDelete(Context context,int archivesId){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getUserMessageDelete(archivesId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserMessageDelete>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UserMessageDelete userMessageDelete) {
                        if(getView()!=null){
                            getView().onSucceed(userMessageDelete);
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
    //用户添加档案
    public  void getUserMessageAdd(Context context,
                                   String diseaseMain,
                                   String diseaseNow,
                                   String diseaseBefore,
                                   String treatmentHospitalRecent,
                                   String treatmentProcess,
                                   String treatmentStartTime,
                                   String treatmentEndTime){
        HashMap<String, Object> map = new HashMap<>();
        map.put("diseaseMain",diseaseMain);
        map.put("diseaseNow",diseaseNow);
        map.put("diseaseBefore",diseaseBefore);
        map.put("treatmentHospitalRecent",treatmentHospitalRecent);
        map.put("treatmentProcess",treatmentProcess);
        map.put("treatmentStartTime",treatmentStartTime);
        map.put("treatmentEndTime",treatmentEndTime);
        //网络请求
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getUserMessageAdd(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserMessageAddBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {


                    }
                    @Override
                    public void onNext(@NonNull UserMessageAddBean userMessageAddBean) {
                        if(getView()!=null){
                            getView().onSucceed(userMessageAddBean);
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
    //图片的请求
    public  void  getImae(Context context,List<MultipartBody.Part>list){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getImage(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserMessageImageBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull UserMessageImageBean userMessageImageBean) {
                        if(getView()!=null){
                            getView().onSucceed(userMessageImageBean);
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
    //用户的消费记录
    public  void  getUserMoneyMessage(Context context,int page,int count){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getUserMoneyMessage(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserMoneyMessageBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UserMoneyMessageBean userMoneyBean) {
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
    //用户咨询收藏的请求
    public void  getUserCollcetMessage(Context context,int page){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getUserCollcetMessage(page,6)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserCollcetMessageBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UserCollcetMessageBean userCollcetMessageBean) {
                        if(getView()!=null){
                            getView().onSucceed(userCollcetMessageBean);
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
    //用户添加视频的请求
    public void  getUserCollectVideo(Context context,int page){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getUserCollectVideo(page,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserCollectVideoBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UserCollectVideoBean userCollectVideoBean) {
                    if(getView()!=null){
                        getView().onSucceed(userCollectVideoBean);
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
    //用户收藏列表
    public void  getUserCollectList(Context context,int page){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getUserCollectList(page,6)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserCollectLost>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UserCollectLost userCollectLost) {
                        if(getView()!=null){
                            getView().onSucceed(userCollectLost);
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
    //用户购买的视频
    public  void  getUserCollectBuyVideo(Context context,int page){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getUserCollectBuyVideo(page,6)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<UserBuyVideoBean>() {
                   @Override
                   public void onSubscribe(@NonNull Disposable d) {

                   }
                   @Override
                   public void onNext(@NonNull UserBuyVideoBean userBuyVideoBean) {
                        if (getView()!=null){
                            getView().onSucceed(userBuyVideoBean);
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
    //用户收藏病友圈请求
    public void  getUserCollectCircle(Context context,int page){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getCollectCircle(page,6)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserCollectCircleBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UserCollectCircleBean userCollectCircleBean) {
                    if(getView()!=null){
                        getView().onSucceed(userCollectCircleBean);
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
    //用户充值的请求
    public  void  getToup(Context context,Double money,int payType){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getToUp(money,payType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RechargeBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RechargeBean rechargeBean) {
                        if(getView()!=null){
                            getView().onSucceed(rechargeBean);
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
    //结束问诊页面
    public  void  getDeleteDoctor(Context context,int recordId){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getDelete(recordId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EndInquiryBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull EndInquiryBean endInquiryBean) {
                        if(getView()!=null){
                            getView().onSucceed(endInquiryBean);
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


