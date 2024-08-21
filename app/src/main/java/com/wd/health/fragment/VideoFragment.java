package com.wd.health.fragment;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.kd.easybarrage.Barrage;
import com.kd.easybarrage.BarrageView;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.GSYSampleADVideoPlayer;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.wd.health.R;
import com.wd.health.adapter.VideoAdapter;
import com.wd.health.base.BaseFragment;
import com.wd.health.base.BasePresenter;
import com.wd.health.bean.BarrageBean;
import com.wd.health.bean.userbean.UserMoneyBean;
import com.wd.health.bean.video.BuyVideoBean;
import com.wd.health.bean.video.CollectVideoBean;
import com.wd.health.bean.video.DeleteCollectVideoBean;
import com.wd.health.bean.video.VideoBean;
import com.wd.health.presenter.video.Videopresenter;
import com.wd.health.utils.videoutils.DpTools;
import com.wd.health.utils.videoutils.ScrollCalculatorHelper;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:视频fragment</p>
 *
 * @author 张凯涛
 * @date 2022/7/15
 */
public class VideoFragment extends BaseFragment<Videopresenter>{
    //存储标题
    String[] title={"儿科","常识","健身","美容","心里","养生"};
    //不同状态的视频栏目id
    int[] type={1,2,3,4,5,6};
    private TabLayout tabLayout;
    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;
    private PopupWindow videopopupWindow;
    private ImageView guanbi;
    private TextView myhbi;
    private BarrageView barrageView;

    @Override
    protected Videopresenter initPresenter() {
        return new  Videopresenter();
    }

