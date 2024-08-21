package com.wd.health.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.health.R;
import com.wd.health.adapter.UserHistoryConsultAdapter;
import com.wd.health.base.BaseActivity;
import com.wd.health.bean.userbean.UserHistoryConsultBean;
import com.wd.health.presenter.user.UserPresenter;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:用户历史问诊</p>
 * @author 张凯涛
 * @date 2022/7/19
 */
public class UserHistoryConsultActivity extends BaseActivity<UserPresenter>{

    //列表
    private RecyclerView recyclerView;
    //返回图像
    private ImageView back;
    //适配器
    private UserHistoryConsultAdapter userHistoryConsultAdapter;

    @Override
    protected UserPresenter initPresenter() {
        return new UserPresenter();
    }

    @Override
    protected void initDate() {
        //历史问诊的请求
        getPresenter().getUserHistoryConsult(UserHistoryConsultActivity.this,1,15);

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
        recyclerView = findViewById(R.id.userhistory_rec);
        back = findViewById(R.id.userhistory_back);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_user_history_consult;
    }
    @Override
    public void onSucceed(Object object) {
        //用户历史咨询的数据请求
        if(object instanceof UserHistoryConsultBean) {
            UserHistoryConsultBean userHistoryConsultBean = (UserHistoryConsultBean) object;
            List<UserHistoryConsultBean.ResultBean> result = userHistoryConsultBean.getResult();
            Toast.makeText(UserHistoryConsultActivity.this, userHistoryConsultBean.getMessage(), Toast.LENGTH_SHORT).show();
          //设置适配器
            if(userHistoryConsultAdapter==null){
                    userHistoryConsultAdapter = new UserHistoryConsultAdapter(UserHistoryConsultActivity.this, R.layout.item_userhistoryconsult_adapter, result);
                    recyclerView.setLayoutManager(new LinearLayoutManager(UserHistoryConsultActivity.this));
                    recyclerView.setAdapter(userHistoryConsultAdapter);
                }else{
                    userHistoryConsultAdapter.notifyDataSetChanged();
                }
        }
    }
    @Override
    public void onError(String message) {
    }
}