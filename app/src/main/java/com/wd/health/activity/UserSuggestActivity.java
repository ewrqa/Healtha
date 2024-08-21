package com.wd.health.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;

public class UserSuggestActivity extends BaseActivity{
    /**
     * <p>项目名称:Health</p>
     * <p>包名:com.wd.health.base</p>
     * <p>简述:用户采纳意见</p>
     * @author 张凯涛
     * @date 2022/7/15
     */
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initView() {

    }
    @Override
    protected int getLayout() {
        return R.layout.activity_user_suggest;
    }

    @Override
    public void onSucceed(Object object) {

    }

    @Override
    public void onError(String message) {

    }
}