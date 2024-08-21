package com.wd.health.utils;


import com.wd.health.activity.ForgetpasswordActivity;
import com.wd.health.bean.BarrageBean;
import com.wd.health.bean.DoctorConsultBean;
import com.wd.health.bean.HealthInFormtionMessageBean;
import com.wd.health.bean.HealthInformationBean;
import com.wd.health.bean.HomeHealthMessageBean;
import com.wd.health.bean.HomeHealthMessageClrearCollectBean;
import com.wd.health.bean.HomeHealthMessageCollectBean;
import com.wd.health.bean.HomePageXbannerBean;
import com.wd.health.bean.YaoPinDuiYingBean;
import com.wd.health.bean.YaoPinListBean;
import com.wd.health.bean.circleoffriends.CircleCommentBean;
import com.wd.health.bean.circleoffriends.CircleCommentInputBean;
import com.wd.health.bean.circleoffriends.CircleMessageBean;
import com.wd.health.bean.circleoffriends.CircleofFragmentMessageBean;
import com.wd.health.bean.circleoffriends.CollectCircleBean;
import com.wd.health.bean.circleoffriends.EndInquiryBean;
import com.wd.health.bean.circleoffriends.FabubingyouquanBean;
import com.wd.health.bean.circleoffriends.OfficeDiseaseBean;
import com.wd.health.bean.circleoffriends.UploadImageBean;
import com.wd.health.bean.doctor.OfficeBean;
import com.wd.health.bean.doctor.OfficedoctorBean;
import com.wd.health.bean.loginandregister.EmailBean;
import com.wd.health.bean.loginandregister.LoginBean;
import com.wd.health.bean.loginandregister.RegisterBean;
import com.wd.health.bean.userbean.RechargeBean;
import com.wd.health.bean.userbean.SingInBean;
import com.wd.health.bean.userbean.UserBuyVideoBean;
import com.wd.health.bean.userbean.UserCollcetMessageBean;
import com.wd.health.bean.userbean.UserCollectCircleBean;
import com.wd.health.bean.userbean.UserCollectLost;
import com.wd.health.bean.userbean.UserCollectVideoBean;
import com.wd.health.bean.userbean.UserDoctorConsultBean;
import com.wd.health.bean.userbean.UserFinshConsultBean;
import com.wd.health.bean.userbean.UserHistoryConsultBean;
import com.wd.health.bean.userbean.UserMessageAddBean;
import com.wd.health.bean.userbean.UserMessageBean;
import com.wd.health.bean.userbean.UserMessageDelete;
import com.wd.health.bean.userbean.UserMessageImageBean;
import com.wd.health.bean.userbean.UserMoneyBean;
import com.wd.health.bean.userbean.UserMoneyMessageBean;
import com.wd.health.bean.video.BuyVideoBean;
import com.wd.health.bean.video.CollectVideoBean;
import com.wd.health.bean.video.DeleteCollectVideoBean;
import com.wd.health.bean.video.VideoBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


/**
 * <p>项目名称:Dimensions of health</p>
 * <p>包名:com.wd.health.dimensionsofhealth.a.model</p>
 * <p>简述:接口</p>
 *
 * @author 张凯涛
 * @date 2022/4/19
 */
public interface ApiServace {

    //邮箱注册的接口
    @POST("health/user/v1/sendOutEmailCode")
    Observable<EmailBean>getEmail(@Query("email")String email);
//    //注册接口
    @POST("health/user/v1/register")
    Observable<RegisterBean>getRegister(@QueryMap Map<String,Object>parmas);
    //登录接口
    @POST("health/user/v1/login")
    Observable<LoginBean>getLogin(@QueryMap Map<String,Object>parmas);
    //重新设置密码的接口
    @PUT("health/user/v1/resetUserPwd")
    Observable<ForgetpasswordActivity>getRest(@Query("email")String email,
                                                        @Query("pwd1")String pwd1,
                                                        @Query("pwd2")String pwd2);
    //用户签到
    @POST("health/user/verify/v1/addSign")
    Observable<SingInBean>getSingin();

    //用户查询个人档案
    @GET("health/user/verify/v1/findUserArchives")
    Observable<UserMessageBean>getUserMessage();

    //删除用户的档案
    @DELETE("health/user/verify/v1/deleteUserArchives")
    Observable<UserMessageDelete>getUserMessageDelete(@Query("archivesId")int archivesId);

    //用户添加档案
    @POST("health/user/verify/v1/addUserArchives")
    Observable<UserMessageAddBean>getUserMessageAdd(@Body Map<String,Object>map);

