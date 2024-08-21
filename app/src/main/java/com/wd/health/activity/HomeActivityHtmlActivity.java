package com.wd.health.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.bean.HomeHealthMessageBean;
import com.wd.health.bean.HomeHealthMessageClrearCollectBean;
import com.wd.health.bean.HomeHealthMessageCollectBean;
import com.wd.health.presenter.HomePapgePresenter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:健康资讯的超链接HTMl</p>
 * @author 张凯涛
 * @date 2022/7/15
 */
public class HomeActivityHtmlActivity extends BaseActivity<HomePapgePresenter>{
    //文本view
    private WebView webView;
    //点击返回
    private ImageView back;
    //作者
    private TextView zuozhe;
    //事件
    private TextView time;
    //标题
    private TextView title;
    //分享
    private ImageView fenxiang;
    //搜搜
    private ImageView shoucang;

    private  boolean fload=false;
    private ImageView shoucang2;

    @Override
    protected HomePapgePresenter initPresenter() {
        return new HomePapgePresenter();
    }
    @Override
    protected void initDate() {

        //使用sp获取登录的状态
        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        boolean state = user.getBoolean("state", false);

        //获取资讯的id
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        //设置收藏和分享的点击事件
//        if(fload){
//            shoucang.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(state){
//                        shoucang.setImageResource(R.mipmap.shoucang2);
//                        getPresenter().getHealthMessageShoucang(HomeActivityHtmlActivity.this,id);
//                        //成功之后进行显示填充
//                        fload=false;
//                    }else{
//                        Toast.makeText(HomeActivityHtmlActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }else{
//            shoucang.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    shoucang.setImageResource(R.mipmap.wodeshoucang);
//                    getPresenter().getHealthMessageQuxiaoShoucang(HomeActivityHtmlActivity.this,id);
//                    fload=true;
//                }
//            });
//        }
        //收藏的点击时间
        shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state){
                    if(fload){
                        shoucang.setImageResource(R.mipmap.shoucang2);
                        getPresenter().getHealthMessageShoucang(HomeActivityHtmlActivity.this,id);
                        //成功之后进行显示填充
                        fload=false;
                    }else{
                        shoucang.setImageResource(R.mipmap.wodeshoucang);
                        getPresenter().getHealthMessageQuxiaoShoucang(HomeActivityHtmlActivity.this,id);
                        fload=true;
                    }
                }else{
                    Toast.makeText(HomeActivityHtmlActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //点击分享的点击事件
        fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state){

                }else{
                    Toast.makeText(HomeActivityHtmlActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        getPresenter().getHelthMessage(HomeActivityHtmlActivity.this,id);
        //设置允许js
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        //点击进行返回销毁
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    protected void initView() {
        webView = findViewById(R.id.webview);
        //获取返回
        back = findViewById(R.id.healthmesage_back);

        zuozhe = findViewById(R.id.healthmessage_zuozhe);
        //时间
        time = findViewById(R.id.healthmessage_time);

        title = findViewById(R.id.healthmessage_title);

        //获取收藏和分享
        shoucang = findViewById(R.id.healthmessage_shoucang);
        shoucang2 = findViewById(R.id.healthmessage_shoucang2);
        fenxiang = findViewById(R.id.healthmessage_fenxaing);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_home_html;
    }
    @Override
    public void onSucceed(Object object) {
        if(object instanceof HomeHealthMessageBean){
            HomeHealthMessageBean     homeHealthMessageBean=(HomeHealthMessageBean)object;
            if(homeHealthMessageBean.getMessage().equals("查询成功")){
                webView.loadData(homeHealthMessageBean.getResult().getContent(),"text/html","utf-8");
            //获取标题和作者还有事件

                title.setText(homeHealthMessageBean.getResult().getTitle());

                zuozhe.setText(homeHealthMessageBean.getResult().getSource());

                //获得时间戳
                long releaseTime = homeHealthMessageBean.getResult().getReleaseTime();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

                Date date = new Date(releaseTime);

                String format = simpleDateFormat.format(date);

                time.setText(format);
            }
        }
        //收藏的数据
        if(object instanceof HomeHealthMessageCollectBean){
            HomeHealthMessageCollectBean homeHealthMessageShoucangBean=(HomeHealthMessageCollectBean)object;
            Toast.makeText(HomeActivityHtmlActivity.this, homeHealthMessageShoucangBean.getMessage(), Toast.LENGTH_SHORT).show();
            if(homeHealthMessageShoucangBean.getStatus().equals("收藏成功")){
                Toast.makeText(HomeActivityHtmlActivity.this, homeHealthMessageShoucangBean.getMessage(), Toast.LENGTH_SHORT).show();
                shoucang.setVisibility(View.GONE);
                shoucang2.setVisibility(View.VISIBLE);
            }
        }
        //取消收藏
        if(object instanceof HomeHealthMessageClrearCollectBean){
            HomeHealthMessageClrearCollectBean   homeHealthMessageClrearCollectBean= (HomeHealthMessageClrearCollectBean)object;
            if(homeHealthMessageClrearCollectBean.getMessage().equals("取消成功")){
                Toast.makeText(HomeActivityHtmlActivity.this, homeHealthMessageClrearCollectBean.getMessage(), Toast.LENGTH_SHORT).show();
                shoucang.setVisibility(View.VISIBLE);
                shoucang2.setVisibility(View.GONE);
            }
        }
    }
    @Override
    public void onError(String message) {

    }
}