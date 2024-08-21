package com.wd.health.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.wd.health.R;
import com.wd.health.adapter.UserCircleAdapter;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;
import com.wd.health.bean.userbean.UserCollectCircleBean;
import com.wd.health.presenter.user.UserPresenter;

import java.util.List;

public class UserCollectCircleActivity extends BaseActivity<UserPresenter>{
    /**
     * <p>项目名称:Health</p>
     * <p>包名:com.wd.health.base</p>
     * <p>简述:我的发布的病友圈activity</p>
     * @author 张凯涛
     * @date 2022/7/15
     */
    //页数
    int page=1;
    //刷新
    private SmartRefreshLayout shuaxin;
    //列表
    private RecyclerView recyclerView;
    //适配器
    private UserCircleAdapter userCircleAdapter;
    //返回按钮
    private ImageView back;

    @Override
    protected UserPresenter initPresenter() {
        return new UserPresenter();
    }

    @Override
    protected void initDate() {
        //获取列表数据
        getPresenter().getUserCollectCircle(UserCollectCircleActivity.this,page);


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

        back = findViewById(R.id.usercircle_back);


        recyclerView = findViewById(R.id.rec);

    }
    @Override
    protected int getLayout() {
        return R.layout.activity_user_collect_circle;
    }

    @Override
    public void onSucceed(Object object) {
        if(object instanceof UserCollectCircleBean){
            UserCollectCircleBean     userCollectCircleBean= (UserCollectCircleBean)object;
            List<UserCollectCircleBean.ResultBean> result = userCollectCircleBean.getResult();
            if(result!=null){

                if(userCircleAdapter==null){
                    userCircleAdapter = new UserCircleAdapter(UserCollectCircleActivity.this, R.layout.item_mycircle_adapter, result);
                    recyclerView.setLayoutManager(new LinearLayoutManager(UserCollectCircleActivity.this));

                    recyclerView.setAdapter(userCircleAdapter);

                }else{
                    userCircleAdapter.notifyDataSetChanged();
                }
            }
        }
    }
    @Override
    public void onError(String message) {

    }
}