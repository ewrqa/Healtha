package com.wd.health.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.wd.health.R;
import com.wd.health.adapter.ImageAdapter;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;
import com.wd.health.bean.userbean.UserMessageImageBean;
import com.wd.health.presenter.user.UserPresenter;
import com.wd.health.utils.HttPUtils;
import com.wd.health.utils.imageutils.GlideEngine;
import com.wd.health.utils.imageutils.Tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import id.zelory.compressor.Compressor;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ImageActivity extends BaseActivity<UserPresenter>{

    //创建所有图片的集合
    private List<String>allSelectList;
    private  int i=0;
    //预览图片的集合
    private  List<LocalMedia> selectList  = new ArrayList<>();
    private RecyclerView recyclerView_duoimg;
    private File file;
    private ImageAdapter imageAdapter;
    private Button button;
    private List<MultipartBody.Part> list;

    @Override
    protected UserPresenter initPresenter() {
        return new UserPresenter();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // 结果回调 把选中的 图片存放的list集合
            selectList = PictureSelector.obtainMultipleResult(data);
            // 调用 转化为path
            showSelectPic(selectList);
        }
    }
    @Override
    protected void initView() {
        recyclerView_duoimg = findViewById(R.id.recycler);
        button = findViewById(R.id.upload);

    }
    @Override
    protected void initDate() {
        //判断是否为空
            if(null==allSelectList){
               allSelectList= new ArrayList();
            }
            //点击事件走接口
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
            //设置适配器
        imageAdapter = new ImageAdapter(ImageActivity.this, 6);
        // 设置 选中的图片集合 展示到适配器
        imageAdapter.setMediaDtoList(allSelectList);
        //设置适配器
        recyclerView_duoimg.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView_duoimg.setAdapter(imageAdapter);
        //调佣适配器
        initAdapter();
    }
    //适配器
    public  void  initAdapter(){

        imageAdapter.setListener(new ImageAdapter.CallbackListener() {
            @Override
            public void add() {
                // todo 可添加的最大张数= 6  - 当前已选的张数
                int size = 6 - allSelectList.size();
                // 调用 多图的工具类 选取图片
                Tools.galleryPictures(ImageActivity.this, size);

            }
            @Override
            //删除
            public void delete(int position) {
                // 删除 集合中对应下标的数据
                allSelectList.remove(position);
                // 在把 集合重新放入适配器
                imageAdapter.setMediaDtoList(allSelectList);//再set所有集合
            }
            @Override
            public void item(int position, List<String> mList) {
                selectList.clear();
                for (i = 0; i < allSelectList.size(); i++) {
                    LocalMedia localMedia = new LocalMedia();
                    localMedia.setPath(allSelectList.get(i));
                    selectList.add(localMedia);
                }
                //①、图片选择器自带预览
                PictureSelector.create(ImageActivity.this)
                        .themeStyle(R.style.picture_default_style)
                        .isNotPreviewDownload(true)//是否显示保存弹框
                        .imageEngine(GlideEngine.createGlideEngine()) // 选择器展示不出图片则添加
                        .openExternalPreview(position, selectList);
                //②:自定义布局预览
                //Tools.startPhotoViewActivity(MainActivity.this, categoryLists, position);
            }
        });
    }
    public void publish_pictures() {
        list = new ArrayList<>();
        for (int i = 0; i < allSelectList.size(); i++) {
            Log.i("path_file", allSelectList.get(i));
            file = new File(allSelectList.get(i));
            try {
                file = new Compressor(this).compressToFile(file);
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part picture = MultipartBody.Part.createFormData("picture", file.getName(), requestBody);
                list.add(picture);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        HttPUtils.getHttPUtils(ImageActivity.this)
                .getApiServace()
                .getImage(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserMessageImageBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UserMessageImageBean userMessageImageBean) {
                        Toast.makeText(ImageActivity.this, userMessageImageBean.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void showSelectPic(List<LocalMedia> result) {
        for (int i = 0; i < result.size(); i++) {
            String path;
            //判断是否10.0以上
            if (Build.VERSION.SDK_INT >= 29) {
                path = result.get(i).getAndroidQToPath();
            } else {
                path = result.get(i).getPath();
            }
            allSelectList.add(path);
            Log.e("image_path", "图片链接: " + path);
        }
        imageAdapter.setMediaDtoList(allSelectList);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_image;
    }
    @Override
    public void onSucceed(Object object) {
        if(object instanceof UserMessageImageBean){
            UserMessageImageBean   userMessageImageBean=(UserMessageImageBean)object;
            Toast.makeText(ImageActivity.this, userMessageImageBean.getMessage(), Toast.LENGTH_SHORT).show();
            if(userMessageImageBean.getStatus().equals("0000")){
                Toast.makeText(ImageActivity.this, userMessageImageBean.getMessage(), Toast.LENGTH_SHORT).show();
                publish_pictures();
            }
              }
    }
    @Override
    public void onError(String message) {

    }
}