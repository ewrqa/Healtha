package com.wd.health.fragment.usercollcet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.scwang.smart.refresh.header.BezierRadarHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.wd.health.R;
import com.wd.health.adapter.UserCollectListAdapter;
import com.wd.health.base.BaseFragment;
import com.wd.health.base.BasePresenter;
import com.wd.health.bean.userbean.UserCollectLost;
import com.wd.health.presenter.user.UserPresenter;

import java.util.ArrayList;
import java.util.List;

public class UserCollcetCircleFragment extends BaseFragment<UserPresenter>{

    private SmartRefreshLayout shuaxin;
    private RecyclerView recyclerView;
    private  int page=1;
    ArrayList<UserCollectLost.ResultBean> resultBeans = new ArrayList<>();
    private UserCollectListAdapter userCollectListAdapter;

    @Override
    protected UserPresenter initPresenter() {
        return new UserPresenter();
    }

    @Override
    protected void initData() {
        //设置刷新
        //设置 Header 为 贝塞尔雷达 样式
        shuaxin.setRefreshHeader(new BezierRadarHeader(getContext()).setEnableHorizontalDrag(true));

        getPresenter().getUserCollectList(getContext(),1);
        //设置刷新的监听
        shuaxin.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getPresenter().getUserCollectList(getContext(),page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                getPresenter().getUserCollectList(getContext(),page);
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
        return R.layout.activity_user_collcet_circle_fragment;
    }

    @Override
    public void onSucceed(Object object) {
        //用户收藏列表 的数据
        if(object instanceof UserCollectLost){
            //停止动画
            shuaxin.finishRefresh();
            shuaxin.finishLoadMore();
            UserCollectLost      userCollectLost=(UserCollectLost)object;
            List<UserCollectLost.ResultBean> result = userCollectLost.getResult();

            if(result!=null){
                //设置适配器
                if(page==1){
                    resultBeans.clear();
                }
                    resultBeans.addAll(result);
                    userCollectListAdapter = new UserCollectListAdapter(getContext(), R.layout.item_usercollectlist, result);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(userCollectListAdapter);
            }
        }
    }
    @Override
    public void onError(String message) {

    }
}