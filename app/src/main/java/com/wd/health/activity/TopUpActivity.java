package com.wd.health.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;
import com.wd.health.bean.userbean.RechargeBean;
import com.wd.health.presenter.user.UserPresenter;

public class TopUpActivity extends BaseActivity<UserPresenter>{
    /**
     * <p>项目名称:Health</p>
     * <p>包名:com.wd.health.base</p>
     * <p>简述:充值页面</p>
     * @author 张凯涛
     * @date 2022/7/15
     */
    //返回键
    private ImageView back;
    //输入的金额
    private EditText money;
    //充值之后展示的页面
    private TextView moenyx1000;
    //充值按钮
    private Button topup;
    //单选框
    private RadioGroup radioGroup;
    @Override
    protected UserPresenter initPresenter() {
        return new UserPresenter();
    }
    @Override
    protected void initDate() {
        String sa = money.getText().toString();

        //设置输入框的监听时间
        money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //进行改变输入的金额
                //转换成string
                String s1 = money.getText().toString();
                int i = Integer.valueOf(s1).intValue();
                moenyx1000.setText("本次获得"+(i*100)+"H币");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

                //设置单选框的监听
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        Double aDouble = new Double(money.getText().toString());
                        switch (checkedId){
                            case R.id.rb1:

                                //设置点击充值的点击事件
                                topup.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        getPresenter().getToup(TopUpActivity.this,aDouble,1);
                                    }
                                });
                                break;
                            case R.id.rb2:
                                //设置点击充值的点击事件
                                topup.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        getPresenter().getToup(TopUpActivity.this,aDouble,2);
                                    }
                                });
                                break;
                        }
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

        //返回
        back = findViewById(R.id.topup_back);
        //金钱
        money = findViewById(R.id.topup_money);
        //充值过后的金额展示
        moenyx1000 = findViewById(R.id.topup_moneyx1000);
        //充值按钮
        topup = findViewById(R.id.topup_topup);
        radioGroup = findViewById(R.id.rg);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_top_up;
    }

    @Override
    public void onSucceed(Object object) {

    }
    @Override
    public void onError(String message) {

    }
}