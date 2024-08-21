package com.wd.health.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.bean.userbean.SingInBean;
import com.wd.health.presenter.user.UserPresenter;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:用户的个人中心</p>
 *
 * @author 张凯涛
 * @date 2022/7/15
 */
public class UserActivity extends BaseActivity<UserPresenter>{
    //获取头像id
    private ImageView headpic;
    //个人用户页名字
    private TextView name;
    //签到
    private Button signin;
    //当前问诊咨询
    private RelativeLayout relativeLayout1;
    //历史问诊咨询
    private RelativeLayout relativeLayout2;
    //我的档案
    private LinearLayout dangan;
    //钱包
    private LinearLayout money;
    //
    private LinearLayout collect;
    private LinearLayout suggest;
    //我购买的视频
    private LinearLayout video;
    //病友圈
    private LinearLayout patientscircle;
    //我的关注
    private LinearLayout guanzhu;
    //我的任务
    private LinearLayout task;
    //设置
    private LinearLayout set;
    //点击返回按钮
    private ImageView back;

    @Override
    protected UserPresenter initPresenter() {
        return new UserPresenter();
    }

    @Override
    protected void initDate() {
        //sp获取到登录的状态和名称
        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        String headPic = user.getString("headPic", "");
        String headname = user.getString("name", "");
        //设置头像
        Glide.with(UserActivity.this).load(headPic).into(headpic);
        //设置名字
        name.setText(headname);
        //签到的点击事件
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().getSingin(UserActivity.this);
            }
        });
        //点击返回
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this,TabberActivity.class));
                finish();
            }
        });
        //跳转到当前问诊的页面
        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   startActivity(new Intent(UserActivity.this,UserConsultActivity.class));
            }
        });
        //点击查看历史问诊
        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, UserHistoryConsultActivity.class));
            }
        });
        //用户查看当前的自己档案
        dangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this,UserMessageActivity.class));
            }
        });
        //用户钱包
        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this,UserMoneyActiivity.class));
            }
        });
        //用户点击查看自己的收藏
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击跳转到收藏页面
            startActivity(new Intent(UserActivity.this,UserCollectActivity.class));
            }
        });
        //设置采纳意见的点击事件
        suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this,UserSuggestActivity.class));
            }
        });

        //购买的视频
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this,UserBuyVideoActivity.class));

            }
        });

        //我的朋友圈
        patientscircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this,UserCollectCircleActivity.class));

            }
        });

        //任务
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this,UserTabskActivity.class));
            }
        });

        //设置
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this,UserTabskActivity.class));

            }
        });
    }
    @Override
    protected void initView() {
       //头像
        headpic = findViewById(R.id.user_headpic);
        //名字
        name = findViewById(R.id.user_name);
        //签到
        signin = findViewById(R.id.user_signin);
        //当前问诊
        relativeLayout1 = findViewById(R.id.user_inquiry);
        //历史问诊
        relativeLayout2 = findViewById(R.id.user_history);
        //我的档案
        dangan = findViewById(R.id.user_dangan);
        //钱包
        money = findViewById(R.id.user_qianbao);
        //收藏
        collect = findViewById(R.id.user_shoucang);
        //建议
        suggest = findViewById(R.id.user_jianyi);
        //视频
        video = findViewById(R.id.user_shipin);
        //病友圈
        patientscircle = findViewById(R.id.user_bingyouquan);
        //关注
        guanzhu = findViewById(R.id.user_guanzhu);
        //任务
        task = findViewById(R.id.user_renwu);
        //设置
        set = findViewById(R.id.user_shezhi);
        //返回按钮
        back = findViewById(R.id.user_back);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_user;
    }
    @Override
    public void onSucceed(Object object) {
        //签到成功
        if(object instanceof SingInBean){
            SingInBean   singInBean=(SingInBean)object;
            Toast.makeText(UserActivity.this, singInBean.getMessage(), Toast.LENGTH_SHORT).show();
            if(singInBean.getMessage().equals("签人8到成功")){
                Toast.makeText(UserActivity.this, singInBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onError(String message) {

    }
}