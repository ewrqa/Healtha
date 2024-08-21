package com.wd.health.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.makeramen.roundedimageview.RoundedImageView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.wd.health.activity.HealthinFormtionActivity;
import com.wd.health.R;
import com.wd.health.activity.DoctorActivity;
import com.wd.health.activity.HomeActivityHtmlActivity;
import com.wd.health.activity.HomeMessageHTMLActivity;
import com.wd.health.activity.HomePageInputActivity;
import com.wd.health.activity.LoginActivity;
import com.wd.health.activity.UserActivity;
import com.wd.health.adapter.Home_ConsultAdapter;
import com.wd.health.adapter.Home_HealthAdapter;
import com.wd.health.base.BaseFragment;
import com.wd.health.bean.HealthInFormtionMessageBean;
import com.wd.health.bean.HealthInformationBean;
import com.wd.health.bean.HomePageXbannerBean;
import com.wd.health.bean.doctor.OfficeBean;
import com.wd.health.presenter.HomePapgePresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;
/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:首页的fragment</p>
 *
 * @author 张凯涛
 * @date 2022/7/15
 */
public class HomeFragment extends BaseFragment<HomePapgePresenter>{

    //健康资讯需要的id
    private  int plateId;
    //录播图
    private XBanner xBanner;
    //轮播图的数据
    private List<HomePageXbannerBean.ResultBean> result;
    //搜索框
    private EditText input;
    //健康资讯的rec
    private RecyclerView conslutrecyclerView;
    //健康资讯的顶部导航栏
    private TabLayout tableLayout;
    //健康资讯的tab
    int[] type={1,2,3,4,5};
    //健康资讯选中id
    private int i;
    private RecyclerView jiankangrec;
    private RoundedImageView headimage;
    //登录的状态值
    private boolean state;
    private LinearLayout gengduo;
    private List<HealthInformationBean.ResultBean> healthInformationBeanResult;

    private String platename;
    private ImageView jiankangceping;

