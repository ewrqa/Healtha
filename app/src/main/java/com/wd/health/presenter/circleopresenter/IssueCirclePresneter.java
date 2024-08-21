package com.wd.health.presenter.circleopresenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.wd.health.base.BasePresenter;
import com.wd.health.bean.circleoffriends.FabubingyouquanBean;
import com.wd.health.bean.circleoffriends.OfficeDiseaseBean;
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
 * <p>包名:com.wd.health.presenter.circleopresenter</p>
 * <p>简述:发表病友圈的p层</p>
 *
 * @author 张凯涛
 * @date 2022/8/1
 */
public class IssueCirclePresneter extends BasePresenter {

    //请求医院科室的方法
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
        //科室对应病症的
    public void  getOfficeDisease(Context context,int departmentId){
            HttPUtils.getHttPUtils(context)
                    .getApiServace()
                    .getOfficeDisease(departmentId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<OfficeDiseaseBean>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull OfficeDiseaseBean officeDiseaseBean) {
                        if(getView()!=null){
                            getView().onSucceed(officeDiseaseBean);
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

        //发布病友圈请求
    public  void  getIssueCircle(Context context,
                                 String title,
                                 int departmentId,
                                 String disease,
                                 String detail,
                                 String treatmentHospital,
                                 String treatmentStartTime,
                                 String treatmentEndTime,
                                 String treatmentProcess,
                                 int amount){
        HashMap<String, Object> map = new HashMap<>();
        map.put("departmentId",departmentId);
        map.put("disease",disease);
        map.put("title",title);
        map.put("detail",detail);
        map.put("treatmentHospital",treatmentHospital);
        map.put("treatmentStartTime",treatmentStartTime);
        map.put("treatmentEndTime",treatmentEndTime);
        map.put("treatmentProcess",treatmentProcess);
        map.put("amount",amount);
        HttPUtils.getHttPUtils(context)
                .getApiServace()
                .getFabubingyouquan(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FabubingyouquanBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FabubingyouquanBean fabubingyouquanBean) {
                        if(getView()!=null){
                            getView().onSucceed(fabubingyouquanBean);
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
