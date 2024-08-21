package com.wd.health.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;
import com.wd.health.bean.loginandregister.ForgetpasswordBean;
import com.wd.health.presenter.loginandregister.ForgetPasswordPresenter;
import com.wd.health.utils.loginutils.RsaCoder;
/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:重置密码的页面</p>
 *
 * @author 张凯涛
 * @date 2022/7/15
 */
public class ResetPasswordActivity extends BaseActivity<ForgetPasswordPresenter>{

    //第一个面密码输入
    private EditText password;
    //第二个面密码输入
    private EditText password2;
    //注册的点击按钮
    private Button finish;
    //邮箱的输入
    private String email;
    @Override
    protected ForgetPasswordPresenter initPresenter() {
        return new ForgetPasswordPresenter();
    }
    @Override
    protected void initDate() {
        //获取传过来的邮箱
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        //完成的点击事件
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //进行第一次密码的输入判空
                    if(!password.getText().toString().equals("")){
                        //判断两次密码是否一致
                        if(password.getText().toString().equals(password2.getText().toString())){
                                String s = RsaCoder.encryptByPublicKey(password.getText().toString());
                                getPresenter().getRest(ResetPasswordActivity.this,email,s,s);
                        }else{
                            Toast.makeText(ResetPasswordActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(ResetPasswordActivity.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    protected void initView() {
        //获取设置的密码
        password = findViewById(R.id.rest_password);
        password2 = findViewById(R.id.rest_password2);
        //完成的点击事件
        finish = findViewById(R.id.rest_finish);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_reset;
    }
    @Override
    public void onSucceed(Object object) {
        //注册成功的数据
        if(object instanceof ForgetpasswordBean){
            ForgetpasswordBean    forgetpasswordBean=(ForgetpasswordBean)object;
            Toast.makeText(ResetPasswordActivity.this, forgetpasswordBean.getMessage(), Toast.LENGTH_SHORT).show();
            if(forgetpasswordBean.getStatus().equals("0000")){
                Toast.makeText(ResetPasswordActivity.this, forgetpasswordBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onError(String message) {

    }
}