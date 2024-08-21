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
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.wd.health.R;
import com.wd.health.adapter.ImageAdapter;
import com.wd.health.base.BaseActivity;
import com.wd.health.base.BasePresenter;
import com.wd.health.bean.circleoffriends.FabubingyouquanBean;
import com.wd.health.bean.circleoffriends.OfficeDiseaseBean;
import com.wd.health.bean.circleoffriends.UploadImageBean;
import com.wd.health.bean.doctor.OfficeBean;
import com.wd.health.bean.userbean.UserMessageImageBean;
import com.wd.health.fragment.CircleofFriendsFragment;
import com.wd.health.fragment.PatientscircleFragment;
import com.wd.health.presenter.circleopresenter.IssueCirclePresneter;
import com.wd.health.utils.HttPUtils;
import com.wd.health.utils.imageutils.GlideEngine;
import com.wd.health.utils.imageutils.Tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import id.zelory.compressor.Compressor;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.health.base</p>
 * <p>简述:病友圈发布和上传病友圈图片</p>
 * @author 张凯涛
 * @date 2022/7/15
 */
public class IssueCircleActivity extends BaseActivity<IssueCirclePresneter>{

    private Spinner spinneroffice;
    private Spinner bingzheng;
    private ArrayList<String> list;
    private Button button;
    private List<OfficeBean.ResultBean> result;
    private EditText title;
    private EditText bingzhengxiangqing;
    private EditText yiyuan;
    private ImageView startime;
    private ImageView stoptime;
    private EditText zhiliaoguocheng;
    private RecyclerView recyclerView;
    private int departmentId;
    String str1;
    String str2;
    private TextView time1;
    private TextView time2;
    private String departmentName;
    private String name;
    private List<String> allSelectList;
    private ImageAdapter imageAdapter;
    private  List<LocalMedia> selectList  = new ArrayList<>();
    private  int i=0;
    private File file;
    //查看图片的集合
    private List<MultipartBody.Part> list2;
    private Switch huasong;
    private LinearLayout aaaa;
    private RadioGroup radioGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private int amount=0;

    @Override
    protected IssueCirclePresneter initPresenter() {
        return new IssueCirclePresneter();
    }

