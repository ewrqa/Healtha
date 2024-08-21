package com.wd.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.utils.imageutils.Tools;

import java.util.List;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.adapter</p>
 * <p>简述:多条目的图片上传适配器</p>
 *
 * @author 张凯涛
 * @date 2022/7/20
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder > {
    private Context mContext;
    private List<String> mediaDtoList;
    private int picMax;

    public ImageAdapter(Context mContext, int picMax) {
        this.mContext = mContext;
        this.picMax = picMax;
    }
    public void setMediaDtoList(List<String> mediaDtoList) {
        this.mediaDtoList = mediaDtoList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ImageAdapter.ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      //连接布局
        return new ImageHolder(LayoutInflater.from(mContext).
                inflate(R.layout.item_gallery, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ImageHolder holder, int position) {
        //当前位置大于图片数量并且小于最大减1
        if (position >= mediaDtoList.size() && position <= (picMax - 1)) {
            //显示添加图片按钮、并隐藏删除按钮
            Tools.showGlide(mContext,holder.gallery,"");
            holder.delete.setVisibility(View.GONE);
        } else {
            //显示本地或网络图片，并显示删除按钮
            Tools.showGlide(mContext,holder.gallery,mediaDtoList.get(position));
            holder.delete.setVisibility(View.VISIBLE);
        }
        //按钮删除事件
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传入position删除第几张
                listener.delete(position);
            }
        });

        holder.gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加图片的点击事件
                if (position >= mediaDtoList.size() && position <= (picMax - 1)) {
                    listener.add();
                } else {
                    //点击查看图片事件，并将最新list传入actiuvity
                    listener.item(position, mediaDtoList);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        //进行判断
        if (mediaDtoList == null || mediaDtoList.size() == 0) {
            return 1;
        } else {
            return this.mediaDtoList.size() >= picMax ? picMax : this.mediaDtoList.size() + 1;
        }
    }
    //连接布局
    public class ImageHolder extends RecyclerView.ViewHolder {
        private ImageView gallery;
        private ImageView delete;
        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            gallery = itemView.findViewById(R.id.im_show_gallery);
            delete = itemView.findViewById(R.id.iv_del);

        }
    }

    //自定义接口回调
    private CallbackListener listener;

    public void setListener(CallbackListener listener) {
        this.listener = listener;
    }

    public interface CallbackListener {
        //图片添加事件
        void add();
        //删除第几张图片
        void delete(int position);
        //图片点击
        void item(int position, List<String> mList);
    }
}
