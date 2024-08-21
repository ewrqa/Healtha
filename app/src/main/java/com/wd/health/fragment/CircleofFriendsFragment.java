package com.wd.health.fragment;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.header.BezierRadarHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.wd.health.R;
import com.wd.health.adapter.CircleFriendsAdapter;
import com.wd.health.base.BaseFragment;
import com.wd.health.bean.circleoffriends.CircleofFragmentMessageBean;
import com.wd.health.presenter.circleopresenter.CircilePresemter;

import java.util.ArrayList;
import java.util.List;

public class CircleofFriendsFragment extends BaseFragment<CircilePresemter>{
    /**
     * <p>项目名称:Health</p>
     * <p>包名:com.wd.health.base</p>
     * <p>简述:病友圈的fragment页面</p>
     * @author 张凯涛
     * @date 2022/7/15
     */
    //同一个fragment对应不同的id  复用同一个fragment
    private int id1;
    //列表
    private RecyclerView recyclerView;
    //朋友圈的适配器
    private CircleFriendsAdapter circleFriendsAdapter;
    //刷新
    private SmartRefreshLayout shuaxin;
    int page=1;
    ArrayList<CircleofFragmentMessageBean.ResultBean> resultBeans = new ArrayList<>();

    public  CircleofFriendsFragment(int id1){
        this.id1=id1;
    }
    @Override
    protected CircilePresemter initPresenter() {
        return new CircilePresemter();
    }

    @Override
    protected void initData() {

        shuaxin.setRefreshHeader(new BezierRadarHeader(getContext()).setEnableHorizontalDrag(true));
        shuaxin.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));

        Toast.makeText(getContext(), id1+"", Toast.LENGTH_SHORT).show();
        getPresenter().getCircle(getContext(),id1,page,10);

        shuaxin.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getPresenter().getCircle(getContext(),id1,page,10);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                getPresenter().getCircle(getContext(),id1,page,10);
            }
        });
    }
    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.circlefragment_rec);

        shuaxin = view.findViewById(R.id.shuaxin);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_circleof_friends_fragment;
    }
    @Override
    public void onSucceed(Object object) {
        if(object instanceof CircleofFragmentMessageBean){
            shuaxin.finishLoadMore();
            shuaxin.finishRefresh();
            CircleofFragmentMessageBean  circleofFragmentMessageBean =(CircleofFragmentMessageBean)object;
            List<CircleofFragmentMessageBean.ResultBean> result = circleofFragmentMessageBean.getResult();
            if(result!=null){
                if(page==1){
                    resultBeans.clear();
                }
                //添加数据
                    resultBeans.addAll(circleofFragmentMessageBean.getResult());
                    circleFriendsAdapter = new CircleFriendsAdapter(getContext(), R.layout.item_circlo_adapter, result);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(circleFriendsAdapter);
            }else{
                Toast.makeText(getContext(), "暂无数据", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onError(String message) {

    }
}