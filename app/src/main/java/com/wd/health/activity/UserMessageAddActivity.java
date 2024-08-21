package com.wd.health.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.wd.health.R;
import com.wd.health.adapter.ImageAdapter;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;
import com.wd.health.bean.userbean.UserMessageAddBean;
import com.wd.health.bean.userbean.UserMessageImageBean;
import com.wd.health.presenter.user.UserPresenter;
import com.wd.health.utils.HttPUtils;
import com.wd.health.utils.imageutils.GlideEngine;
import com.wd.health.utils.imageutils.Tools;

import java.io.File;
import java.io.IOException;
import java.sql.DatabaseMetaData;
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

public class UserMessageAddActivity extends BaseActivity<UserPresenter>{
    /**
     * <p>项目名称:Health</p>
     * <p>包名:com.wd.health.base</p>
     * <p>简述:用户添加自己档案页面</p>
     * @author 张凯涛
     * @date 2022/7/15
     */
    //开始和结束的时间
    private TextView starttime;
    private TextView stoptime;
    //设置时间
    String str1;
    String str2;
    //症状
    private EditText zhegnzhuang;
    //现病史
    private EditText xianbingshi;
    //过往病史
    private EditText jiwangbingshi;
    //经历
    private EditText jinli;
    //治疗医院
    private EditText yiyuan;
    private Button go;
    private ImageView imageView;
    //创建所有图片的集合
    private List<String> allSelectList;
    private  int i=0;
    //预览图片的集合
    private  List<LocalMedia> selectList  = new ArrayList<>();

    //上传图片的文件
    private File file;
    //适配器
    private ImageAdapter imageAdapter;
    private Button button;
    //查看图片的集合
    private List<MultipartBody.Part> list;
    private RecyclerView recyclerView;

    @Override
    protected UserPresenter initPresenter() {
        return new UserPresenter();
    }
    @SuppressLint("ResourceType")
    @Override
    protected void initDate() {
        //判断是否为空
        if(null==allSelectList){
            allSelectList= new ArrayList();
        }
        //设置适配器
        imageAdapter = new ImageAdapter(UserMessageAddActivity.this, 6);
        // 设置 选中的图片集合 展示到适配器
        imageAdapter.setMediaDtoList(allSelectList);
        //设置适配器
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(imageAdapter);
        //调佣适配器
        initAdapter();
        //启动日期
        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            month=month+1;
                            //设置日期显示的格式
                            str1 = year+"-"+month+"-"+dayOfMonth;
                            //进行赋值
                            starttime.setText("开始时间 "+str1);
                    }
                };
                //上下文   时间的格式
                new DatePickerDialog(UserMessageAddActivity.this,3,onDateSetListener,2022,5,1).show();
            }
        });
        //设置结束的时间
        stoptime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        //设置日期显示的格式
                        str2 = year+"-"+month+"-"+dayOfMonth;
                        stoptime.setText("结束时间 "+str2);
                    }
                };
                //设置弹框
                new DatePickerDialog(UserMessageAddActivity.this,3,onDateSetListener,2022,5,1).show();
            }
        });
        //保存的点击事件
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().getUserMessageAdd(UserMessageAddActivity.this
                        ,zhegnzhuang.getText().toString(),
                        xianbingshi.getText().toString(),
                        jiwangbingshi.getText().toString(),
                        yiyuan.getText().toString(),
                        jinli.getText().toString(),
                        str1,
                        str2
                );
            }
        });
    }
    //设置适配器
    public  void  initAdapter(){
        imageAdapter.setListener(new ImageAdapter.CallbackListener() {
            @Override
            public void add() {
                // todo 可添加的最大张数= 6  - 当前已选的张数
                int size = 6 - allSelectList.size();
                // 调用 多图的工具类 选取图片
                Tools.galleryPictures(UserMessageAddActivity.this, size);
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
                PictureSelector.create(UserMessageAddActivity.this)
                        .themeStyle(R.style.picture_default_style)
                        .isNotPreviewDownload(true)//是否显示保存弹框
                        .imageEngine(GlideEngine.createGlideEngine()) // 选择器展示不出图片则添加
                        .openExternalPreview(position, selectList);
            }
        });
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
        //网络请求
        HttPUtils.getHttPUtils(UserMessageAddActivity.this)
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
                        Toast.makeText(UserMessageAddActivity.this, userMessageImageBean.getMessage(), Toast.LENGTH_SHORT).show();
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
        }  imageAdapter.setMediaDtoList(allSelectList);

    }
    @Override
    protected void initView() {
        //开始的时间
        starttime = findViewById(R.id.usermessage_starttime);
        //结束的时间
        stoptime = findViewById(R.id.usermessage_stoptime);
        //症状
        zhegnzhuang = findViewById(R.id.usermessage_zhengzhuang);
        //先病史
        xianbingshi = findViewById(R.id.usermessage_xianbingshi);
        //既往病史
        jiwangbingshi = findViewById(R.id.usermessage_jiwangbingshi);
        //经历
        jinli = findViewById(R.id.usermessage_jingli);
        //医院
        yiyuan = findViewById(R.id.usermessage_yiyuan);
        //保存档案
        go = findViewById(R.id.usermessage_go);
        //点击上传图片
        imageView = findViewById(R.id.usermessage_image1);

        recyclerView = findViewById(R.id.recycler);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_user_message_add;
    }

    @Override
    public void onSucceed(Object object) {
        //添加成功数据
        if(object instanceof UserMessageAddBean){
            UserMessageAddBean      userMessageAddBean=(UserMessageAddBean)object;
            Toast.makeText(UserMessageAddActivity.this, userMessageAddBean.getMessage(),Toast.LENGTH_SHORT).show();
            if(userMessageAddBean.getStatus().equals("0000")){
                if(userMessageAddBean.getMessage().equals("档案添加成功")){
                    publish_pictures();
                    startActivity(new Intent(UserMessageAddActivity.this,UserMessageActivity.class));
                    finish();
                }
                Toast.makeText(UserMessageAddActivity.this, userMessageAddBean.getMessage(), Toast.LENGTH_SHORT).show();
                //添加成功之后跳转到个人档案
            }
        }
    }
    @Override
    public void onError(String message) {

    }
}