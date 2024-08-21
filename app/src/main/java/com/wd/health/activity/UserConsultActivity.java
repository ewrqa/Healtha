package com.wd.health.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;
import com.wd.health.bean.circleoffriends.EndInquiryBean;
import com.wd.health.bean.userbean.UserDoctorConsultBean;
import com.wd.health.presenter.user.UserPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:用户当前咨询的页面</p>
 * @author 张凯涛
 * @date 2022/7/19
 */
public class UserConsultActivity extends BaseActivity<UserPresenter> {

    //咨询医生的照片
    private ImageView imageView;
    //医生的name
    private TextView name;
    //医生的工作
    private TextView job;
    //医生的所在科室
    private TextView office;
    //当前咨询的时间
    private TextView time;
    //返回
    private ImageView back;
    //结束咨询
    private Button over;
    //继续咨询
    private Button go;
    //获取到问诊的id
    private int recordId;
    //有咨询和没有咨询
    private LinearLayout one;
    private LinearLayout two;

    @Override
    protected UserPresenter initPresenter() {
        return new UserPresenter();
    }

    @Override
    protected void initDate() {
        //点击结束问诊
        over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().getDeleteDoctor(UserConsultActivity.this,recordId);
            }
        });
        //请求接口
        getPresenter().getUserDoctorConsult(UserConsultActivity.this);
        //点击结束问诊
        over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().getUserFinsih(UserConsultActivity.this,recordId);
                finish();
            }
        });
        //点击返回
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    @Override
    protected void initView() {
        //获取控件
        //头像
        imageView = findViewById(R.id.userconsult_image);
        //名字
        name = findViewById(R.id.userconsult_name);
        //工作
        job = findViewById(R.id.userconsult_job);
        //科室
        office = findViewById(R.id.userconsult_office);
        //问诊时间
        time = findViewById(R.id.userconsult_time);
        //返回
        back = findViewById(R.id.userconsult_back);
        //结束问诊
        over = findViewById(R.id.userconsult_over);
        //继续问诊
        go = findViewById(R.id.userconsult_go);

        one = findViewById(R.id.userconsult_one);
        two = findViewById(R.id.userconsult_two);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_user_consult;
    }

    @Override
    public void onSucceed(Object object) {
        //请求成功回调
        if(object instanceof UserDoctorConsultBean){
            UserDoctorConsultBean    userDoctorConsultBean=(UserDoctorConsultBean)object;
            UserDoctorConsultBean.ResultBean result = userDoctorConsultBean.getResult();
            if(result!=null&&userDoctorConsultBean.getMessage().equals("查询成功")){
                //请求到数据并成功开始赋值
                recordId = result.getRecordId();
                Glide.with(UserConsultActivity.this).load(result.getImagePic()).into(imageView);
                //设置名称
                name.setText(result.getDoctorName());
                //设置职位
                job.setText(result.getJobTitle());
                //设置科室
                office.setText(result.getDepartment());
                //获取到时间
                long inquiryTime = result.getInquiryTime();
                //设置时间的格式
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm:ss");
                Date date = new Date(inquiryTime);
                String format = simpleDateFormat.format(date);
                time.setText("问诊时间    "+format);
            }
            //当前没有问诊记录
            if(userDoctorConsultBean.getMessage().equals("当前无问诊")){
                    one.setVisibility(View.GONE);
                    two.setVisibility(View.VISIBLE);
            }
        }
        //
        if(object instanceof EndInquiryBean){
            EndInquiryBean    endInquiryBean=(EndInquiryBean)object;
            Toast.makeText(UserConsultActivity.this, endInquiryBean.getMessage(), Toast.LENGTH_SHORT).show();
            one.setVisibility(View.GONE);

            two.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onError(String message) {

    }
}