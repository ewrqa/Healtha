package com.wd.health.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wd.health.R;
import com.wd.health.bean.userbean.UserCollcetMessageBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.adapter</p>
 * <p>简述:用户收藏的适配器</p>
 *
 * @author 张凯涛
 * @date 2022/7/25
 */
public class UserCollectMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<UserCollcetMessageBean.ResultBean>list;
    private Context context;
    private String[] split;
    private String createDate;

    public UserCollectMessageAdapter(List<UserCollcetMessageBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==0){

            //连接布局
            View inflate = View.inflate(context, R.layout.item_healthmessage_0, null);

            return new NullHolder(inflate);

        }else if(viewType==1){

            View inflate = View.inflate(context, R.layout.item_healthmessage_1, null);

            return new OneHodler(inflate);

        }else{
            View inflate = View.inflate(context, R.layout.item_healthmessage_3, null);

            return new ThreadHolder(inflate);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){

            case 0:

              NullHolder nullHolder=(NullHolder)holder;
                //进行赋值
                nullHolder.message0.setText(list.get(position).getTitle());

                //获取本地时间
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
                formatter.setTimeZone(TimeZone.getTimeZone("GMT+08"));
                Date curDate = new Date(System.currentTimeMillis());
                createDate = formatter.format(curDate);
                nullHolder.time0.setText(createDate);
                //点击事件
                nullHolder.message0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myAdapter.getdocter(list.get(position).getId());
                    }
                });
                break;
            case 1:
                OneHodler oneHodler=(OneHodler)holder;

                //进行赋值
                oneHodler.message1.setText(list.get(position).getTitle());
                oneHodler.time1.setText(createDate);
                //设置图片
                //设置站位图和错位图
                Glide.with(context).load(list.get(position).getThumbnail()).into(((OneHodler) holder).imageView11);
                oneHodler.message1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myAdapter.getdocter(list.get(position).getId());
                    }
                });

                break;
            case  3:
               ThreadHolder threadHolder=(ThreadHolder) holder;
                //进行赋值
                threadHolder.message3.setText(list.get(position).getTitle());

                threadHolder.time3.setText(createDate);
                threadHolder.message3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myAdapter.getdocter(list.get(position).getId());
                    }
                });
                //设置图片
                //设置站位图和错位图
                Glide.with(context).load(split[0]).into(((ThreadHolder) holder).imageView31);
                Glide.with(context).load(split[1]).into(((ThreadHolder) holder).imageView32);
                Glide.with(context).load(split[2]).into(((ThreadHolder) holder).imageView33);
                break;
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    //没有图片的组件
    class NullHolder  extends RecyclerView.ViewHolder{

        private final TextView name0;
        private final TextView time0;
        private final TextView message0;

        public NullHolder(@NonNull View itemView) {
            super(itemView);
            //获取文本时间作者
            name0 = itemView.findViewById(R.id.healthmessage0_name);
            time0 = itemView.findViewById(R.id.healthmessage0_time);
            message0 = itemView.findViewById(R.id.healthmessage0_message);
        }
    }
    //没有图片的组件
    class OneHodler  extends RecyclerView.ViewHolder{
        private final TextView name1;
        private final TextView time1;
        private final TextView message1;
        private final ImageView imageView11;
        public OneHodler(@NonNull View itemView) {
            super(itemView);
            name1 = itemView.findViewById(R.id.healthmessage1_name);
            time1 = itemView.findViewById(R.id.healthmessage1_time);
            message1 = itemView.findViewById(R.id.healthmessage1_message);
            imageView11 = itemView.findViewById(R.id.healthmessage1_image);
        }
    }
    //三个图片的组件
    class ThreadHolder  extends RecyclerView.ViewHolder{
        private final TextView name3;
        private final TextView time3;
        private final TextView message3;
        private final ImageView imageView31;
        private final ImageView imageView32;
        private final ImageView imageView33;
        public ThreadHolder(@NonNull View itemView) {
            super(itemView);
            name3 = itemView.findViewById(R.id.healthmessage3_name);
            time3 = itemView.findViewById(R.id.healthmessage3_time);
            message3 = itemView.findViewById(R.id.healthmessage3_message);
            imageView31 = itemView.findViewById(R.id.healthmessage3_image1);
            imageView32 = itemView.findViewById(R.id.healthmessage3_image2);
            imageView33 = itemView.findViewById(R.id.healthmessage3_image3);
        }
    }

    @Override
    public int getItemViewType(int position) {
        //根据图片的长度不同返回不同的值
        split = list.get(position).getThumbnail().split(";");
        if(split.length==0){
            return 0;
        }else if(split.length==1){
            return 1;
        }
        return 3;
    }

    public  interface MyAdapter{
        void getdocter(int  id);
    }
    private Home_HealthAdapter.MyAdapter myAdapter;

    public void setMyAdapter(Home_HealthAdapter.MyAdapter myAdapter) {

        this.myAdapter = myAdapter;

    }

}
