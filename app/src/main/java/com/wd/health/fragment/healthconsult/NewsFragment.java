package com.wd.health.fragment.healthconsult;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.wd.health.R;
import com.wd.health.base.BaseFragment;
import com.wd.health.presenter.HomePapgePresenter;

public class NewsFragment extends BaseFragment<HomePapgePresenter>{

    private RecyclerView recyclerView;

    @Override
    protected HomePapgePresenter initPresenter() {
        return new HomePapgePresenter();
    }

    @Override
    protected void initData() {
        //接收传过来的值
        SharedPreferences user = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        int id1 = user.getInt("id", 0);
        getPresenter().gewenzhenzixunmessage(getContext(),1);

    }
    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.news_rec);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_news_fragment;
    }

    @Override
    public void onSucceed(Object object) {

    }
    @Override
    public void onError(String message) {

    }
}