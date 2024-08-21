package com.wd.health.adapter;

import android.content.Context;

import com.wd.health.R;
import com.wd.health.bean.userbean.UserCollectLost;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.adapter</p>
 * <p>简述:用户收藏列表的适配器</p>
 *
 * @author 张凯涛
 * @date 2022/7/25
 */
public class UserCollectListAdapter extends CommonAdapter<UserCollectLost.ResultBean> {
    public UserCollectListAdapter(Context context, int layoutId, List<UserCollectLost.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, UserCollectLost.ResultBean resultBean, int position) {
        //设置标题
        holder.setText(R.id.usercollectlist_title,resultBean.getTitle());

        //设置时间
        //获取本地的时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //如果它本来就是long类型的,则不用写这一步
        //设置时间
        Date date = new Date(resultBean.getCreateTime());
        String format = simpleDateFormat.format(date);
        holder.setText(R.id.usercollectlist_time,format);
        //设置内容
        holder.setText(R.id.usercollectlist_message,resultBean.getDisease());

    }
}
