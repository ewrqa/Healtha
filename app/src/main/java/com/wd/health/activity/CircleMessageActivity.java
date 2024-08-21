package com.wd.health.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wd.health.R;
import com.wd.health.adapter.CircleMessageAdapter;
import com.wd.health.adapter.CommentAdapter;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;
import com.wd.health.bean.circleoffriends.CircleCommentBean;
import com.wd.health.bean.circleoffriends.CircleCommentInputBean;
import com.wd.health.bean.circleoffriends.CircleMessageBean;
import com.wd.health.bean.circleoffriends.CircleofFragmentMessageBean;
import com.wd.health.bean.circleoffriends.CollectCircleBean;
import com.wd.health.presenter.circleopresenter.CircilePresemter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:病友圈评论列表 </p>
 * @author 张凯涛
 * @date 2022/7/15
 */

public class CircleMessageActivity extends BaseActivity<CircilePresemter>{

    //展示头像
    private ImageView head;
    //要展示的列表
    private RecyclerView recyclerView;
    //标题
    private TextView titlea;
    //登录的状态
    private boolean state;
    //病友圈的列表
    private CircleMessageAdapter circleMessageAdapter;
    //得到的病友圈列表的数据
    private List<CircleCommentBean.ResultBean> result;
    //点击发送评论列表
    private Button fasong;
    //发送评论的输入
    private EditText input;
    //得到的帖子id
    private  int id;

    @Override
    protected CircilePresemter initPresenter() {
        return new CircilePresemter();
    }

