package com.wd.health.utils;

import android.content.Context;

import com.wd.health.base.BaseView;
import com.wd.health.bean.YaoPinDuiYingBean;
import com.wd.health.bean.YaoPinListBean;


// todo 知识宝典 常用药品
public class ChangYongYaoPinContract {
    public interface mainView extends BaseView {
        void cyyp(YaoPinListBean yaoPinListBean);
        void cyypdy(YaoPinDuiYingBean yaoPinDuiYingBean);
    }

    public interface IMPresenter{
        void loadData(Context context);
        void loadData2(Context context,int drugsCategoryId,int page,int count);
    }
}