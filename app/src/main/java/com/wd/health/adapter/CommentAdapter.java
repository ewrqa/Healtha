package com.wd.health.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wd.health.R;
import com.wd.health.bean.circleoffriends.CircleCommentBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.adapter</p>
 * <p>简述:用户评论列表的适配器</p>
 *
 * @author 张凯涛
 * @date 2022/7/29
 */
public class CommentAdapter extends CommonAdapter<CircleCommentBean.ResultBean> {
    public CommentAdapter(Context context, int layoutId, List<CircleCommentBean.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, CircleCommentBean.ResultBean resultBean, int position) {

        Context context = holder.itemView.getContext();

        //设置头像
        Glide.with(context).load(resultBean.getHeadPic()).into((ImageView) holder.itemView.findViewById(R.id.comment_adapter_head));

        //设置名称
        holder.setText(R.id.comment_adapter_name,resultBean.getNickName());

        //设置输入的内容8t
        holder.setText(R.id.comment_adapter_message,resultBean.getContent());

        //获取时间戳
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");

        long commentTime = resultBean.getCommentTime();
        Date date = new Date(commentTime);

        String format = simpleDateFormat.format(date);

        //设置时间
        holder.setText(R.id.comment_Adapter_time,format);

        //设置点赞和反对的个数
        holder.setText(R.id.comment_adapter_dianzannumber,resultBean.getSupportNum()+"");

        holder.setText(R.id.comment_adapter_quxiaodianzannumber,resultBean.getOpposeNum()+"");

        ImageView dianzan = holder.itemView.findViewById(R.id.comment_adapter_dianzan);

        ImageView budianzan = holder.itemView.findViewById(R.id.comment_adapter_quxiaodianzan);
        //设置点咋的点击效果
       switch (resultBean.getOpinion()){
           case 1:
               //设置显示的状态
               dianzan.setImageResource(R.mipmap.dianzanho);
                    dianzan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //修改状态
                            resultBean.setOpinion(2);
                        }
                    });
               break;
           case 2:
               budianzan.setImageResource(R.mipmap.quxiaodianzanhou);
                budianzan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        resultBean.setOpinion(1);
                    }
                });
               break;
       }
    }
}
