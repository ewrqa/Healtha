package com.wd.health.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;


import com.wd.health.R;
import com.wd.health.activity.CircleMessageActivity;
import com.wd.health.bean.circleoffriends.CircleofFragmentMessageBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * <p>项目名称:Dimensions of health</p>
 * <p>包名:com.wd.health.dimensionsofhealth.a.view.adapter.circleofFriendsAdapter</p>
 * <p>简述:发布朋友圈的适配器</p>
 *
 * @author 张凯涛
 * @date 2022/5/3
 */
public class CircleFriendsAdapter extends CommonAdapter<CircleofFragmentMessageBean.ResultBean> {

    public CircleFriendsAdapter(Context context, int layoutId, List<CircleofFragmentMessageBean.ResultBean> datas) {
        super(context, layoutId, datas);
    }
    @Override
    protected void convert(ViewHolder holder, CircleofFragmentMessageBean.ResultBean resultBean, int position) {

        RelativeLayout relativeLayout= holder.itemView.findViewById(R.id.xiagnqing);

        Context context = holder.itemView.getContext();

        RelativeLayout xuanshang = holder.itemView.findViewById(R.id.xuanshang);
        //设置标题
        holder.setText(R.id.circle_adapter_title,resultBean.getTitle());

        //设置发表提起
        holder.setText(R.id.circle_adapter_time,resultBean.getReleaseTime()+"");

        //设置发表的内容
        holder.setText(R.id.circle_adapter_message,resultBean.getDetail());

        //设置收藏和建议的个数
        holder.setText(R.id.fapengyouquan_shoucnag,"收藏"+resultBean.getCollectionNum());
        holder.setText(R.id.circle_adapter_jianyi,"建议"+resultBean.getCommentNum());

        //判断是不是有悬赏
        if(resultBean.getAmount()!=0){
                //设置悬赏的额度
            xuanshang.setVisibility(View.VISIBLE);
            //设置悬赏的金额
            holder.setText(R.id.circle_adapter_xuanshang,resultBean.getAmount()+"");
        }

        //设置详情的点击事件
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击进入详情
                Intent intent = new Intent(context, CircleMessageActivity.class);
                intent.putExtra("id",resultBean.getSickCircleId());
                intent.putExtra("title",resultBean.getTitle());
                context.startActivity(intent);
            }
        });
    }
}
