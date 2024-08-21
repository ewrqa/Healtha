package com.wd.health.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wd.health.R;
import com.wd.health.adapter.Home_HealthAdapter;
import com.wd.health.base.BaseActivity;
import com.wd.health.bean.HealthInFormtionMessageBean;
import com.wd.health.presenter.HomePapgePresenter;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:健康资讯的查看更多页面</p>
 * @author 张凯涛
 * @date 2022/7/15
 */
public class HealthinFormtionActivity extends BaseActivity<HomePapgePresenter> {
    //获取顶部头像
    private ImageView head;
    //获取顶部的消息
    private ImageView mesage;
    //获取列表
    private RecyclerView recyclerView;

    private TextView healthin_name;
    //健康资讯板块的名称
    private String name;
    @Override
    protected HomePapgePresenter initPresenter() {
        return new HomePapgePresenter();
    }
    @Override
    protected void initDate() {
        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        boolean state = user.getBoolean("state", false);
        String headPic = user.getString("headPic", "");

        if(state){
            Glide.with(HealthinFormtionActivity.this).load(headPic).into(head);
        }else{
            head.setImageResource(R.mipmap.tupian);
        }
        //获取相对应的板块iD
        Intent intent = getIntent();
        int plateId = intent.getIntExtra("plateId", 0);
        name = intent.getStringExtra("name");
        getPresenter().getjiankangzixun(HealthinFormtionActivity.this,plateId);
    }
    @Override
    protected void initView() {
        head = findViewById(R.id.healthin_head);
        mesage = findViewById(R.id.healthin_message);
        recyclerView = findViewById(R.id.healthin_rec);
        healthin_name = findViewById(R.id.healthin_name);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_healthin_formtion;
    }

    @Override
    public void onSucceed(Object object) {
        //健康板块设置适配器
        if(object instanceof HealthInFormtionMessageBean){
            HealthInFormtionMessageBean    healthInFormtionMessageBean=(HealthInFormtionMessageBean)object;
            List<HealthInFormtionMessageBean.ResultBean> result = healthInFormtionMessageBean.getResult();
            if(result!=null){

                Home_HealthAdapter home_healthAdapter = new Home_HealthAdapter(result,HealthinFormtionActivity.this);

                recyclerView.setAdapter(home_healthAdapter);

                recyclerView.setLayoutManager(new LinearLayoutManager(HealthinFormtionActivity.this));

                //设置顶部板块的名称
                healthin_name.setText(name);

            }
        }
    }

    @Override
    public void onError(String message) {

    }
}