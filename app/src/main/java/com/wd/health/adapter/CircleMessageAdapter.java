package com.wd.health.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.wd.health.R;
import com.wd.health.bean.circleoffriends.CircleMessageBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.adapter</p>
 * <p>简述:病友圈的详情的适配器</p>
 *
 * @author 张凯涛
 * @date 2022/7/27
 */
public class CircleMessageAdapter extends CommonAdapter<CircleMessageBean.ResultBean> {

    public CircleMessageAdapter(Context context, int layoutId, List<CircleMessageBean.ResultBean> datas) {
        super(context, layoutId, datas);
    }
    @Override
    protected void convert(ViewHolder holder, CircleMessageBean.ResultBean resultBean, int position) {

        //设置明湖曾
        holder.setText(R.id.circle_adapter_name,resultBean.getAdoptNickName());

        //设置病症
        holder.setText(R.id.circle_adapter_bingzheng,resultBean.getDisease());
        //设置科室
        holder.setText(R.id.circle_adapter_office,resultBean.getDepartment());

        //病症详情
        holder.setText(R.id.circle_adapter_message,resultBean.getDetail());

        //设置经历
        holder.setText(R.id.circle_adapter_jingli,resultBean.getTreatmentProcess());

        //设置医院
        holder.setText(R.id.circle_adapter_yiyuan,resultBean.getTreatmentHospital());

        //获取时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //如果它本来就是long类型的,则不用写这一步
        //设置时间
        Date date = new Date(resultBean.getTreatmentEndTime());
        String format = simpleDateFormat.format(date);
        holder.setText(R.id.circle_adapter_time,format);

        //判断是否有上传图片
            if(!resultBean.getPicture().equals("")){
                ImageView xBanner = holder.itemView.findViewById(R.id.xb);
                xBanner.setVisibility(View.VISIBLE);
                //设置轮播图
                Glide.with(holder.itemView.getContext()).load(resultBean.getPicture()).into((ImageView) holder.itemView.findViewById(R.id.xb));
            }
        //获取收藏采访的个数
        holder.setText(R.id.circle_adapter_number1,resultBean.getCollectionNum()+"");
        //评论的额个数
        holder.setText(R.id.circle_adapter_number2,resultBean.getCommentNum()+"");

        //设置发表意见的点击事件
      holder.itemView.findViewById(R.id.circle_adapter_pinglun).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            myclick.fapingluln(resultBean.getSickCircleId(),resultBean.getCommentNum());
          }
      });
        ImageView imageView = holder.itemView.findViewById(R.id.circle_adapter_shoucang);

        //设置收藏的点击事件
      switch (resultBean.getCollectionFlag()){
          case 1:
              //已经收藏了则改变图片的显示
              imageView.setImageResource(R.mipmap.shoucang2);
              //取消收藏
             resultBean.setCollectionFlag(2);
              break;
          case 2:
              imageView.setImageResource(R.mipmap.wodeshoucang);
              //需要收藏
              imageView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      myclick.shoucang(resultBean.getSickCircleId());
                      resultBean.setCollectionFlag(1);
                  }
              });
              break;
      }
    }
    //设置接口回调
    private Myclick myclick;

    public void setMyclick(Myclick myclick) {
        this.myclick = myclick;
    }

    public  interface  Myclick{
        void  fapingluln(int sickCircleId,int number);
        //收藏的点击事件
        void  shoucang(int sickCircleId);
    }
}