    //用户上传图片
    @Multipart
    @POST("health/user/verify/v1/uploadArchivesPicture")
    Observable<UserMessageImageBean>getImage(@Part List<MultipartBody.Part>list);


    //根据不同的状态播方视频
    @GET("health/user/video/v1/findVideoVoList")
    Observable<VideoBean>getVideo(@QueryMap Map<String,Object>parmas);


    //购买的接口
    @POST("health/user/video/verify/v1/videoBuy")
    Observable<BuyVideoBean>getBuy(@Query("videoId")int videoId,
                                   @Query("price")int price);
    //收藏视频
    @POST("health/user/video/verify/v1/addUserVideoCollection")
    Observable<CollectVideoBean>getCollectVideoBean(@Query("videoId")int videoId);

        //用户取消收藏视频
        @DELETE("health/user/verify/v1/cancelVideoCollection")
        Observable<DeleteCollectVideoBean>getDleteVideoBean(@Query("videoId")int videoId);

//    //弹幕展示的接口
//    @GET("health/user/video/v1/findVideoCommentList")
//    Observable<BarrageBean>getBrrage(@QueryMap Map<String,Object>parmas);
//
//    //收藏接口
//    @POST("health/user/video/verify/v1/addUserVideoCollection")
//    Observable<CollectBean>getCollect(@QueryMap Map<String,Object>parmas);
//    //取消收藏接口
//    @DELETE("health/user/verify/v1/cancelVideoCollection")
//    Observable<QxiaoshoucangBean>getQuxiao(@QueryMap Map<String,Object>parmas);
//    //发布弹幕的接口
//    @POST("health/user/video/verify/v1/addVideoComment")
//    Observable<IssuedbarrageBean>getIssued(@QueryMap Map<String,Object>parmag);
//
//
    //科室对应医生列表
    @GET("health/user/inquiry/v1/findDoctorList")
    Observable<OfficedoctorBean>getOfficeDoctor(@QueryMap Map<String,Object>parmas);
//
//    //医生详情接口
//    @GET("health/user/inquiry/v1/findDoctorInfo")
//    Observable<DoctorMessageBean>getDocotrMessage(@QueryMap Map<String,Object>parmas);
//
//    //咨询医生的接口
//    @PUT("health/user/inquiry/verify/v1/consultDoctor")
//    Observable<DoctorConsultBean>getDoctorCounsult(@QueryMap Map<String,Object>parma);
//
    //用户查询当前咨询的医生
    @GET("health/user/inquiry/verify/v1/findCurrentInquiryRecord")
    Observable<UserDoctorConsultBean>getUserDoctorConsult();

    //用户历史问诊列表
    @GET("health/user/inquiry/verify/v1/findHistoryInquiryRecord")
    Observable<UserHistoryConsultBean>getUserHistoryConsultBean(@Query("page")int page,
                                                                @Query("count")int count);

    //用户结束问诊接口
    @PUT("health/user/inquiry/verify/v1/endInquiry")
    Observable<UserFinshConsultBean>getUserFinishConsult(@Query("recordId")int recordId);

//    //用户查看历史问诊
//    @GET("inquiry/verify/v1/findHistoryInquiryRecord")
//    Observable<>
//
//
//    @POST("health/user/inquiry/verify/v1/pushMessage")
//    Observable<ConsulePageBean>getConsulePage(@QueryMap Map<String,Object>parmas);
//
    //xbanner的接口
    @GET("health/share/v1/bannersShow")
    Observable<HomePageXbannerBean> getXbanner();

    // 医院科室的接口
    @GET("health/share/knowledgeBase/v1/findDepartment")
    Observable<OfficeBean>getOffice();

    @GET("health/share/knowledgeBase/v1/findDiseaseCategory")
    Observable<OfficeDiseaseBean>getOfficeDisease(@Query("departmentId")int departmentId);

    //获取健康资讯的接口
    @GET("health/share/information/v1/findInformationPlateList")
    Observable<HealthInformationBean>getHealthIn();
    //资讯列表的接口
    @GET("health/share/information/v1/findInformationList")
    Observable<HealthInFormtionMessageBean>getHealthInFormationMessage(@QueryMap Map<String,Object> parmas);

    //健康资讯列表的详情页
    @GET("health/share/information/v1/findInformation")
    Observable<HomeHealthMessageBean>getHomeMessageHTMl(@Query("infoId")int infoId);


    //用户收藏健康资讯
    @POST("health/user/verify/v1/addInfoCollection")
    Observable<HomeHealthMessageCollectBean>getShoucang(@Query("infoId")int infoId);
    //用户取消收藏
    @DELETE("health/user/verify/v1/cancelInfoCollection")
    Observable<HomeHealthMessageClrearCollectBean>getQuxiaoshoucang(@Query("infoId")int  infoId);


