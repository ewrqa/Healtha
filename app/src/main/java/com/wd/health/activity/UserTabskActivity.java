package com.wd.health.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;

public class UserTabskActivity extends BaseActivity{
    /**
     * <p>项目名称:Health</p>
     * <p>包名:com.wd.health.base</p>
     * <p>简述:用户任务页面</p>
     * @author 张凯涛
     * @date 2022/7/15
     */
    private ImageView imageView;
    private TextView username;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initDate() {
        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        String headPic = user.getString("headPic", "");
        String headname = user.getString("name", "");
        //设置头像
        Glide.with(UserTabskActivity.this).load(headPic).into(imageView);

        //名字
        username.setText(headname);
    }

    @Override
    protected void initView() {
        //头像名称
        imageView = findViewById(R.id.set_image);

        username = findViewById(R.id.set_name);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_user_set;
    }

    @Override
    public void onSucceed(Object object) {

    }

    @Override
    public void onError(String message) {

    }
}