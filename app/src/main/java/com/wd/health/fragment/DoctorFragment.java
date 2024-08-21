package com.wd.health.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.wd.health.R;
import com.wd.health.activity.DoctorActivity;
import com.wd.health.activity.DoctorConsultActivity;
import com.wd.health.adapter.DoctorAdapter;
import com.wd.health.base.BaseFragment;
import com.wd.health.base.BasePresenter;
import com.wd.health.bean.DoctorConsultBean;
import com.wd.health.bean.doctor.OfficeBean;
import com.wd.health.bean.doctor.OfficedoctorBean;
import com.wd.health.presenter.DoctorPresenter;
import com.wd.health.utils.HttPUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.adapter</p>
 * <p>简述:医生列表的fragment</p>
 *
 * @author 张凯涛
 * @date 2022/7/18
 */
public class DoctorFragment extends BaseFragment<DoctorPresenter>{
    //设置顶部导航栏的文字
    String[] title={"总和","好评","好评率","价格"};
    //内部的导航栏
    private TabLayout tabLayout2;
    //第二个导航栏的id
    private int[] condition={1,2,3,4};
    //每个不同科室的id
    private int id1;
    //科室医生的图片
    private ImageView imageView;
    //名称
    private TextView name;
    //工作
    private TextView job;
    //医院
    private TextView yiyuan;
    //好评率
    private TextView haoping;
    //测试次数
    private TextView ceshu;
    private RecyclerView recyclerView;
    private DoctorAdapter doctorAdapter1;
    private TextView zixun;
    private int doctorId;
    private String doctorName;

    //科室的id
    public  DoctorFragment(int id1){
      this.id1=id1;
    }

    @Override
    protected DoctorPresenter initPresenter() {
        return new DoctorPresenter();
    }
    @Override
    protected void initData() {
        //给顶部导航栏进行赋值
        for (String s : title) {
            tabLayout2.addTab(tabLayout2.newTab().setText(s));
        }
        Toast.makeText(getContext(), id1+"", Toast.LENGTH_SHORT).show();
        //默认的请求
        getPresenter().getOfficeDoctor(getContext(), id1, condition[0]);
        //第二个的tab的点击监听世间
        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getPresenter().getOfficeDoctor(getContext(), id1, condition[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //点击咨询
        zixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行弹框
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                //设置内容
                builder.setMessage("本次咨询将扣除500H比");

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       getActivity().finish();
                    }
                });

                builder.setPositiveButton("去咨询", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //点击去咨询的接口
                        getPresenter().getDoctorCounsult(getActivity(),doctorId);
                    }
                });
                //弹框展示
                builder.show();
            }
        });
    }
    @Override
    protected void initView(View view) {
        //内部的顶部导航栏
        tabLayout2 = view.findViewById(R.id.doctorfragment_tab);
        //获取医生的图片
        imageView = view.findViewById(R.id.doctor_image);
        //获取医生的名字
         name = view.findViewById(R.id.doctor_name);
         //获取职位
        job = view.findViewById(R.id.doctor_jobTitle);
        //获取医院
        yiyuan = view.findViewById(R.id.doctor_yiyuan);
        //获取好评率
        haoping = view.findViewById(R.id.doctor_haoping);
        //获取次数
        ceshu = view.findViewById(R.id.doctor_cishu);
        //获取医生列表底部的rec
        recyclerView = view.findViewById(R.id.doctor_rec);
        //咨询
        zixun = view.findViewById(R.id.dector_zixun);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_doctor_fragment;
    }
    @Override
    public void onSucceed(Object object) {
        //医生的列表
        if(object instanceof  OfficedoctorBean){
            OfficedoctorBean     officedoctorBean=(OfficedoctorBean)object;

            if(officedoctorBean.getMessage().equals("查询成功")){
                //设置医生的头像
                Glide.with(getContext()).load(officedoctorBean.getResult().get(0).getImagePic()).into(imageView);
//                设置姓名
                doctorName = officedoctorBean.getResult().get(0).getDoctorName();
                name.setText(officedoctorBean.getResult().get(0).getDoctorName());
                //设置医生的职位
                job.setText(officedoctorBean.getResult().get(0).getJobTitle());
                //医生所在医院
                yiyuan.setText(officedoctorBean.getResult().get(0).getInauguralHospital());
                //设置好评率
                haoping.setText("好评率 "+officedoctorBean.getResult().get(0).getPraise());
                //设置服务次数
                ceshu.setText("服务患者次数 "+officedoctorBean.getResult().get(0).getServerNum()+"");

                doctorId = officedoctorBean.getResult().get(0).getDoctorId();

                //设置适配器
                //设置排列方式
                    doctorAdapter1 = new DoctorAdapter(getContext(), R.layout.item_doctoradapter, officedoctorBean.getResult());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                    recyclerView.setAdapter(doctorAdapter1);
            }
        }
        //当前是否有人咨询
        if(object instanceof DoctorConsultBean){

            DoctorConsultBean         doctorConsultBean=(DoctorConsultBean)object;

            if(doctorConsultBean.getMessage().equals("查询成功")){
                //跳转到对话框的页面
                Intent intent = new Intent(getContext(), DoctorConsultActivity.class);

                intent.putExtra("doctorName",doctorName);

                startActivity(intent);

            }else{
                Toast.makeText(getContext(), doctorConsultBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onError(String message) {

    }
}