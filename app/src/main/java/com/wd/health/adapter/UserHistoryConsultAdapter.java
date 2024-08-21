package com.wd.health.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wd.health.R;
import com.wd.health.bean.userbean.UserHistoryConsultBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.adapter</p>
 * <p>简述:用户的适配器</p>
 *
 * @author 张凯涛
 * @date 2022/7/19
 */
public class UserHistoryConsultAdapter extends CommonAdapter<UserHistoryConsultBean.ResultBean> {
    public UserHistoryConsultAdapter(Context context, int layoutId, List<UserHistoryConsultBean.ResultBean> datas) {
        super(context, layoutId, datas);
    }
    @Override
    protected void convert(ViewHolder holder, UserHistoryConsultBean.ResultBean resultBean, int position) {
        //设置图片

        Glide.with(holder.itemView.getContext()).load(resultBean.getImagePic()).into((ImageView)holder.itemView.findViewById(R.id.userhistory_iamge_adapter));

        //设置名字
        holder.setText(R.id.userhistory_name_adapter,resultBean.getDoctorName());

        //设置问诊时间
        //将得到的时间戳转换成时间
        long inquiryTime = resultBean.getInquiryTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //如果它本来就是long类型的,则不用写这一步
//        long lt = new Long(inquiryTime);
        Date date = new Date(inquiryTime);
        String format = simpleDateFormat.format(date);
        holder.setText(R.id.userhistory_time_adapter,format);
    }
}
