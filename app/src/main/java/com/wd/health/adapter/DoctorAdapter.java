package com.wd.health.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wd.health.R;
import com.wd.health.bean.doctor.OfficedoctorBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.adapter</p>
 * <p>简述:医生列表的适配器</p>
 *
 * @author 张凯涛
 * @date 2022/7/18
 */
public class DoctorAdapter extends CommonAdapter<OfficedoctorBean.ResultBean> {
    public DoctorAdapter(Context context, int layoutId, List<OfficedoctorBean.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, OfficedoctorBean.ResultBean resultBean, int position) {

        //设置医生的头像
        Glide.with(holder.itemView.getContext()).load(resultBean.getImagePic()).into((ImageView)holder.getView(R.id.doctor_adapter_image) );
        //设置姓名
        holder.setText(R.id.doctor_adapter_name,resultBean.getDoctorName());

    }
}