    //用户消费记录
    @GET("health/user/verify/v1/findUserConsumptionRecordList")
    Observable<UserMoneyMessageBean>getUserMoneyMessage(@Query("page")int page, @Query("count")int count);


    //用户金钱月
    @GET("health/user/verify/v1/findUserWallet")
    Observable<UserMoneyBean>getUserMoeny();

    //用户咨询收藏的接口
    @GET("health/user/verify/v1/findUserInfoCollectionList")
    Observable<UserCollcetMessageBean>getUserCollcetMessage(@Query("page")int page,
                                                            @Query("count")int count);

    //用户收藏视频
    @GET("health/user/verify/v1/findUserSickCollectionList")
    Observable<UserCollectVideoBean>getUserCollectVideo(@Query("page")int page,
                                                        @Query("count")int count);

    //用户数收藏列表
    @GET("health/user/verify/v1/findUserSickCollectionList")
    Observable<UserCollectLost>getUserCollectList(@Query("page")int page,
                                                  @Query("count")int count);

    @GET("health/user/verify/v1/findUserVideoBuyList")
    Observable<UserBuyVideoBean>getUserCollectBuyVideo(@Query("page")int page,
                                                   @Query("count")int count);

    //用户数收藏病友圈的数据
    @GET("health/user/sickCircle/verify/v1/findMySickCircleList")
    Observable<UserCollectCircleBean>getCollectCircle(@Query("page")int page,
                                                     @Query("count")int count);

//    //热门搜搜
//    @GET("health/share/v1/homePageSearch")
//    Observable<HomePageSousuoBean>getSousuo(@QueryMap Map<String,Object>parage);
//
    //病友圈的数据
    @GET("health/user/sickCircle/v1/findSickCircleList")
    Observable<CircleofFragmentMessageBean>getCircleMessage(@QueryMap Map<String,Object>parmas);

    //病友全的详情
    @GET("health/user/sickCircle/v1/findSickCircleInfo")
    Observable<CircleMessageBean>getCircleParticulars(@Query("sickCircleId")int sickCircleId);

    //用户收藏病友圈接口
    @POST("health/user/verify/v1/addUserSickCollection")
    Observable<CollectCircleBean>getCollectCircle(@Query("sickCircleId")int sickCircleId);

    //用户充值
    @POST("health/user/verify/v1/recharge")
    Observable<RechargeBean>getToUp(@Query("money")double money,
                                    @Query("payType")int payType);

    //用户病友圈的评论
    @GET("health/user/sickCircle/v1/findSickCircleCommentList")
    Observable<CircleCommentBean>getCircleComment(@Query("sickCircleId")int sickCircleId,
                                                  @Query("page")int page,
                                                  @Query("count")int count);



    @POST("health/user/sickCircle/verify/v1/publishComment")
    Observable<CircleCommentInputBean>getCircleInput(@Query("sickCircleId")int sickCircleId,
                                                     @Query("content")String content);

    //发布病友圈
    @POST("health/user/sickCircle/verify/v1/publishSickCircle")
    Observable<FabubingyouquanBean>getFabubingyouquan(@Body Map<String,Object>parmas);

    //点击上传病友圈图片
    @Multipart
    @POST("health/user/sickCircle/verify/v1/uploadSickCirclePicture")
    Observable<UploadImageBean>getUploadImage(@Query("sickCircleId")int sickCircleId,@Part List<MultipartBody.Part>list);

    //当前医生是否有人咨询
    @PUT("health/user/inquiry/verify/v1/consultDoctor")
    Observable<DoctorConsultBean>getDoctorCounsult(@Query("doctorId")int doctorId);

    //用户结束问诊
    @PUT("health/doctor/inquiry/verify/v1/endInquiry")
    Observable<EndInquiryBean>getDelete(@Query("recordId")int recordId);

    //显示弹幕
    @GET("health/user/video/v1/findVideoCommentList")
    Observable<BarrageBean>getBrrage(@Query("videoId")int videoId);


    //药品科目分类列表查询
    @GET("health/share/knowledgeBase/v1/findDrugsCategoryList")
    Observable<YaoPinListBean> findDrugsCategoryList();

    //根据药品类目查询常见药品
    @GET("health/share/knowledgeBase/v1/findDrugsKnowledgeList")
    Observable<YaoPinDuiYingBean> findDrugsKnowledgeList(@Query("drugsCategoryId") int drugsCategoryId, @Query("page") int page, @Query("count") int count);


   
}
