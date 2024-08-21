package com.wd.health.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.scwang.smart.refresh.header.BezierRadarHeader;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.wd.health.R;
import com.wd.health.adapter.UserCollectBuyVideoAdapter;
import com.wd.health.adapter.UserCollectVideoAdapter;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;
import com.wd.health.bean.userbean.UserBuyVideoBean;
import com.wd.health.presenter.user.UserPresenter;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:用户购买视频的activity</p>
 * @author 张凯涛
 * @date 2022/7/15
 */

public class UserBuyVideoActivity extends BaseActivity<UserPresenter> {

    //刷新
    private SmartRefreshLayout shuaxin;
    //列表
    private RecyclerView recyclerView;
    int page=1;
    //添加的集合
    ArrayList<UserBuyVideoBean.ResultBean> resultBeans = new ArrayList<>();
    //适配器
    private UserCollectBuyVideoAdapter userCollectBuyVideoAdapter;
    private ImageView back;

    @Override
    protected UserPresenter initPresenter() {
        return new UserPresenter();
    }

    @Override
    protected void initDate() {
        //刷新
        //设置 Header 为 贝塞尔雷达 样式
        shuaxin.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true));
        //设置 Footer 为 球脉冲 样式
        getPresenter().getUserCollectBuyVideo(UserBuyVideoActivity.this,page);

        shuaxin.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getPresenter().getUserCollectBuyVideo(UserBuyVideoActivity.this,page);
            }
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                getPresenter().getUserCollectBuyVideo(UserBuyVideoActivity.this,page);
            }
        });
        //点击关闭
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    protected void initView() {

        shuaxin = findViewById(R.id.shuaxin);
        recyclerView = findViewById(R.id.rec);
        //返回
        back = findViewById(R.id.userbuvideo_back);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_user_buy_video;
    }

    @Override
    public void onSucceed(Object object) {
        if(object instanceof UserBuyVideoBean){
            //停止动画
            shuaxin.finishRefresh();
            shuaxin.finishLoadMore();
            UserBuyVideoBean       userBuyVideoBean=(UserBuyVideoBean)object;
            List<UserBuyVideoBean.ResultBean> result = userBuyVideoBean.getResult();
            if(result!=null){
                if(page==1){
                    resultBeans.clear();
                }

                resultBeans.addAll(result);

                if(userCollectBuyVideoAdapter==null){
                    userCollectBuyVideoAdapter = new UserCollectBuyVideoAdapter(UserBuyVideoActivity.this, R.layout.item_collectvido, result);
               recyclerView.setLayoutManager(new LinearLayoutManager(UserBuyVideoActivity.this));

               recyclerView.setAdapter(userCollectBuyVideoAdapter);

                }else{
                    userCollectBuyVideoAdapter.notifyDataSetChanged();
                }


            }

        }

    }

    @Override
    public void onError(String message) {

    }
}