    @Override
    protected void initDate() {

        //判断是否为空
        if(null==allSelectList){
            allSelectList= new ArrayList();
        }

        //设置上传图片的适配器
        //设置适配器
        imageAdapter = new ImageAdapter(IssueCircleActivity.this, 6);
        // 设置 选中的图片集合 展示到适配器
        imageAdapter.setMediaDtoList(allSelectList);
        //设置适配器
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(imageAdapter);

        initAdapter();


//        //走请求科室的接口
        getPresenter().getOffice(IssueCircleActivity.this);
        //设置时间的点击事件
        startime.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        //设置日期显示的格式
                        str1 = year+"-"+month+"-"+dayOfMonth;
                        //进行赋值
                        time1.setText("开始时间 "+str1);
                    }
                };
                //上下文   时间的格式
                new DatePickerDialog(IssueCircleActivity.this,3,onDateSetListener,2022,5,1).show();
            }
        });
        stoptime.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {

                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        //设置日期显示的格式
                        str2 = year+"-"+month+"-"+dayOfMonth;
                        //进行赋值
                        time2.setText("结束时间 "+str2);
                    }
                };
                //上下文   时间的格式
                new DatePickerDialog(IssueCircleActivity.this,3,onDateSetListener,2022,5,1).show();
            }
        });
        //发布病友圈的点击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().getIssueCircle(IssueCircleActivity.this,
                        title.getText().toString(),
                        departmentId,
                        departmentName,
                        name,
                        yiyuan.getText().toString(),
                        str1,
                        str2,
                        zhiliaoguocheng.getText().toString()
                        ,amount
                );
            }
        });
        //设置滑动点击事件
      huasong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if(isChecked){
                  aaaa.setVisibility(View.VISIBLE);
                  //设置点击事件
                  radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                      @Override
                      public void onCheckedChanged(RadioGroup group, int checkedId) {
                          switch (checkedId){
                              case R.id.rb1:

                                  amount=10;
                                  Toast.makeText(IssueCircleActivity.this, amount+"", Toast.LENGTH_SHORT).show();

                                  break;
                              case R.id.rb2:

                                  amount=20;
                                  Toast.makeText(IssueCircleActivity.this, amount+"", Toast.LENGTH_SHORT).show();

                                  break;
                              case R.id.rb3:

                                  amount=30;
                                  Toast.makeText(IssueCircleActivity.this, amount+"", Toast.LENGTH_SHORT).show();

                                  break;
                          }
                      }
                  });
              }else{
                  aaaa.setVisibility(View.GONE);
              }
          }
      });
    }
    @Override
    protected void initView() {
        //获取选择科室的下拉框
        spinneroffice = findViewById(R.id.issue_office);
        //获取病症详情下拉框
        bingzheng = findViewById(R.id.issue_bingzheng);
        button = findViewById(R.id.issue_fasong);
        //获取标题
        title = findViewById(R.id.issue_title);
        bingzhengxiangqing = findViewById(R.id.issue_bingzhengmessage);
        yiyuan = findViewById(R.id.issue_yiyuan);
        startime = findViewById(R.id.issue_startime);
        stoptime = findViewById(R.id.issue_stoptime);
        zhiliaoguocheng = findViewById(R.id.issue_zhiliaoguocheng);
        recyclerView = findViewById(R.id.issue_uploadingimage);
        time1 = findViewById(R.id.issue_time1);
        time2 = findViewById(R.id.issue_time2);
        //获取滑动组件的id
        huasong = findViewById(R.id.issue_slide);

        aaaa = findViewById(R.id.aaaaa);
        //后去单选框按钮的点击事件
        radioGroup = findViewById(R.id.issue_Rg);
        //获取单选框
        rb1 = findViewById(R.id.issue_rb1);
        rb2 = findViewById(R.id.issue_rb2);
        rb3 = findViewById(R.id.issue_rb3);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_issue_circle;
    }

    //上传适配器的点击事件
    //设置适配器

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
    public void publish_pictures(Integer id) {
        list2 = new ArrayList<>();
        for (int i = 0; i < allSelectList.size(); i++) {
            Log.i("path_file", allSelectList.get(i));
            file = new File(allSelectList.get(i));
            try {
                file = new Compressor(this).compressToFile(file);
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part picture = MultipartBody.Part.createFormData("picture", file.getName(), requestBody);
                list2.add(picture);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //网络请求
        HttPUtils.getHttPUtils(IssueCircleActivity.this)
                .getApiServace()
                .getUploadImage(id,list2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UploadImageBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UploadImageBean uploadImageBean) {
                        Toast.makeText(IssueCircleActivity.this, uploadImageBean.getMessage(), Toast.LENGTH_SHORT).show();
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
    public  void  initAdapter(){
        imageAdapter.setListener(new ImageAdapter.CallbackListener() {
            @Override
            public void add() {
                // todo 可添加的最大张数= 6  - 当前已选的张数
                int size = 6 - allSelectList.size();
                // 调用 多图的工具类 选取图片
                Tools.galleryPictures(IssueCircleActivity.this, size);
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
                PictureSelector.create(IssueCircleActivity.this)
                        .themeStyle(R.style.picture_default_style)
                        .isNotPreviewDownload(true)//是否显示保存弹框
                        .imageEngine(GlideEngine.createGlideEngine()) // 选择器展示不出图片则添加
                        .openExternalPreview(position, selectList);
            }
        });
    }
    @Override
    public void onSucceed(Object object) {

        list = new ArrayList<>();
        //医院科室的数据
        if(object instanceof OfficeBean){
            OfficeBean     officeDiseaseBean=(OfficeBean)object;
            result = officeDiseaseBean.getResult();
            Toast.makeText(IssueCircleActivity.this,officeDiseaseBean.getMessage(), Toast.LENGTH_SHORT).show();
            if(officeDiseaseBean.getMessage().equals("查询成功")){
                //设置下拉框
                for(int i = 0; i< result.size(); i++){
                    String name = result.get(i).getDepartmentName();
                     list.add(name);
                }
                ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(IssueCircleActivity.this, R.layout.item_dropdown,list);
                stringArrayAdapter.setDropDownViewResource(R.layout.item_spinner);
                //设置下拉框的数组适配器
                spinneroffice.setAdapter(stringArrayAdapter);
                //设置下拉框默认的显示第一项
                spinneroffice.setSelection(0);
                //给下拉框设置选择监听器，一旦用户选中某一项，就触发监听器的onItemSelected方法
                //设置点击事件
                spinneroffice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //走请求对应科室的病症的请求
                        departmentId = result.get(position).getId();

                        getPresenter().getOfficeDisease(IssueCircleActivity.this, departmentId);

                        //选中的科室名称
                        departmentName = result.get(position).getDepartmentName();

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        }
        //对应科室症状的数据请求
        if(object instanceof  OfficeDiseaseBean){
            ArrayList<String> list = new ArrayList<>();
            OfficeDiseaseBean       officeDiseaseBean= (OfficeDiseaseBean)object;
            List<OfficeDiseaseBean.ResultBean> result = officeDiseaseBean.getResult();
            Toast.makeText(IssueCircleActivity.this, "对应症状"+officeDiseaseBean.getMessage(), Toast.LENGTH_SHORT).show();
            if(officeDiseaseBean.getMessage().equals("查询成功")){
                for(int i = 0; i< result.size(); i++){
                    String name = result.get(i).getName();
                  list.add(name);
                }
                ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(IssueCircleActivity.this, R.layout.item_dropdown, list);
                stringArrayAdapter.setDropDownViewResource(R.layout.item_spinner);
                //设置下拉框的数组适配器
                bingzheng.setAdapter(stringArrayAdapter);
                //设置下拉框默认的显示第一项
                bingzheng.setSelection(0);

                bingzheng.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        name = result.get(position).getName();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        }
        //发布病友圈
        if(object instanceof FabubingyouquanBean){
            FabubingyouquanBean      fabubingyouquanBean=(FabubingyouquanBean)object;
            Toast.makeText(IssueCircleActivity.this, fabubingyouquanBean.getMessage(), Toast.LENGTH_SHORT).show();
            if(fabubingyouquanBean.getStatus().equals("0000")){
                publish_pictures(fabubingyouquanBean.getResult());
                startActivity(new Intent(IssueCircleActivity.this, TabberActivity.class));
            }
        }
}
    @Override
    public void onError(String message) {

    }
}