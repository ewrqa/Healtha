package com.wd.health.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.bean.loginandregister.LoginBean;
import com.wd.health.presenter.loginandregister.LoginPresenter;
import com.wd.health.utils.loginutils.RsaCoder;

import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.RequestCallback;
import cn.jpush.im.android.api.model.DeviceInfo;


/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:登录的页面</p>
 *
 * @author 张凯涛
 * @date 2022/7/15
 */
public class LoginActivity extends BaseActivity<LoginPresenter>{

    //关闭眼睛
    private ImageView stopeye;
    //密码的隐藏和显示
    private  Boolean fload=false;
    //登录的点击事件
    private Button login;
    //输入密码
    private EditText password;
    //输入的邮箱
    private EditText email;
    //注册的控件
    private TextView register;
    //忘记密码的控件
    private TextView forgetpassword;
    private ImageView back;
    private String md5;

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }
    @Override
    protected void initDate() {
        //点击改变密码是否显示
        stopeye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //状态1的则为关闭状态
                if(fload){
                    stopeye.setImageResource(R.mipmap.stop_eye);
                    //设置当前密码是不可见的状态
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    fload=false;
                }else{
                    stopeye.setImageResource(R.mipmap.start_eye);
                    //设置当前密码为可见的状态
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    fload=true;
                }
            }
        });
        //点击登录按钮
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取邮箱和密码
                //给密码进行加密
                try {
                    String s = RsaCoder.encryptByPublicKey(password.getText().toString());
                    //邮箱的正则验证
                    boolean matches = email.getText().toString().matches("\\w+([-+.']\\w+)*@qq.com");
                    if(matches){
                        getPresenter().getLogin(LoginActivity.this,email.getText().toString(),s);
                    }else{
                        Toast.makeText(LoginActivity.this, "请输入正确的邮箱格式", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //点击注册的跳转
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        //点击忘记密码
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgetpasswordActivity.class));
            }
        });
//        点击返回到主页面
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this,TabberActivity.class));
//                finish();
//            }
//        });

    }
    //创建极光登录的方法
    public  void  JGLogint(String userName,String pwd){
        //进行解密和加密的操作
        try {
            String s = RsaCoder.decryptByPublicKey(pwd);
            md5 = RsaCoder.MD5(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JMessageClient.login(userName, md5, new RequestCallback<List<DeviceInfo>>() {
            @Override
            public void gotResult(int i, String s, List<DeviceInfo> deviceInfos) {
                if(i==0){

                    Toast.makeText(LoginActivity.this, "极光登录成功", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(LoginActivity.this, "极光登录失败", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    @Override
    protected void initView() {
        //获取小眼睛的id
        stopeye = findViewById(R.id.login_stopeye);
        //登录按钮
        login = findViewById(R.id.login_login);
        //输入密码
        password = findViewById(R.id.login_password);
        //输入邮箱
        email = findViewById(R.id.login_email);
        //获取注册的点击事件
        register = findViewById(R.id.login_register);
        //忘记密码的
        forgetpassword = findViewById(R.id.login_forgetpassword);
        back = findViewById(R.id.user_back);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }
    @Override
    public void onSucceed(Object object) {
        if(object instanceof LoginBean){
            LoginBean    loginBean= (LoginBean)object;
            LoginBean.ResultBean result = loginBean.getResult();
            Toast.makeText(LoginActivity.this,loginBean.getMessage(), Toast.LENGTH_SHORT).show();
            if(loginBean.getStatus().equals("0000")){
                //登录成功之后进行sp存储状态
                //获取极光的密码
                String jiGuangPwd = result.getJiGuangPwd();
                String userName = result.getUserName();
                Toast.makeText(LoginActivity.this,loginBean.getMessage(), Toast.LENGTH_SHORT).show();
                //使用请谅解的存储登录之后的状态
                SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor edit = user.edit();
                edit.putString("sessionId",result.getSessionId());
                edit.putInt("userId",result.getUserId());
                //获取到头像
                edit.putString("headPic",result.getHeadPic());
                edit.putString("name",result.getNickName());
                edit.putBoolean("state",true);
                edit.commit();
                JGLogint(userName,jiGuangPwd);
                //成功跳转到首页
                startActivity(new Intent(LoginActivity.this,TabberActivity.class));
                finish();
            }
        }
    }
    @Override
    public void onError(String message) {

    }
}