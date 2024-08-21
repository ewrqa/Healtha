package com.wd.health.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;
import com.wd.health.bean.loginandregister.EmailBean;
import com.wd.health.bean.loginandregister.RegisterBean;
import com.wd.health.presenter.loginandregister.RegisterPresenter;
import com.wd.health.utils.loginutils.RsaCoder;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:注册的页面</p>
 *
 * @author 张凯涛
 * @date 2022/7/17
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter>{
    //邮箱验证码
    private EditText code;
    //输入的邮箱
    private EditText email;
    //密码
    private EditText password;
    private EditText password2;
    //点击注册
    private Button register;
    //获取验证码的按钮
    private Button getEmail;
    //小眼睛的显示和隐藏
    private ImageView stopeye;
    private ImageView stopeye2;
    //判断隐藏和显示额状态
    private  boolean fload1=false;
    private  boolean fload2=false;

    @Override
    protected RegisterPresenter initPresenter() {
        return new RegisterPresenter();
    }
    @Override
    protected void initDate() {
        //获取邮箱验证码的验证
        getEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //走请求接口
                //点击进行qq邮箱的正则验证
                boolean matches = email.getText().toString().matches("\\w+([-+.']\\w+)*@qq.com");
                if(matches){
                    getPresenter().getEmil(RegisterActivity.this,email.getText().toString());
                }else{
                    Toast.makeText(RegisterActivity.this, "请输入正确的邮箱格式", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //设置注册的点击事件
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先进行判断是否已经输入验证码
                if(!code.getText().toString().equals("")){
                    //判断是否输入密码
                    if(!password.getText().toString().equals("")){
                        //判断两次密码是否一致
                        if(password.getText().toString().equals(password2.getText().toString())){
                            //对密码进行加密
                            try {
                                String s = RsaCoder.encryptByPublicKey(password.getText().toString());
                                getPresenter().getRegister(RegisterActivity.this,email.getText().toString(),code.getText().toString(),s,s);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this, "两次输入的密码不相符", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //设置密码隐藏和显示的点击事件
        stopeye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fload1){
                    stopeye.setImageResource(R.mipmap.stop_eye);
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    fload1=false;
                }else{
                    stopeye.setImageResource(R.mipmap.start_eye);
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    fload1=true;
                }
            }
        });
        //设置密码隐藏和显示的点击事件
        stopeye2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fload2){
                    stopeye2.setImageResource(R.mipmap.stop_eye);
                    password2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    fload2=false;
                }else{
                    stopeye2.setImageResource(R.mipmap.start_eye);
                    password2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    fload2=true;
                }
            }
        });
    }
    @Override
    protected void initView() {
        //点击获取验证码
        code = findViewById(R.id.register_code);
        //获取输入的邮箱
        email = findViewById(R.id.register_eimail);
        //获取输入密码
        password = findViewById(R.id.register_password);
        password2 = findViewById(R.id.register_password2);
        //点击注册
        register = findViewById(R.id.register_register);
        //点击获取验证码
        getEmail = findViewById(R.id.register_getEmail);
        //获取密码隐藏和显示的图片
        stopeye = findViewById(R.id.register_stopeye);
        stopeye2 = findViewById(R.id.register_stopeye2);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void onSucceed(Object object) {
        if(object instanceof EmailBean){
            EmailBean   emailBean=(EmailBean)object;
            Toast.makeText(RegisterActivity.this, emailBean.getMessage(), Toast.LENGTH_SHORT).show();
            if(emailBean.getStatus().equals("0000")){
                Toast.makeText(RegisterActivity.this, emailBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        if(object instanceof RegisterBean){
            RegisterBean     registerBean=(RegisterBean)object;
            Toast.makeText(RegisterActivity.this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
            if(registerBean.getStatus().equals("0000")){
                Toast.makeText(RegisterActivity.this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();

                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        }
    }
    @Override
    public void onError(String message) {


    }
}