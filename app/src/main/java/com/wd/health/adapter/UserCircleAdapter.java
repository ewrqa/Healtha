package com.wd.health.adapter;

import android.content.Context;

import com.wd.health.R;
import com.wd.health.bean.userbean.UserCollectCircleBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.adapter</p>
 * <p>简述:用户的朋友圈的适配器</p>
 *
 * @author 张凯涛
 * @date 2022/7/25
 */
public class UserCircleAdapter extends CommonAdapter<UserCollectCircleBean.ResultBean>{
    public UserCircleAdapter(Context context, int layoutId, List<UserCollectCircleBean.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, UserCollectCircleBean.ResultBean resultBean, int position) {

        //标题进行赋值
        holder.setText(R.id.mycircle_title,resultBean.getTitle());

        //设置内容
        holder.setText(R.id.mycircle_message,resultBean.getDetail());

        long releaseTime = resultBean.getReleaseTime();
        //获取时间


        SimpleDateFormat mm = new SimpleDateFormat("MM");

        Date date = new Date(releaseTime);

        String format = mm.format(date);
        holder.setText(R.id.mycircle_month,format);

        //设置天数
        SimpleDateFormat mm2 = new SimpleDateFormat("dd");

        Date date2 = new Date(releaseTime);

        String format2 = mm2.format(date2);
        holder.setText(R.id.mycircle_day,format2+"日");

    }
}
