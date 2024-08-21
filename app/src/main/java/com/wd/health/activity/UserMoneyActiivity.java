package com.wd.health.activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.header.BezierRadarHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.wd.health.R;
import com.wd.health.adapter.UserMoneyAdapter;
import com.wd.health.base.BaseActivity;
import com.wd.health.bean.userbean.UserMoneyBean;
import com.wd.health.bean.userbean.UserMoneyMessageBean;
import com.wd.health.presenter.user.UserPresenter;

import java.util.ArrayList;

public class UserMoneyActiivity extends BaseActivity<UserPresenter>{
    /**
     * <p>项目名称:Health</p>
     * <p>包名:com.wd.health.base</p>
     * <p>简述:用户金钱页面</p>
     * @author 张凯涛
     * @date 2022/7/15
     */

    //返回
    private ImageView back;
    //需要的金钱
    private TextView money;
    //体现按钮
    private Button tixian;
    //充值按钮
    private Button chongzhi;
    //刷新
    private SmartRefreshLayout shuaxin;
    private  int page=1;
    //金钱的适配器
    private UserMoneyAdapter userMoneyAdapter;
    //列表
    private RecyclerView recyclerView;

   private  ArrayList<UserMoneyMessageBean.ResultBean> resultBeans = new ArrayList<>();

    @Override
    protected UserPresenter initPresenter() {
        return new UserPresenter();
    }

    @Override
    protected void initDate() {

        getPresenter().getuserMoney(UserMoneyActiivity.this);

        //返回的点击事件
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //体现的充值点击时间
        tixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //充值的点击事件
        chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //设置刷新和加载
        //设置 Header 为 贝塞尔雷达 样式
        shuaxin.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true));
        //设置 Footer 为 球脉冲 样式
        shuaxin.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));

            getPresenter().getUserMessage(UserMoneyActiivity.this);
        //        设置加载额页数
//        设置刷新的监听
        shuaxin.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载
                page++;
                //走请求的接口
                getPresenter().getUserMoneyMessage(UserMoneyActiivity.this,page,10);
            }
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //刷新
                page=1;
                //走请求的接口
                getPresenter().getUserMoneyMessage(UserMoneyActiivity.this,page,10);
            }
        });

        getPresenter().getUserMoneyMessage(UserMoneyActiivity.this,page,10);

        //充值点击事件
        chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserMoneyActiivity.this,TopUpActivity.class));
            }
        });

    }
    @Override
    protected void initView() {
        back = findViewById(R.id.usermoney_back);
        //前的数额
        money = findViewById(R.id.usermoney_money);
        //体现
        tixian = findViewById(R.id.usermoney_tixian);
        //充值
        chongzhi = findViewById(R.id.usermoney_chongzhi);
        //获取刷新的di
        shuaxin = findViewById(R.id.shuaxin);
        recyclerView = findViewById(R.id.usermoney_rec);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_user_money_actiivity;
    }

    @Override
    public void onSucceed(Object object) {
        if(object instanceof UserMoneyMessageBean){
            shuaxin.finishRefresh();
            shuaxin.finishLoadMore();
            UserMoneyMessageBean userMoneyBean= (UserMoneyMessageBean)object;

            if(userMoneyBean.getResult()!=null){
                if(page==1){
                    resultBeans.clear();
                }
                resultBeans.addAll(userMoneyBean.getResult());
                if(userMoneyAdapter==null){

                    userMoneyAdapter = new UserMoneyAdapter(UserMoneyActiivity.this, resultBeans);

                    recyclerView.setLayoutManager(new LinearLayoutManager(UserMoneyActiivity.this));

                    recyclerView.setAdapter(userMoneyAdapter);

                }else{
                    userMoneyAdapter.notifyDataSetChanged();
                }
            }
        }
        //用户金钱余额
        if(object instanceof UserMoneyBean){
            UserMoneyBean   userMoneyBean=(UserMoneyBean)object;
            if(userMoneyBean.getStatus().equals("0000")){
                money.setText(userMoneyBean.getResult()+"");
            }
        }
    }

    @Override
    public void onError(String message) {

    }
}