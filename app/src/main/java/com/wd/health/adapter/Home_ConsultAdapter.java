package com.wd.health.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wd.health.R;
import com.wd.health.bean.doctor.OfficeBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.adapter</p>
 * <p>简述:首页问诊咨询的适配器</p>
 *
 * @author 张凯涛
 * @date 2022/7/15
 */
public class Home_ConsultAdapter extends CommonAdapter<OfficeBean.ResultBean> {
    public Home_ConsultAdapter(Context context, int layoutId, List<OfficeBean.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, OfficeBean.ResultBean resultBean, int position) {

        //获取office的图片
        Glide.with(holder.itemView.getContext()).load(resultBean.getPic()).into((ImageView) holder.getView(R.id.wenzhenzixun_image));

        //设置文本
        holder.setText(R.id.wenzhenzixun_message,resultBean.getDepartmentName());

    }
}
