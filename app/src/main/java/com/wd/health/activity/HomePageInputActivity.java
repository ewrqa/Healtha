package com.wd.health.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wd.health.R;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;
import com.wd.health.customview.InPutView;

public class HomePageInputActivity extends BaseActivity{
    /**
     * <p>项目名称:Health</p>
     * <p>包名:com.wd.health.base</p>
     * <p>简述:热门搜索页面</p>
     * @author 张凯涛
     * @date 2022/7/15
     */

    private EditText input;
    private TextView button;
    private InPutView inputview;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initDate() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = input.getText().toString();


                Button button = new Button(HomePageInputActivity.this);


                button.setText(s);

                inputview.addView(button);

            }
        });

    }
    @Override
    protected void initView() {
        //输入框
        input = findViewById(R.id.inputview_input);
        //按钮
        button = findViewById(R.id.inputbox_sousuo);

        inputview = findViewById(R.id.inputview_view);

    }
    @Override
    protected int getLayout() {
        return R.layout.activity_home_page_input;
    }

    @Override
    public void onSucceed(Object object) {

    }

    @Override
    public void onError(String message) {

    }
}