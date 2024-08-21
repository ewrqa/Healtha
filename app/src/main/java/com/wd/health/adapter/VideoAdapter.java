package com.wd.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.wd.health.R;
import com.wd.health.bean.video.VideoBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.adapter</p>
 * <p>简述:视频的适配器</p>
 *
 * @author 张凯涛
 * @date 2022/7/26
 */
public class VideoAdapter extends CommonAdapter<VideoBean.ResultBean> {

    private Context context;

    //设置状态进行判断是否已经收藏
    private  boolean state=false;
    public VideoAdapter(Context context, int layoutId, List<VideoBean.ResultBean> datas) {
        super(context, layoutId, datas);
    }
    @Override
    protected void convert(ViewHolder holder, VideoBean.ResultBean resultBean, int position) {
        //设置视频的播放
        StandardGSYVideoPlayer standardGSYVideoPlayer = holder.getView(R.id.video_video);

        //打开默认播放的视频
        if (position==0){
            //默认播放视频
            standardGSYVideoPlayer.startPlayLogic();
        }
            //获取视频控件的id
//        context = holder.item.getContext();
        ImageView goumai = holder.itemView.findViewById(R.id.video_goumai);

        ImageView shoucang = holder.itemView.findViewById(R.id.video_shoucang);

        //设置购买人数
        holder.setText(R.id.video_goumai_number,resultBean.getBuyNum()+"");
        //进行判断用户是否已经购买了视频
        switch (resultBean.getWhetherBuy()){
            case 1:
                //修改图片
                goumai.setImageResource(R.mipmap.fadanmu);
                standardGSYVideoPlayer.setUp(resultBean.getOriginalUrl(),false,resultBean.getTitle());
                goumai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //已经购买 播放全部的视频
                        //电极发送弹幕
                        clickAdapter.fadanmu(resultBean.getCategoryId());
                    }
                });
            break;
            case  2:
                //未购买
                //未购买
                standardGSYVideoPlayer.setUp(resultBean.getShearUrl(),false,resultBean.getTitle());
                //购买的点击事件
                goumai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickAdapter.butvideo(resultBean.getId(),resultBean.getPrice());
                    }
                });
                break;
        }

//        //判读是不是已经收藏过该视频
//        if(state){
//            //展示不同的状态
//
//            shoucang.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //2为取消收藏
//                    clickAdapter.shoucang1(resultBean.getCategoryId(),2);
//                }
//            });
//            state=false;
//        }else{
//            //未收藏
//
//            shoucang.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //1需要收藏
//
//                }
//            });
//         state=true;
//        }
        if(resultBean.getWhetherCollection()==1){
            shoucang.setImageResource(R.mipmap.shoucang2);
            shoucang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //修改状态
                    clickAdapter.shoucang1(resultBean.getCategoryId());

                    resultBean.setWhetherCollection(2);
                }
            });
        }else{
            shoucang.setImageResource(R.mipmap.wodeshoucang);
            shoucang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickAdapter.shoucang2(resultBean.getCategoryId());

                    resultBean.setWhetherCollection(1);
                }
            });
        }

        //弹幕显示的点击事件
        ImageView imageView = holder.itemView.findViewById(R.id.video_danmu);

        //设置弹幕的点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state){
                    imageView.setImageResource(R.mipmap.dabmuguan);
                    clickAdapter.showBanrrage(resultBean.getId(),state);
                    state=false;
                }else{
                    imageView.setImageResource(R.mipmap.danmukai);
                    clickAdapter.showBanrrage(resultBean.getId(),state);
                    state=true;
                }
            }
        });


    }
    private  ClickAdapter clickAdapter;

    public void setClickAdapter(ClickAdapter clickAdapter) {
        this.clickAdapter = clickAdapter;
    }
    //点击事件的接口回调
    public  interface  ClickAdapter{
        //购买的点击事件
        void  butvideo(int videoId,int price);
        //发送弹幕
        void  fadanmu(int videoId);
        //显示弹幕
        void showBanrrage(int videoId,boolean f);
        //收藏
        void  shoucang1(int videoId);

        void shoucang2(int videoId);
    }

}
