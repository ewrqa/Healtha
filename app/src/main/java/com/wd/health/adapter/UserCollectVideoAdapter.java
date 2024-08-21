package com.wd.health.adapter;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.Glide;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.wd.health.R;
import com.wd.health.bean.userbean.UserCollectVideoBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.adapter</p>
 * <p>简述:用户收藏视频的适配器</p>
 *
 * @author 张凯涛
 * @date 2022/7/25
 */
public class UserCollectVideoAdapter extends CommonAdapter<UserCollectVideoBean.ResultBean> {
    public UserCollectVideoAdapter(Context context, int layoutId, List<UserCollectVideoBean.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, UserCollectVideoBean.ResultBean resultBean, int position) {
        //获取本地的时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //如果它本来就是long类型的,则不用写这一步

        //设置时间
        Date date = new Date(resultBean.getCreateTime());
        String format = simpleDateFormat.format(date);
        holder.setText(R.id.usercollect_time,format);

        //设置点击事件
                //设置视频的播放
                StandardGSYVideoPlayer standardGSYVideoPlayer = holder.getView(R.id.usercollect_video);
                standardGSYVideoPlayer.setUp(resultBean.getOriginalUrl(),false,resultBean.getTitle());

    //设置点击事件进行播放
        holder.getView(R.id.usercollect_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standardGSYVideoPlayer.startPlayLogic();
            }
        });

    }
}
