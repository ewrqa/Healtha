package com.wd.health.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;
import com.wd.health.bean.loginandregister.EmailBean;
import com.wd.health.presenter.loginandregister.ForgetPasswordPresenter;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:忘记密码的页面 </p>
 *
 * @author 张凯涛
 * @date 2022/7/15
 */
public class ForgetpasswordActivity extends BaseActivity<ForgetPasswordPresenter>{

    //邮箱
    private EditText email;
    //点击获取验证吗
    private Button getcode;
    //第一步按钮
    private Button next;
    //验证码
    private EditText code;

    @Override
    protected ForgetPasswordPresenter initPresenter() {
        return new ForgetPasswordPresenter();
    }
    @Override
    protected void initDate() {
        //点击获取验证码
            getcode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //进行邮箱的正则验证
                    boolean matches = email.getText().toString().matches("\\w+([-+.']\\w+)*@qq.com");
                    if(matches){
                        getPresenter().getForgetCode(ForgetpasswordActivity.this,email.getText().toString());
                    }else{
                        Toast.makeText(ForgetpasswordActivity.this, "请输入正确的邮箱格式", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //设置点击下一步的点击事件
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断 是否已经输入邮箱和验证码
                if(!email.getText().toString().equals("")){
                    if(!code.getText().toString().equals("")){
                        Intent intent = new Intent(ForgetpasswordActivity.this, ResetPasswordActivity.class);
                        intent.putExtra("email",email.getText().toString());
                        startActivity(intent);
                    }else{
                        Toast.makeText(ForgetpasswordActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ForgetpasswordActivity.this, "请输入邮箱账号", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void initView() {
        //获取输入框中的邮箱
        email = findViewById(R.id.forget_email);
        //点击获取验证码
        getcode = findViewById(R.id.forget_getcode);
        //下一步
        next = findViewById(R.id.forget_next);
        //验证码
        code = findViewById(R.id.forget_code);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_forgetpassword;
    }
    @Override
    public void onSucceed(Object object) {
        //获取验证码的数据
        if(object instanceof EmailBean){
            EmailBean    emailBean=(EmailBean)object;
            Toast.makeText(ForgetpasswordActivity.this, emailBean.getMessage(), Toast.LENGTH_SHORT).show();
            if(emailBean.getStatus().equals("0000")){
                Toast.makeText(ForgetpasswordActivity.this, emailBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onError(String message) {

    }
}