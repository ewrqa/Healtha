package com.wd.health.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.health.R;
import com.wd.health.adapter.SendAdapter;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;
import com.wd.health.bean.userbean.UserDoctorConsultBean;
import com.wd.health.utils.HttPUtils;
import com.wd.health.utils.VoicePlayingBgUtil;
import com.wd.health.utils.voiceutils.VoiceUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DoctorConsultActivity extends BaseActivity{

    private TextView doctorname;
    private ImageView back;
    //医生appkey
    private  String appkeya="b5f102cc307091e167ce52e0";
    private RecyclerView recyclerView;
    private Button button;
    private EditText input;
    private Message message;

    private String userName;
    private SendAdapter sendAdapter;
    private List<Message> allMessage;
    private ImageView biaoqing;
    private ImageView gengduo;
    private RelativeLayout relativeLayout;
    private ImageView yuyin;
    private Button sendvoice;
    private ImageView jianpan;
    private TextView voice_popup_message;
    private ImageView voice_popup_voice;
    private TextView voice_popup_time;
    private PopupWindow popupWindow;
    private String title;
    private  int time_int=0;
    private boolean isPopuWindow=true;
    private String path;

 private  VoiceUtils voiceUtils=  new VoiceUtils("data/data/com.wd.health/record/");

    //耗时操作
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void dispatchMessage(@NonNull android.os.Message msg) {
            super.dispatchMessage(msg);
            //时长的记录
            voice_popup_time.setText(msg.what+"");
            //发送
            handler.sendEmptyMessageDelayed(time_int++,1000);
        }
    };

    private int now_time;
    private ImageView imageView;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    @Override
    protected void initDate() {
        //获取传送过来姓名
        Intent intent = getIntent();
        String name = intent.getStringExtra("doctorName");
        doctorname.setText(name);

        //点击返回
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //用户成功咨询医生的
        HttPUtils.getHttPUtils(DoctorConsultActivity.this)
                .getApiServace()
                .getUserDoctorConsult()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserDoctorConsultBean>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UserDoctorConsultBean userDoctorConsultBean) {
                        userName = userDoctorConsultBean.getResult().getUserName();
                        //创建极光的对话
                        Conversation.createSingleConversation(userName,appkeya);
                        //获取聊天对话
                        Conversation singleConversation = JMessageClient.getSingleConversation(userDoctorConsultBean.getResult().getUserName(), appkeya);

                        //进行值的非空判断
                        if (singleConversation!=null){
                            allMessage = singleConversation.getAllMessage();

                            //创建对话的适配器
                            sendAdapter = new SendAdapter(DoctorConsultActivity.this, allMessage);

                            recyclerView.setLayoutManager(new LinearLayoutManager(DoctorConsultActivity.this));

                            recyclerView.setAdapter(sendAdapter);


                            senMessage(userName);

                        }

                    }
                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        //输入框的监听
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {



            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入有值的时候则进行
                if (!input.getText().toString().isEmpty()) {
                    //有值的时候显示
                    gengduo.setVisibility(View.GONE);

                    button.setVisibility(View.VISIBLE);

                } else {
                    //没有值的时候
                    //有值的时候显示
                    gengduo.setVisibility(View.VISIBLE);
                    //设置在那个的旁边
                    //显示发送的按钮
                    button.setVisibility(View.GONE);

                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //设置语音发送的点击事件
        yuyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //语音的图片
                yuyin.setVisibility(View.GONE);
                //隐藏文本的输入
                input.setVisibility(View.GONE);
                //语音发送按钮的显示
                sendvoice.setVisibility(View.VISIBLE);
                //显示键盘
                jianpan.setVisibility(View.VISIBLE);

            }
        });
        //键盘的点击事件
        jianpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jianpan.setVisibility(View.GONE);
                //显示输入框
                input.setVisibility(View.VISIBLE);

                sendvoice.setVisibility(View.GONE);
                //语音按钮的显示
                yuyin.setVisibility(View.VISIBLE);
            }
        });

        //语音发送的点击事件

    }
    //发送消息
    private void senMessage(String userName) {
        //点击发送文本
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取文本框的内容
                String s = input.getText().toString();

                message = JMessageClient.createSingleTextMessage(userName, appkeya, s);

                send();
            }
        });

        //语音发送的案件监听
        sendvoice.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    //当按下的时候
                    case MotionEvent.ACTION_DOWN:
                        //当按下的时候开始改变文本框的状态
                        sendvoice.setText("正在录音");

                        title="向上取消发送";

                        voicePopwindow();

                        //发送语音调用工具类
                        voiceUtils.startRecord();

                        //popupwindow录音时长
                        handler.sendEmptyMessageDelayed(time_int++,1000);

                        break;
                    //抬起的时候
                    case  MotionEvent.ACTION_UP:
                        handler.removeCallbacksAndMessages(null);
                        time_int=0;
                        //设置按钮文字
                        sendvoice.setText("按住发送语音");

                        //获取开始减去结束时间 得到语音时长
                        long time = event.getEventTime() - event.getDownTime();
                        //现在的时间
                        now_time = (int) time / 1000;
                        if(isPopuWindow){
                            if(now_time > 2){
                                Toast.makeText(DoctorConsultActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                                path = voiceUtils.stopRecord();

                                //语音的发送
                                voiceMessage(userName);
                            }else{
                                //录音小于两秒  发送失败
                                Toast.makeText(DoctorConsultActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            isPopuWindow=true;
                        }
                        //关闭popupwindow
                        popupWindow.dismiss();

                        break;
                    //移动之后
                    case  MotionEvent.ACTION_MOVE:
                        if(event.getY() < -100){
                            title = "松开取消发送";
                            voice_popup_voice.setImageResource(R.mipmap.ic_launcher);
                            voice_popup_message.setText(title);
                            isPopuWindow = false;
                            voiceUtils.cancelRecord();
                        }
                        break;
                }
                return true;
            }
        });

        //点击更多查看发送图片
        gengduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    //语音发送
    public void voiceMessage(String userName){
        File file = new File(path);
        try {
            message = JMessageClient.createSingleVoiceMessage(userName, appkeya, file, now_time);

            send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //发送消息
    @SuppressLint("NotifyDataSetChanged")
    public void send(){
        JMessageClient.sendMessage(message);
        allMessage.add(message);
        //刷新适配器
        sendAdapter.notifyDataSetChanged();


    }
    @Override
    protected void initView() {
        //获取键盘
        jianpan = findViewById(R.id.consult_jianpan);

        doctorname = findViewById(R.id.consult_name);

        back = findViewById(R.id.consult_back);

        //获取对话框的列表
        recyclerView = findViewById(R.id.consult_rec);

        button = findViewById(R.id.send_button);
        //输入框
        input = findViewById(R.id.consult_input);

        biaoqing = findViewById(R.id.consult_biaoqing);

        gengduo = findViewById(R.id.consult_gengduo);

        relativeLayout = findViewById(R.id.aaaaaaa);

        //发送语音
        yuyin = findViewById(R.id.consult_yuyin);

        //发送语音
        sendvoice = findViewById(R.id.consult_fasongyuyin);

        //获取点击更多的id
        imageView = findViewById(R.id.consult_gengduo);

    }

    //发送语音的popwindow
    public  void  voicePopwindow(){

        //获取弹框的id设置他的背景透明度

        //展示的布局
        View inflate = LayoutInflater.from(DoctorConsultActivity.this).inflate(R.layout.popwindow_sendvoice, null);


        RelativeLayout relativeLayout= inflate.findViewById(R.id.bbbbbbb);


        relativeLayout.getBackground().setAlpha(90);


        //要连接的布局
        View inflate1 = LayoutInflater.from(DoctorConsultActivity.this).inflate(R.layout.activity_doctor_consult, null);

        //获取文本展示的id
        voice_popup_message = inflate.findViewById(R.id.pop_voice_message);
        voice_popup_voice = inflate.findViewById(R.id.pop_voice_yuyin);
        //当前状态文本
        voice_popup_message = inflate.findViewById(R.id.pop_voice_message);
        //消息时间

        voice_popup_time = inflate.findViewById(R.id.pop_voice_time);

        popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

        //设置popupwindow 的位置
        popupWindow.showAtLocation(inflate, Gravity.CENTER,0,0);
        //点击其他地方关闭popupwindow
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }
    @Override
    protected int getLayout() {
        return R.layout.activity_doctor_consult;
    }

    @Override
    public void onSucceed(Object object) {

    }
    @Override
    public void onError(String message) {

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event){

        Message message = event.getMessage();

        allMessage.add(message);

        sendAdapter.notifyDataSetChanged();


    }

    @Override
    protected void onStart() {
        super.onStart();
        JMessageClient.registerEventReceiver(this,200);
    }

    @Override
    protected void onStop() {
        super.onStop();
        JMessageClient.unRegisterEventReceiver(this);

        //释放一下资源
        allMessage.clear();
    }
}