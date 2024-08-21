package com.wd.health.activity;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;
/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述：欢迎页的动画 </p>
 *
 * @author 张凯涛
 * @date 2022/7/15
 */
public class MainActivity extends BaseActivity {

    private ImageView welCome;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    @Override
    protected void initDate() {
        //设置动画
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.welcome_animation);
        //给图片进行赋动画
        welCome.setAnimation(animation);
        //设置动画的监听
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(MainActivity.this, TabberActivity.class));
                //跳转过后销毁掉之前的的页面
                finish();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    onTu
    @Override
    protected void initView() {
        //获取图片的id
        welCome = findViewById(R.id.welcome);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
    @Override
    public void onSucceed(Object object) {
    }
    @Override
    public void onError(String message) {

    }
}