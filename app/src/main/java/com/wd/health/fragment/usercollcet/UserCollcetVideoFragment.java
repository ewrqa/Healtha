package com.wd.health.fragment.usercollcet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.wd.health.R;
import com.wd.health.adapter.UserCollectVideoAdapter;
import com.wd.health.base.BaseFragment;
import com.wd.health.base.BasePresenter;
import com.wd.health.bean.userbean.UserCollectVideoBean;
import com.wd.health.presenter.user.UserPresenter;

import java.util.List;

public class UserCollcetVideoFragment extends BaseFragment<UserPresenter> {

    private SmartRefreshLayout shuaxin;
    private RecyclerView recyclerView;
    private  int page=1;

    @Override
    protected UserPresenter initPresenter() {
        return new UserPresenter();
    }

    @Override
    protected void initData() {
        getPresenter().getUserCollectVideo(getContext(),page);
    }
    @Override
    protected void initView(View view) {
        //刷新
        shuaxin = view.findViewById(R.id.shuaxin);
        //rec
        recyclerView = view.findViewById(R.id.rec);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_user_collcet_video_fragment;
    }

    @Override
    public void onSucceed(Object object) {
        //收藏的数据
        if(object instanceof UserCollectVideoBean){
            UserCollectVideoBean       userCollectVideoBean=  (UserCollectVideoBean)object;
            List<UserCollectVideoBean.ResultBean> result = userCollectVideoBean.getResult();
            if(result!=null){
                UserCollectVideoAdapter userCollectVideoAdapter = new UserCollectVideoAdapter(getContext(), R.layout.item_collectvido, result);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(userCollectVideoAdapter);
            }
        }
    }
    @Override
    public void onError(String message) {


    }
}