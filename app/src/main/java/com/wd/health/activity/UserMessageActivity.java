package com.wd.health.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;
import com.wd.health.bean.userbean.UserDoctorConsultBean;
import com.wd.health.bean.userbean.UserMessageBean;
import com.wd.health.bean.userbean.UserMessageDelete;
import com.wd.health.presenter.user.UserPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserMessageActivity extends BaseActivity<UserPresenter>{

    //病症
    private TextView bingzheng;
    //现病情
    private TextView xianbingshi;
    //过往的病情
    private TextView jiwangbingshi;
    //经历
    private TextView jingli;
    private ImageView imageView1;
    private ImageView imageView2;
    private LinearLayout two;
    private LinearLayout one;
    //医院
    private TextView yiyuan;
    //时间
    private TextView time;
    //返回
    private ImageView back;
    //结束
    private Button over;
    //档案的id
    private int id;
    private Button add;
    private ImageView imageView3;
    private LinearLayout qianbao;

    @Override
    protected UserPresenter initPresenter() {
        return new UserPresenter();
    }
    @Override
    protected void initDate() {
        getPresenter().getUserMessage(UserMessageActivity.this);

        //返回上一页
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击进行删除档案
        over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除进行弹框
                AlertDialog.Builder builder = new AlertDialog.Builder(UserMessageActivity.this);

                //设置标题
                builder.setMessage("您的档案将会被删除");

                //取消删除
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                //删除
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            //走接口进行删除
                        getPresenter().getUserMessagDelete(UserMessageActivity.this,id);
                    }
                });
                //显示弹框
                builder.show();
            }
        });
        //用户点击添加答案
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserMessageActivity.this,UserMessageAddActivity.class));
                //关闭
                finish();
            }
        });
        //我的钱包


    }
    @Override
    protected void initView() {
        //主要的病症
        bingzheng = findViewById(R.id.usermessage_zhengzhuang);
        //现病史
        xianbingshi = findViewById(R.id.usermessage_xianbingshi);
        //既往病史
        jiwangbingshi = findViewById(R.id.usermessage_jiwangbingshi);
        //治疗经历
        jingli = findViewById(R.id.usermessage_jingli);
        //图片
        imageView1 = findViewById(R.id.usermessage_image1);
        imageView2 = findViewById(R.id.usermessage_image2);
        yiyuan = findViewById(R.id.usermessage_yiyuan);
        //时间
        time = findViewById(R.id.usermessage_time);
        two = findViewById(R.id.usermessage_two);
        //第一个
        one = findViewById(R.id.usermessage_one);
        //返回
        back = findViewById(R.id.usermessage_back);
        //结束
        over = findViewById(R.id.usermessage_over);
        //点击用户添加档案
        add = findViewById(R.id.usermessage_add);
        imageView3 = findViewById(R.id.usermessage_image3);

        qianbao = findViewById(R.id.user_qianbao);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_user_message;
    }

    @Override
    public void onSucceed(Object object) {
        //用户档案查询
        if(object instanceof UserMessageBean){
            UserMessageBean    userMessageBean=(UserMessageBean)object;
            UserMessageBean.ResultBean result = userMessageBean.getResult();
            if(userMessageBean.getMessage().equals("档案为空，快去添加吧！")){
                one.setVisibility(View.GONE);
                two.setVisibility(View.VISIBLE);
            }
            //用户有档案的时候
            if(userMessageBean.getMessage().equals("用户档案查询成功")){
                //获取档案的id
                id = result.getId();
                //进行赋值
               bingzheng.setText(result.getDiseaseMain());
               //现病史
                xianbingshi.setText(result.getDiseaseNow());
                //既往病史
                jiwangbingshi.setText(result.getDiseaseBefore());
                //治疗经历
                //医院
                yiyuan.setText(result.getTreatmentHospitalRecent());
                //时间
                long treatmentStartTime = result.getTreatmentStartTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                Date date = new Date(treatmentStartTime);
                String format = simpleDateFormat.format(date);
                //设置时间
                time.setText(format);
                //获取到图片
                if(result.getPicture()!=null){
                    String picture = result.getPicture();
                    String[] split = picture.split(",");
                        if(split.length==1){
                            Glide.with(UserMessageActivity.this).load(split[0]).into(imageView1);
                        }
                }
            }  }
        //删除档案
        if(object instanceof UserMessageDelete){
            UserMessageDelete      userMessageDelete=(UserMessageDelete)object;
            if(userMessageDelete.getStatus().equals("0000")){
                Toast.makeText(UserMessageActivity.this, userMessageDelete.getMessage(), Toast.LENGTH_SHORT).show();
                //删除之后隐藏原有的比距
                one.setVisibility(View.GONE);
                two.setVisibility(View.VISIBLE);
            }
        }
    }
    @Override
    public void onError(String message) {

    }
}