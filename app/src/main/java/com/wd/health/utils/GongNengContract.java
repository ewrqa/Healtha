package com.wd.health.utils;
import android.content.Context;

import com.wd.health.base.BaseView;
import com.wd.health.bean.ChaXunKeShiBean;
import com.wd.health.bean.doctor.OfficeBean;


// todo 知识宝典-病症分类页面contract
public class GongNengContract {
    public interface cjbz extends BaseView {
        void cjbz(OfficeBean keShiBean);
        void chaxunkeshi(ChaXunKeShiBean chaXunKeShiBean);
    }

    public interface IMPresenter{
        void loadData(Context context);
        void loadData2(Context context,int departmentId);
    }

}