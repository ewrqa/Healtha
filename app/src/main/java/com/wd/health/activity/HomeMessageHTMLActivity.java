package com.wd.health.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;
/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:健康调查的HTML</p>
 * @author 张凯涛
 * @date 2022/7/15
 */
public class HomeMessageHTMLActivity extends BaseActivity{

    private WebView webView;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initDate() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);


        webView.getSettings().setJavaScriptEnabled(true);//设置为JS
        webView.setWebViewClient(new WebViewClient());//在软件中进入网址不会跳转到系统的浏览器
        //跳转到健康测评的页面
        webView.loadUrl("https://www.wjx.cn/jq/33939807.aspx");//加载网址

    }
    @Override
    protected void initView() {
        webView = findViewById(R.id.webview);

    }
    @Override
    protected int getLayout() {
        return R.layout.activity_home_message_htmlactivity;
    }

    @Override
    public void onSucceed(Object object) {

    }

    @Override
    public void onError(String message) {

    }
}