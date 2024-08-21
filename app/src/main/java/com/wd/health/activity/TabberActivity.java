package com.wd.health.activity;

import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentTransaction;

import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;
import com.wd.health.fragment.HomeFragment;
import com.wd.health.fragment.PatientscircleFragment;
import com.wd.health.fragment.VideoFragment;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:项目的框架</p>
 *
 * @author 张凯涛
 * @date 2022/7/15
 */
public class TabberActivity extends BaseActivity{

    private FrameLayout framelayout;
    //首页的fragmnt
    private HomeFragment homeFragment;
    //病友圈的fragment
    private PatientscircleFragment patientscircleFragment;
    //视频的fragment
    private VideoFragment videoFragment;
    //单选框
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initDate() {
        //创建集合存储fragment
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb1:
                        init(0);
                        break;
                    case R.id.rb2:
                        init(1);
                        break;
                    case R.id.rb3:
                        init(2);
                        break;
                }
            }
        });
        radioGroup.check(R.id.rb1);
        init(0);

    }
    public  void  init(int i){
        hint();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (i){
            case 0:
                if(homeFragment==null){
                    homeFragment = new HomeFragment();

                    transaction.add(R.id.framelayout,homeFragment);
                }else{
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                if(patientscircleFragment==null){
                    patientscircleFragment = new PatientscircleFragment();

                    transaction.add(R.id.framelayout,patientscircleFragment);
                }else{
                    transaction.show(patientscircleFragment);
                }
                break;
            case 2:
                if(videoFragment==null){
                    videoFragment = new VideoFragment();

                    transaction.add(R.id.framelayout,videoFragment);
                }else{
                    transaction.show(videoFragment);
                }
                break;
        }
        transaction.commit();
    }
    //创建隐藏的方法
    public  void  hint(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(homeFragment!=null){
            transaction.hide(homeFragment);
        }
        if(patientscircleFragment!=null){
            transaction.hide(patientscircleFragment);
        }
        if(videoFragment!=null){
            transaction.hide(videoFragment);
        }
        transaction.commit();
    }
    @Override
    protected void initView() {

        //获取viewpage的id和tablayout的
        framelayout = findViewById(R.id.framelayout);

        radioGroup = findViewById(R.id.rg);

        //获取到底部导航栏的按钮
        radioButton = findViewById(R.id.rb2);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_tabber;
    }

    @Override
    public void onSucceed(Object object) {

    }

    @Override
    public void onError(String message) {

    }
}