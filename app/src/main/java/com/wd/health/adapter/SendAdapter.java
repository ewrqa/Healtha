package com.wd.health.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.wd.health.R;
import com.wd.health.utils.VoicePlayingBgUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * <p>项目名称:Lianxi220505</p>
 * <p>包名:com.bawei.lianxi.view.adapter</p>
 * <p>简述:</p>
 *
 * @author 连文科
 * @date 2022/5/5
 */
public class SendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Message> list;
    private MediaPlayer mediaPlayer;
    boolean isPlayClient =false;
    boolean isPlayDoctor= false;
    private MediaPlayer mp;

    public SendAdapter(Context context, List<Message> list) {
        this.context = context;
        this.list = list;
    }
    //连接布局
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==1){
            return new YouViewHolder(LayoutInflater.from(context).inflate(R.layout.send_item2, parent,false));
        }else{
            return new ZuoViewHolder(LayoutInflater.from(context).inflate(R.layout.send_item1, parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = list.get(position);
        //发送信息的类别
        ContentType contentType = message.getContentType();

        if(holder instanceof YouViewHolder){
            //设置头像

            SharedPreferences user = context.getSharedPreferences("user", Context.MODE_PRIVATE);
            String headPic = user.getString("headPic", "");

            RoundedImageView head = ((YouViewHolder)holder).head2;
            Glide.with(context).load(headPic).into(head);

            switch (contentType){
                case text:
                    //隐藏
                    //播放语音
                    ((YouViewHolder)holder).startvocie2.setVisibility(View.GONE);
                    //点击播放语音的时长
                    ((YouViewHolder)holder).time2.setVisibility(View.INVISIBLE);
                    //隐藏语音的发送和显示
                    //获取文字发送的信息
                    TextContent content = (TextContent) message.getContent();
                    ((YouViewHolder) holder).message2.setText(content.getText());
                    break;
                case image:
//                    //隐藏
//                    ((YouViewHolder) holder).message2.setVisibility(View.VISIBLE);
//                    ((YouViewHolder)holder).head2.setVisibility(View.VISIBLE);
//
//                    //获取图片的发送信息
//                    ImageContent imagecontent = (ImageContent) message.getContent();
//                    String Path = imagecontent.getLocalThumbnailPath();
//                    Bitmap bitmap = BitmapFactory.decodeFile(Path);
                    break;

                    //判断是否属于语音的发送
                case voice:
                    //隐藏发送的文本信息
                    ((YouViewHolder) holder).message2.setVisibility(View.INVISIBLE);
                    //获取语音信息
                    VoiceContent voiceContent = (VoiceContent) message.getContent();
                    //显示时长
                    ((YouViewHolder) holder).time2.setText(voiceContent.getDuration()+"s");
                    //配置语音
                    media(voiceContent);
                    //点击播放语音
                    ((YouViewHolder) holder).startvocie2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //点播放是点击进行播放 再次点击是暂停播放
                            if(isPlayClient){
                                ((YouViewHolder) holder).startvocie2.setImageResource(R.mipmap.yuyin1);
                                mediaPlayer.stop();
                                mediaPlayer.release();
                                isPlayClient = false;
                            }else{
                                ((YouViewHolder) holder).startvocie2.setImageResource(R.mipmap.yuyin);
                                media(voiceContent);
                                if(mediaPlayer!=null){
                                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                        @Override
                                        public void onPrepared(MediaPlayer mp) {
                                            VoicePlayingBgUtil voicePlayingBgUtil = new VoicePlayingBgUtil(voiceContent.getDuration());
                                            voicePlayingBgUtil.voicePlay();
                                            mp.start();
                                            isPlayClient = true;
                                        }
                                    });
                                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            mp.stop();
                                            mp.release();
                                            mp = null;
                                            ((YouViewHolder) holder).startvocie2.setImageResource(R.mipmap.yuyin1);
                                            isPlayClient = false;
                                        }
                                    });
                                }
                            }
                        }
                    });
                    break;
            }
        }else if(holder instanceof ZuoViewHolder){
            switch (contentType){
                //文本的接和显示
                case text:
                    //隐藏
                    ((ZuoViewHolder) holder).info1.setVisibility(View.VISIBLE);
                    ((ZuoViewHolder) holder).tupian2.setVisibility(View.INVISIBLE);
                    //获取文字发送的信息
                    TextContent content = (TextContent) message.getContent();
                    ((ZuoViewHolder) holder).info1.setText(content.getText());
                    break;
                case image:
                    //隐藏
                    ((ZuoViewHolder) holder).tupian2.setVisibility(View.VISIBLE);
                    ((ZuoViewHolder) holder).info1.setVisibility(View.INVISIBLE);
                    //获取图片的发送信息
                    ImageContent imagecontent = (ImageContent) message.getContent();
                    String Path = imagecontent.getLocalThumbnailPath();
                    Bitmap bitmap = BitmapFactory.decodeFile(Path);
                    ((ZuoViewHolder) holder).tupian2.setImageBitmap(bitmap);
                    break;

                case voice:
                    ((ZuoViewHolder) holder).tupian2.setVisibility(View.INVISIBLE);
                    ((ZuoViewHolder) holder).info1.setVisibility(View.INVISIBLE);
                    VoiceContent voiceContent = (VoiceContent) message.getContent();
                    ((ZuoViewHolder) holder).l_voice.setText(voiceContent.getDuration()+"");
                    media(voiceContent);
                    ((ZuoViewHolder) holder).head1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(isPlayDoctor){
                                ((ZuoViewHolder) holder).l_start.setImageResource(R.mipmap.yuyin);
                                mp.stop();
                                isPlayDoctor = false;
                            }else{
                                ((ZuoViewHolder) holder).l_start.setImageResource(R.mipmap.tupian);
                                media(voiceContent);
                                if(mp!=null){

                                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                        @Override
                                        public void onPrepared(MediaPlayer mp) {
                                            mp.start();
                                            isPlayDoctor = true;
                                        }
                                    });
                                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            mp.stop();
                                            mp.release();
                                            mp = null;
                                            ((ZuoViewHolder) holder).l_start.setImageResource(R.mipmap.yuyin);
                                            isPlayDoctor = false;
                                        }
                                    });
                                }
                                isPlayDoctor = true;
                            }
                        }
                    });
                    break;
            }
        }
    }

    //语音的发送
    private void media(VoiceContent voiceContent){
        mediaPlayer = new MediaPlayer();
        //获取语音的文件的路径
        File file = new File(voiceContent.getLocalPath());
        try {

            FileInputStream fileInputStream = new FileInputStream(file);
            //缓存到sp卡当中
            mediaPlayer.setDataSource(fileInputStream.getFD());
            mediaPlayer.prepareAsync();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        //获取用户Id
        UserInfo myInfo = JMessageClient.getMyInfo();
        long userID = myInfo.getUserID();

        UserInfo fromUser = list.get(position).getFromUser();
        long userID1 = fromUser.getUserID();

        if(userID == userID1){
            return 1;
        }else{
            return 2;
        }
    }

    //医生端
    class ZuoViewHolder extends RecyclerView.ViewHolder {
        ImageView tupian2,head1;
        TextView time1,info1;
        TextView l_voice;
        ImageView l_start;
        public ZuoViewHolder(@NonNull View itemView) {
            super(itemView);
            time1 = itemView.findViewById(R.id.liantian_rec_one_time);
            info1 = itemView.findViewById(R.id.liantian_rec_one_info);
            head1 = itemView.findViewById(R.id.liantian_rec_one_head);
            tupian2 = itemView.findViewById(R.id.tupian2);
            l_voice = itemView.findViewById(R.id.consult_page_voice_l);
            l_start = itemView.findViewById(R.id.consult_page_start_l);

        }
    }

    //个人发送
    class YouViewHolder extends RecyclerView.ViewHolder {
        //语音时长 和 发送的文字
        TextView time2,message2;
        //头像
        RoundedImageView  head2;
        //播放语音的按钮
        ImageView startvocie2;
        public YouViewHolder(@NonNull View itemView) {
            super(itemView);
            //语音时长
            time2 = itemView.findViewById(R.id.voice_time);
            //发送的文字
            message2 = itemView.findViewById(R.id.voice_message);
            //头像
            head2 = itemView.findViewById(R.id.voice_head);
            //播放语音的按钮
            startvocie2 = itemView.findViewById(R.id.voice_startvoice);
        }
    }
}
