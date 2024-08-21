package com.wd.health.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;
import com.wd.health.bean.doctor.OfficeBean;
import com.wd.health.bean.doctor.OfficedoctorBean;
import com.wd.health.fragment.DoctorFragment;
import com.wd.health.presenter.DoctorPresenter;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:医生的列表展示activity </p>
 *
 * @author 张凯涛
 * @date 2022/7/15
 */
public class DoctorActivity extends BaseActivity<DoctorPresenter>{

    private ImageView head;
    private TabLayout tableLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> list = new ArrayList<>();
    //科室的id
    String[] titlea={"内科","眼科","骨科","小儿科","传染病科","皮肤科","耳鼻喉科","精神病科"};
    private boolean state;

    @Override
    protected DoctorPresenter initPresenter() {
        return new DoctorPresenter();
    }
    @Override
    protected void initDate() {
        //获取点击的下标
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        state = user.getBoolean("state", false);
        String headPic = user.getString("headPic", "");
        //登录成功之后进行头像的显示
        if(state){
            Glide.with(DoctorActivity.this).load(headPic).into(head);
        }else{
            head.setImageResource(R.mipmap.tupian);
        }
        //共同服用同一个fragment  传入不同的id
        list.add(new DoctorFragment(7));
        list.add(new DoctorFragment(4));
        list.add(new DoctorFragment(2));
        list.add(new DoctorFragment(5));
        list.add(new DoctorFragment(12));
        list.add(new DoctorFragment(9));
        list.add(new DoctorFragment(6));
        list.add(new DoctorFragment(11));
        //设置tablayout和viewpager的联动
         viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
             @NonNull
             @Override
             public Fragment getItem(int position) {
                 return list.get(position);
             }
             @Override
             public int getCount() {
                 return list.size();
             }
             //设置导航栏
             @Nullable
             @Override
             public CharSequence getPageTitle(int position) {
                 return titlea[position];
             }
         });
         //连接viewpager
        tableLayout.setupWithViewPager(viewPager);
       //设置tablaout的显示页
        tableLayout.getTabAt(id).select();

    }
    @Override
    protected void initView() {
        //用户头像
        head = findViewById(R.id.doctor_head);
        //页面的顶部导航栏
        tableLayout = findViewById(R.id.doctor_tab);
        viewPager = findViewById(R.id.doctor_viewpager);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_doctor;
    }
    @Override
    public void onSucceed(Object object) {

    }
    @Override
    public void onError(String message) {

    }
}