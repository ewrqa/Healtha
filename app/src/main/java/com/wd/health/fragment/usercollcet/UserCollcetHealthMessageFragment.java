package com.wd.health.fragment.usercollcet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.scwang.smart.refresh.header.BezierRadarHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.wd.health.R;
import com.wd.health.activity.HomeActivityHtmlActivity;
import com.wd.health.adapter.Home_HealthAdapter;
import com.wd.health.adapter.UserCollectMessageAdapter;
import com.wd.health.base.BaseFragment;
import com.wd.health.base.BasePresenter;
import com.wd.health.bean.userbean.UserCollcetMessageBean;
import com.wd.health.bean.userbean.UserMessageBean;
import com.wd.health.presenter.user.UserPresenter;

import java.util.ArrayList;
import java.util.List;

public class UserCollcetHealthMessageFragment extends BaseFragment<UserPresenter> {

    private SmartRefreshLayout shuaxin;
    private RecyclerView recyclerView;
    //加载的页数
    int page=1;
    ArrayList<UserCollcetMessageBean.ResultBean> resultBeans = new ArrayList<>();

    private UserCollectMessageAdapter userCollectMessageAdapter;

    @Override
    protected UserPresenter initPresenter() {
        return new UserPresenter();
    }

    @Override
    protected void initData() {

        //刷新的数据
        shuaxin.setRefreshHeader(new BezierRadarHeader(getContext()).setEnableHorizontalDrag(true));
        getPresenter().getUserCollcetMessage(getContext(),1);
        //设置刷新和加载的监听
        shuaxin.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    page++;
                getPresenter().getUserCollcetMessage(getContext(),page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                getPresenter().getUserCollcetMessage(getContext(),page);
            }
        });
    }
    @Override
    protected void initView(View view) {
        shuaxin = view.findViewById(R.id.shuaxin);
        recyclerView = view.findViewById(R.id.rec);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_user_collcet_health_message_fragment;
    }

    @Override
    public void onSucceed(Object object) {
        //收藏健康资讯
        if(object instanceof UserCollcetMessageBean){
            //停止加载和动画的监听
            shuaxin.finishLoadMore();
            shuaxin.finishRefresh();
            UserCollcetMessageBean     userCollcetMessageBean=(UserCollcetMessageBean)object;
            List<UserCollcetMessageBean.ResultBean> result = userCollcetMessageBean.getResult();
            if(result!=null){
               resultBeans.addAll(result);
                if(page==1){
                    resultBeans.clear();
                }
                resultBeans.addAll(result);

                    userCollectMessageAdapter = new UserCollectMessageAdapter(result, getContext());

                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                    recyclerView.setAdapter(userCollectMessageAdapter);

                    userCollectMessageAdapter.setMyAdapter(new Home_HealthAdapter.MyAdapter() {
                        @Override
                        public void getdocter(int id) {
                            Intent intent = new Intent(getContext(), HomeActivityHtmlActivity.class);
                            intent.putExtra("id",id);
                            startActivity(intent);
                        }
                    });
            }
        }
    }
    @Override
    public void onError(String message) {

    }
}