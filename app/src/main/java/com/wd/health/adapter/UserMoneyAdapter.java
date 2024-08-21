package com.wd.health.adapter;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.bean.userbean.UserMoneyMessageBean;

import java.util.Date;
import java.util.List;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.adapter</p>
 * <p>简述:用户金钱消费记录适配器</p>
 *
 * @author 张凯涛
 * @date 2022/7/22
 */
public class UserMoneyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<UserMoneyMessageBean.ResultBean>list;
    private String format;

    public UserMoneyAdapter(Context context, List<UserMoneyMessageBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }
    //连接布局
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==1){
            //连接是消费的布局
            View inflate = View.inflate(context, R.layout.item_usermoney_adapter1, null);

            return new jia(inflate);
        }else{
            View inflate = View.inflate(context, R.layout.item_usermoney_adapter2, null);


            return new jian(inflate);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            switch (getItemViewType(position)){
                case  1:
                    jia     jia= (jia)holder;

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

                    long createTime = list.get(position).getCreateTime();

                    Date date = new Date(createTime);

                    format = simpleDateFormat.format(date);
                    //签到
                    if(list.get(position).getType()==1){
                        jia.title1.setText("签到");

                        jia.money1.setText("+"+list.get(position).getChangeNum()+"H币");
                    }
                    if(list.get(position).getType()==2){
                        jia.title1.setText("病友圈首评");

                        jia.money1.setText("+"+list.get(position).getChangeNum()+"H币");

                    }
                    if(list.get(position).getType()==3){
                        jia.title1.setText("首发病友圈");

                        jia.money1.setText("+"+list.get(position).getChangeNum()+"H币");
                    }
                    if(list.get(position).getType()==4){
                        jia.title1.setText("晚上档案");

                        jia.money1.setText("+"+list.get(position).getChangeNum()+"H币");

                    }
                    if(list.get(position).getType()==7){

                        jia.title1.setText("悬赏奖励");

                        jia.money1.setText("+"+list.get(position).getChangeNum()+"H币");
                    }

                    if(list.get(position).getType()==8){
                        jia.title1.setText("邀请奖励");

                        jia.money1.setText("+"+list.get(position).getChangeNum()+"H币");

                    }
                    if(list.get(position).getType()==10){
                        jia.title1.setText("问诊收入");

                        jia.money1.setText("+"+list.get(position).getChangeNum()+"H币");

                    }
                    if(list.get(position).getType()==11){
                        jia.title1.setText("观看咨询");

                        jia.money1.setText("+"+list.get(position).getChangeNum()+"H币");

                    }
                    if(list.get(position).getType()==13){
                        jia.title1.setText("绑定身份证");

                        jia.money1.setText("+"+list.get(position).getChangeNum()+"H币");

                    }
                    if(list.get(position).getType()==14){
                        jia.title1.setText("绑定银行卡");

                        jia.money1.setText("+"+list.get(position).getChangeNum()+"H币");

                    }
                    //添加收入
                    if(list.get(position).getType()==15){
                        //签

                        jia.title1.setText("充值");

                        jia.money1.setText("+"+list.get(position).getChangeNum()+"H币");

                        jia.time1.setText(format);

                    }
                    break;
                    //消费
                case  2:
                    jian     jian= (jian)holder;

                    if(list.get(position).getType()==5){
                        jian.title2.setText("健康测评");

                        jian.money2.setText("+"+list.get(position).getChangeNum()+"H币");
                    }

                    if(list.get(position).getType()==6){
                        jian.title2.setText("悬赏消费");

                        jian.money2.setText("+"+list.get(position).getChangeNum()+"H币");

                    }

                    if(list.get(position).getType()==9){

                        jian.title2.setText("问诊消费");

                        jian.money2.setText(list.get(position).getChangeNum()+"H币");

                        jian.time2.setText(format);

                    }
                    if(list.get(position).getType()==16){
                        jian.title2.setText("体现");

                        jian.money2.setText(list.get(position).getChangeNum()+"H币");

                    }

                    if(list.get(position).getType()==17){
                        jian.title2.setText("购买健康视频");

                        jian.money2.setText(list.get(position).getChangeNum()+"H币");

                    }
                    break;
            }
    }

    //判断消费还是收入
    @Override
    public int getItemViewType(int position) {
        //收入
        if(list.get(position).getDirection()==1){
            //收入
        return 1;
        }
        //消费
        return 2;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    //获取控件
    class  jia extends  RecyclerView.ViewHolder{

        private final TextView money1;
        private final TextView time1;
        private final TextView title1;

        public jia(@NonNull View itemView) {
            super(itemView);
            money1 = itemView.findViewById(R.id.money_adapter_money1);
            time1 = itemView.findViewById(R.id.money_adapter_time1);
            title1 = itemView.findViewById(R.id.money_adapter_title1);
        }
    }
    //加
    class  jian extends  RecyclerView.ViewHolder{
        private final TextView money2;
        private final TextView time2;
        private final TextView title2;
        public jian(@NonNull View itemView) {
            super(itemView);
            money2 = itemView.findViewById(R.id.money_adapter_money2);
            time2 = itemView.findViewById(R.id.money_adapter_time2);
            title2 = itemView.findViewById(R.id.money_adapter_title2);
        }
    }
}
