package com.wd.health.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.wd.health.base.BasePresenter;
import com.wd.health.bean.DoctorConsultBean;
import com.wd.health.bean.doctor.OfficeBean;
import com.wd.health.bean.doctor.OfficedoctorBean;
import com.wd.health.utils.HttPUtils;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.presenter</p>
 * <p>简述:医生页面的科室切换</p>
 *
 * @author 张凯涛
 * @date 2022/7/18
 */
public class DoctorPresenter extends BasePresenter {
    //科室请求
    public void  getOffice(Context context){
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
    }
    //科室对应医生的请求
    public  void  getOfficeDoctor(Context context,int deptId,int condition){
        HashMap<String, Object> map = new HashMap<>();
        map.put("deptId",deptId);
        map.put("condition",condition);
        map.put("page",1);
        map.put("count",5);
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getOfficeDoctor(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OfficedoctorBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull OfficedoctorBean officedoctorBean) {
                        if(getView()!=null){
                           getView().onSucceed(officedoctorBean);
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
    //用户问诊当前是否有咨询
    public void  getDoctorCounsult(Context context,int doctorId){
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getDoctorCounsult(doctorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DoctorConsultBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull DoctorConsultBean doctorConsultBean) {
                        if(getView()!=null){
                            getView().onSucceed(doctorConsultBean);
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
