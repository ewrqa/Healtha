package com.wd.health.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;
import com.wd.health.fragment.usercollcet.UserCollcetCircleFragment;
import com.wd.health.fragment.usercollcet.UserCollcetHealthMessageFragment;
import com.wd.health.fragment.usercollcet.UserCollcetVideoFragment;

import java.util.ArrayList;
/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:用户收藏的activity</p>
 * @author 张凯涛
 * @date 2022/7/15
 */

public class UserCollectActivity extends BaseActivity{

    private TabLayout tabLayout;
    private ImageView backe;
    //viewpager
    private ViewPager viewPager;

    //顶部导航栏的文本
    String[] title={"健康资讯","健康视频","病友圈"};
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initDate() {

        //点击返回销毁
        backe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //创建存储fragment的集合
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new UserCollcetHealthMessageFragment());
        list.add(new UserCollcetVideoFragment());
        list.add(new UserCollcetCircleFragment());

        //循环进行赋值
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

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }

        });
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initView() {
        //获取返回的id
        backe = findViewById(R.id.usercollect_back);
        //顶部导航栏
        tabLayout = findViewById(R.id.usercollect_tab);
        //页面
        viewPager = findViewById(R.id.usercollect_viserpager);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_user_collect;
    }

    @Override
    public void onSucceed(Object object) {

    }
    @Override
    public void onError(String message) {

    }
}