    @Override
    protected void initDate() {

        //获取点获取帖子的id
        Intent intent = getIntent();
        int sickCircleId = intent.getIntExtra("id", 0);
        //评论的标题
        String title = intent.getStringExtra("title");

        //进行登录状态值的获取
        SharedPreferences user1 =getSharedPreferences("user", Context.MODE_PRIVATE);
        //获取登录的状态
        state = user1.getBoolean("state", false);
        String headPic = user1.getString("headPic", "");
        titlea.setText(title);
        //设置图片
        if(state){
            Glide.with(CircleMessageActivity.this).load(headPic).into(head);
        }else{
            head.setImageResource(R.mipmap.tupian);
        }
        //请求的接口  病友圈评论列表
        getPresenter().getCircleMessage(CircleMessageActivity.this,sickCircleId);
        //给顶部导航栏进行设置
    }
    @Override
    protected void initView() {
        //头像
        head = findViewById(R.id.circle_message_head);

        //列表
        recyclerView = findViewById(R.id.circle_message_rec);

        //标题
        titlea = findViewById(R.id.circle_message_title);

    }
    @Override
    protected int getLayout() {
        return R.layout.activity_circle_message;
    }
    @Override
    public void onSucceed(Object object) {
        //评论列表
        if(object instanceof CircleMessageBean){

            CircleMessageBean       circleMessageBean=(CircleMessageBean)object;

            CircleMessageBean.ResultBean result = circleMessageBean.getResult();
            ArrayList<CircleMessageBean.ResultBean> resultBeans = new ArrayList<>();
            //添加到集合当中
            resultBeans.addAll(Collections.singleton(result));
            if(result!=null){
                //设置适配器
                circleMessageAdapter = new CircleMessageAdapter(CircleMessageActivity.this, R.layout.item_circlemessage_adapter, resultBeans);
                recyclerView.setLayoutManager(new LinearLayoutManager(CircleMessageActivity.this));
                recyclerView.setAdapter(circleMessageAdapter);

            }
        }
        //用户收藏病友圈
        if(object instanceof CollectCircleBean){
            CollectCircleBean    collectCircleBean= (CollectCircleBean)object;
            Toast.makeText(CircleMessageActivity.this, collectCircleBean.getMessage(), Toast.LENGTH_SHORT).show();
            circleMessageAdapter.notifyDataSetChanged();
        }
        //评论的列表展示
        if(object instanceof CircleCommentBean){
            CircleCommentBean       circleCommentBean=(CircleCommentBean)object;
            result = circleCommentBean.getResult();
            //有值的时候展示
            if(circleCommentBean.getResult()!=null){
                //展示列表
                fapinglun(View.GONE,result,id);
            }
        }
        //发表评论
        if(object instanceof CircleCommentInputBean){
            CircleCommentInputBean       circleCommentInputBean=(CircleCommentInputBean)object;
            Toast.makeText(CircleMessageActivity.this,circleCommentInputBean.getMessage() , Toast.LENGTH_SHORT).show();
            //记性判断是否已经发表成功
            if(circleCommentInputBean.getStatus().equals("0000")){
                circleMessageAdapter.notifyDataSetChanged();
            }
        }
        //接口回调
        MyClick();
    }
    //适配器点击事件的回调
    private  void MyClick(){
        circleMessageAdapter.setMyclick(new CircleMessageAdapter.Myclick() {
            @Override
            public void fapingluln(int sickCircleId, int number) {
                //根据评论的数量来进行判断
                if(number==0){
//                    Toast.makeText(CircleMessageActivity.this, sickCircleId+"", Toast.LENGTH_SHORT).show();
                    //点击判断是否有值
                    fapinglun(View.VISIBLE,result,sickCircleId);
                }else{
                    id=sickCircleId;
                    Toast.makeText(CircleMessageActivity.this, sickCircleId+"", Toast.LENGTH_SHORT).show();

//                    fapinglun(View.VISIBLE,result,sickCircleId);
                        //如果拥有数据则直接走请求接口
                    getPresenter().getCircleComment(CircleMessageActivity.this,sickCircleId,1,10);
                }
            }
            //用户收藏病友圈
            @Override
            public void shoucang(int sickCircleId) {
                    getPresenter().getCollectCirce(CircleMessageActivity.this,sickCircleId);
            }
        });
    }
    @Override
    public void onError(String message) {

    }
    //发表评论的popwind
    public  void  fapinglun(int view,List<CircleCommentBean.ResultBean> result,int sickCircleId){
        //要展示的页面
        View inflate = LinearLayout.inflate(CircleMessageActivity.this, R.layout.popwindow_comment, null);
        //要在哪个页面展示
        View inflate1 = LinearLayout.inflate(CircleMessageActivity.this, R.layout.activity_circle_message, null);
        PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupWindow.showAtLocation(inflate1, Gravity.CENTER,0,0);
        //获取id
        RelativeLayout relativeLayout = inflate.findViewById(R.id.aaaaa);
        ImageView guanbi = inflate.findViewById(R.id.pop_fapinglun_guanbi);
        ImageView wu = inflate.findViewById(R.id.pop_fapinglun_wu);
        RecyclerView recyclerView = inflate.findViewById(R.id.pop_fapinglun_rec);
        //获取输入框中的值

        input = inflate.findViewById(R.id.pop_fapinglun_input);

        //点击发送走接口

        fasong = inflate.findViewById(R.id.pop_fapinglun_fasong);

        //发送的点击事件
        fasong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CircleMessageActivity.this, sickCircleId+"", Toast.LENGTH_SHORT).show();
                getPresenter().getCircleCommentInput(CircleMessageActivity.this,sickCircleId,input.getText().toString());
            }
        });
        //判断是否显示
        wu.setVisibility(view);
        //如果判断是
        if(view==View.VISIBLE){
            //隐藏列表
            recyclerView.setVisibility(View.GONE);
        }else{
                //rec不隐藏
            recyclerView.setVisibility(View.VISIBLE);
            CommentAdapter commentAdapter = new CommentAdapter(CircleMessageActivity.this, R.layout.item_comment_adapter, result);
            //设置popwine欧文的适配器
            recyclerView.setAdapter(commentAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(CircleMessageActivity.this));

        }
        //点击关闭
        guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        //设置背景色的透明度
        relativeLayout.getBackground().setAlpha(90);
    }
}