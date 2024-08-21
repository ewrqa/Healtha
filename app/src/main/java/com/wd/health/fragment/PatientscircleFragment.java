package com.wd.health.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.wd.health.R;
import com.wd.health.activity.IssueCircleActivity;
import com.wd.health.base.BaseFragment;
import com.wd.health.base.BasePresenter;
import com.wd.health.presenter.circleopresenter.CircilePresemter;

import java.util.ArrayList;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:病友圈fragment</p>
 *
 * @author 张凯涛
 * @date 2022/7/15
 */
public class PatientscircleFragment extends BaseFragment<CircilePresemter>{

    private TabLayout tabLayout;
    private ImageView sousuo;
    private ViewPager viewPager;
    private ImageView head;
    private ImageView message;
    //登录的状态值
    private boolean state;
    String[] titlea={"内科","眼科","骨科","小儿科","传染病科","皮肤科","耳鼻喉科","精神病科"};
    @Override
    protected CircilePresemter initPresenter() {
        return new CircilePresemter();
    }

    @Override
    protected void initData() {
        //进行登录状态值的获取
        SharedPreferences user1 = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        //获取登录的状态
        state = user1.getBoolean("state", false);
        String headPic = user1.getString("headPic", "");

        if(state){
            Glide.with(getContext()).load(headPic).into(head);
        }else{
            head.setImageResource(R.mipmap.tupian);
        }

        //设置头像的点击事件发布病友圈
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), IssueCircleActivity.class));
            }
        });
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new CircleofFriendsFragment(7));
        list.add(new CircleofFriendsFragment(4));
        list.add(new CircleofFriendsFragment(2));
        list.add(new CircleofFriendsFragment(5));
        list.add(new CircleofFriendsFragment(12));
        list.add(new CircleofFriendsFragment(9));
        list.add(new CircleofFriendsFragment(6));
        list.add(new CircleofFriendsFragment(11));

        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
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
     tabLayout.setupWithViewPager(viewPager);
    }
    @Override
    protected void initView(View view) {
        tabLayout = view.findViewById(R.id.list_tab);
        sousuo = view.findViewById(R.id.list_sousuo);
        viewPager = view.findViewById(R.id.list_viewpage);
        head = view.findViewById(R.id.list_head);
        message = view.findViewById(R.id.list_message);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_patientscircle_fragment;
    }

    @Override
    public void onSucceed(Object object) {

    }
    @Override
    public void onError(String message) {

    }
}