    @Override
    protected HomePapgePresenter initPresenter() {
        return new HomePapgePresenter();
    }
    @Override
    protected void initData() {
        //进行登录状态值的获取
        SharedPreferences user1 = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        //获取登录的状态
        state = user1.getBoolean("state", false);
        String headPic = user1.getString("headPic", "");
        //轮播图的请求
        getPresenter().getXbanner(getContext());
        //问诊咨询的请求
        getPresenter().getConsult(getContext());
        //健康资讯的请求
        getPresenter().getwenzhenzixun(getContext());
        //健康资讯的咨询
        getPresenter().gewenzhenzixunmessage(getContext(),plateId);
        //设置登录成功的的头像
        if(state){
            Glide.with(getContext()).load(headPic).into(headimage);
        }else{
            headimage.setImageResource(R.mipmap.tupian);
        }
        //更换头像
        //设置tab的监听
     tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
         @Override
         //选中的时候
         public void onTabSelected(TabLayout.Tab tab) {
             i = type[tab.getPosition()];
             platename = healthInformationBeanResult.get(tab.getPosition()).getName();
             getPresenter().gewenzhenzixunmessage(getContext(),i);
         }
         @Override
         public void onTabUnselected(TabLayout.Tab tab) {

         }
         @Override
         public void onTabReselected(TabLayout.Tab tab) {

         }
     });
        //允许滑动
        tableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
     //设置头像的点击事件
        headimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //点击判断是否已经登陆过
                if(state){
                    startActivity(new Intent(getActivity(), UserActivity.class));
                }else{
                    //未登录跳转
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        });
        //设置点击更多的方法
        gengduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到相对应的页面
                //传入对应 的id
                Intent intent = new Intent(getActivity(), HealthinFormtionActivity.class);
                intent.putExtra("plateId",i);
                intent.putExtra("name",platename);
                startActivity(intent);
            }
        });
        //健康测评的点击事件
        jiankangceping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),HomeMessageHTMLActivity.class));
            }
        });
        //奢姿input的监听时间
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //选中的时候
                startActivity(new Intent(getContext(), HomePageInputActivity.class));
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    @Override
    protected void initView(View view) {
        //录播图
        xBanner = view.findViewById(R.id.home_xbanner);
        //输入
        input = view.findViewById(R.id.homepage_input);
        //设置输入框的透明度
        input.getBackground().setAlpha(90);
        conslutrecyclerView = view.findViewById(R.id.home_recwenzhenzixun);
        conslutrecyclerView.setHasFixedSize(true);
        conslutrecyclerView.setNestedScrollingEnabled(false);
        tableLayout = view.findViewById(R.id.home_tablayout);
        jiankangrec = view.findViewById(R.id.home_jiankangrec);
        //获取首页头像的id
        headimage = view.findViewById(R.id.home_head);
        //获取更多的点击事件
        gengduo = view.findViewById(R.id.home_gengduo);
        //健康测评的id
        jiankangceping = view.findViewById(R.id.home_jianknagpingce);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_home_fragment;
    }
    @Override
    public void onSucceed(Object object) {
        //轮播图
           if(object instanceof HomePageXbannerBean){
               HomePageXbannerBean     homePageXbannerBean=(HomePageXbannerBean)object;
                if(homePageXbannerBean.getResult()!=null){
                    result = homePageXbannerBean.getResult();
                  xBanner.setData(result,null);
                 xBanner.setmAdapter(new XBanner.XBannerAdapter() {
                     @Override
                     public void loadBanner(XBanner banner, Object model, View view, int position) {
                         Glide.with(getContext()).load(result.get(position).getImageUrl()).into((ImageView)view);
                     }
                 });
                 xBanner.setPageTransformer(Transformer.Rotate);
                }
           }
           //问诊咨询
           if(object instanceof OfficeBean){

               OfficeBean      officeBean= (OfficeBean)object;
               if(officeBean.getResult()!=null){

                   List<OfficeBean.ResultBean> result = officeBean.getResult();

                   Home_ConsultAdapter home_consultAdapter = new Home_ConsultAdapter(getContext(), R.layout.adapter_wenzhenzixun, result);

                   //设置适配器
                   conslutrecyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));

                   conslutrecyclerView.setAdapter(home_consultAdapter);
                   //问诊咨询的点击事件
                   home_consultAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                       @Override
                       public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {

                           //判断是否已经登录过
                           //登录过就给将登录的状态传到医生列表

                               Intent intent = new Intent(getActivity(), DoctorActivity.class);
                               intent.putExtra("id",i);
                               startActivity(intent);
                       }
                       @Override
                       public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                           return false;
                       }
                   });
               }
           }
           //健康测评的成功回调
           if(object instanceof HealthInformationBean){
               HealthInformationBean     healthInformationBean=(HealthInformationBean)object;
               healthInformationBeanResult = healthInformationBean.getResult();
               if(healthInformationBean.getResult()!=null){
                   //设置顶部导航栏的文本
                   for(HealthInformationBean.ResultBean show:healthInformationBean.getResult()){
                      tableLayout.addTab(tableLayout.newTab().setText(show.getName()));
                   }
               }
           }
           //健康资讯的文本
           if(object instanceof  HealthInFormtionMessageBean){
               HealthInFormtionMessageBean   healthInFormtionMessageBean=(HealthInFormtionMessageBean)object;
               if(healthInFormtionMessageBean.getResult()!=null){
                   List<HealthInFormtionMessageBean.ResultBean> result = healthInFormtionMessageBean.getResult();
                   Home_HealthAdapter home_healthAdapter = new Home_HealthAdapter(result, getContext());

                   jiankangrec.setAdapter(home_healthAdapter);

                   jiankangrec.setLayoutManager(new LinearLayoutManager(getContext()));

                 home_healthAdapter.setMyAdapter(new Home_HealthAdapter.MyAdapter() {
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