    @Override
    protected void initData() {
        getPresenter().getVideo(getContext(),type[0]);

        //设置视频的滑动播放
        //现获取屏幕的宽高
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        //自定义播放帮助类 限定范围为屏幕一半的上下偏移180dp
        ScrollCalculatorHelper scrollCalculatorHelper = new  ScrollCalculatorHelper(R.id.video_video
                , displayMetrics.heightPixels / 2 - DpTools.dip2px(getActivity(), 180),
                displayMetrics.heightPixels / 2 + DpTools.dip2px(getActivity(), 180));
        //设置视频翻页效果
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();

        pagerSnapHelper.attachToRecyclerView(recyclerView);
        //排列方式 默认排列方式
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        //滑动自动播放
        //recyclerview的页面监听事件
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private int lastVisibleItemPosition;

            private int firstVisibleItemPosition;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                scrollCalculatorHelper.onScrollStateChanged(recyclerView, newState);

            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //第一视频
                firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                //最后一个视频
                lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();

                //一个屏幕显示一个item 所以参数固定为1
                scrollCalculatorHelper.onScroll(recyclerView, firstVisibleItemPosition, lastVisibleItemPosition, 1);
            }
        });

    }
    @Override
    protected void initView(View view) {
        //获取播放视频的控件
        tabLayout = view.findViewById(R.id.video_Tab);

        recyclerView = view.findViewById(R.id.video_rec);

        //获取弹幕的id
        barrageView = view.findViewById(R.id.video_barrageView);

        //记性循环负值
        for(int i=0;i<title.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(title[i]));
        }
        //根据监听切换不同的视频
        //根据不同的id播放不同的视频
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //根据不同的状态传输不一样啊的id
                getPresenter().getVideo(getContext(),type[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_video_fragment;
    }
    @Override
    public void onSucceed(Object object) {
        //视频的数据
        if(object instanceof VideoBean){
            VideoBean      videoBean=(VideoBean)object;
            List<VideoBean.ResultBean> result = videoBean.getResult();
            if(result!=null){
                    videoAdapter = new VideoAdapter(getContext(), R.layout.item_video_adapter, result);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(videoAdapter);
            }
        }
        if(object instanceof BuyVideoBean){
            BuyVideoBean      buyVideoBean=(BuyVideoBean)object;
            Toast.makeText(getContext(), buyVideoBean.getMessage(), Toast.LENGTH_SHORT).show();
            if(buyVideoBean.getStatus().equals("0000")){
                Toast.makeText(getContext(), buyVideoBean.getMessage(), Toast.LENGTH_SHORT).show();
                videoAdapter.notifyDataSetChanged();
                //关闭pop
                videopopupWindow.dismiss();
            }
        }
        //用户余额
        if(object instanceof UserMoneyBean){
            UserMoneyBean       userMoneyBean= (UserMoneyBean)object;
            if(userMoneyBean.getStatus().equals("0000")){
                myhbi.setText(userMoneyBean.getResult()+"");
                if(userMoneyBean.getStatus().equals("0000")){
                    Toast.makeText(getContext(), userMoneyBean.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
        //收藏视频
        if(object instanceof CollectVideoBean){
            CollectVideoBean    collectVideoBean= (CollectVideoBean)object;
            if(collectVideoBean.getStatus().equals("0000")){
                Toast.makeText(getContext(), collectVideoBean.getMessage(), Toast.LENGTH_SHORT).show();

            }

        }
        //取消收藏
        if(object instanceof DeleteCollectVideoBean){
            DeleteCollectVideoBean    collectVideoBean= (DeleteCollectVideoBean)object;
            if(collectVideoBean.getStatus().equals("0000")){
                Toast.makeText(getContext(), collectVideoBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        //弹幕
        if(object instanceof BarrageBean) {

            BarrageBean barrageBean = (BarrageBean) object;

            Log.i("a", barrageBean.getMessage());
            Toast.makeText(getContext(), barrageBean.getMessage(), Toast.LENGTH_SHORT).show();
            //弹幕
            for (int i = 0; i < barrageBean.getResult().size(); i++) {

                Barrage barrage = new Barrage(barrageBean.getResult().get(i).getContent(), R.color.black, true);

                barrageView.addBarrage(barrage);
            }
        }

        videoAdapter.notifyDataSetChanged();
        clickAdapter();
    }
    //适配器的点击事件
    public void  clickAdapter(){
        videoAdapter.setClickAdapter(new VideoAdapter.ClickAdapter() {
            @Override
            public void butvideo(int videoId, int price) {
                buyVideoPopwindow(videoId,price);
            }
            @Override
            public void fadanmu(int videoId) {

            }

            @Override
            public void showBanrrage(int videoId, boolean f) {
                if(f){
                    barrageView.setVisibility(View.GONE);

                    barrageView.destroy();
                }else{
                    barrageView.setVisibility(View.VISIBLE);

                    getPresenter().getshowDanmu(getContext(),videoId);
                }
            }
            //走收藏接口
            @Override
            public void shoucang1(int videoId) {
                getPresenter().getDelelteVideo(getContext(),videoId);
            }
            @Override
            public void shoucang2(int videoId) {
                getPresenter().getCollectVideo(getContext(),videoId);
            }
        });
    }
    @Override
    public void onError(String message) {


    }
    public void buyVideoPopwindow(int videoId,int price){
        //连接布局
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.popwindow_buyvideo, null);
        //设置剩余金额的展示
        videopopupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //显示popupwindow
        View inflate1 = LayoutInflater.from(getContext()).inflate(R.layout.activity_video_fragment, null);

        videopopupWindow.showAsDropDown(inflate1, Gravity.BOTTOM,0,0);



        //获取点击购买的按钮
        Button buy = inflate.findViewById(R.id.pop_video_buy);
        TextView jiage = inflate.findViewById(R.id.pop_video_price);

        myhbi = inflate.findViewById(R.id.pop_video_myHbi);
        ImageView guanbi = inflate.findViewById(R.id.pop_video_guanbi);

        guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videopopupWindow.dismiss();
            }
        });
        //点击事件
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().getBuyVideo(getContext(),videoId,price);
            }
        });
        jiage.setText(price+"H币");
        //走我的hbi接口
        getPresenter().getuserMoney(getContext());

    }
    //设置关闭的时候视频也关闭
    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }
}