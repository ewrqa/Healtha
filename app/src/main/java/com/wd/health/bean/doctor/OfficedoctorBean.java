package com.wd.health.bean.doctor;

import java.util.List;

/**
 * <p>项目名称:Dimensions of health</p>
 * <p>包名:com.wd.health.dimensionsofhealth.a.model.bean</p>
 * <p>简述:医生的bean</p>
 *
 * @author 张凯涛
 * @date 2022/4/25
 */
public class OfficedoctorBean {

    /**
     * result : [{"badNum":1,"doctorId":474,"doctorName":"琪琪","imagePic":"http://mobile.bwstudent.com/images/health/doctor/system_image_pic/system_image6.jpg","inauguralHospital":"清华大学附属医院","jobTitle":"副主任医师","praise":"0.00%","praiseNum":0,"serverNum":5,"servicePrice":500},{"badNum":0,"doctorId":471,"doctorName":"张张张","imagePic":"http://mobile.bwstudent.com/images/health/doctor/system_image_pic/system_image3.jpg","inauguralHospital":"纽约第一人民医院","jobTitle":"主任医师","praise":"0.00%","praiseNum":0,"serverNum":2,"servicePrice":500},{"badNum":0,"doctorId":464,"doctorName":"焦赛虎","imagePic":"http://mobile.bwstudent.com/images/health/doctor/system_image_pic/system_image3.jpg","inauguralHospital":"清华大学附属医院","jobTitle":"主任医师","praise":"50.00%","praiseNum":2,"serverNum":26,"servicePrice":500},{"badNum":0,"doctorId":465,"doctorName":"张三","imagePic":"http://mobile.bwstudent.com/images/health/doctor/system_image_pic/system_image4.jpg","inauguralHospital":"清华大学附属医院","jobTitle":"主治医师","praise":"0.00%","praiseNum":0,"serverNum":2,"servicePrice":500},{"badNum":0,"doctorId":466,"doctorName":"没有昵称","imagePic":"http://mobile.bwstudent.com/images/health/doctor/system_image_pic/system_image5.jpg","inauguralHospital":"清华大学附属医院","jobTitle":"主治医师","praise":"0.00%","praiseNum":0,"serverNum":2,"servicePrice":500}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * badNum : 1
     * doctorId : 474
     * doctorName : 琪琪
     * imagePic : http://mobile.bwstudent.com/images/health/doctor/system_image_pic/system_image6.jpg
     * inauguralHospital : 清华大学附属医院
     * jobTitle : 副主任医师
     * praise : 0.00%
     * praiseNum : 0
     * serverNum : 5
     * servicePrice : 500
     */

    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private int badNum;
        private int doctorId;
        private String doctorName;
        private String imagePic;
        private String inauguralHospital;
        private String jobTitle;
        private String praise;
        private int praiseNum;
        private int serverNum;
        private int servicePrice;

        public int getBadNum() {
            return badNum;
        }

        public void setBadNum(int badNum) {
            this.badNum = badNum;
        }

        public int getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(int doctorId) {
            this.doctorId = doctorId;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public String getImagePic() {
            return imagePic;
        }

        public void setImagePic(String imagePic) {
            this.imagePic = imagePic;
        }

        public String getInauguralHospital() {
            return inauguralHospital;
        }

        public void setInauguralHospital(String inauguralHospital) {
            this.inauguralHospital = inauguralHospital;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public String getPraise() {
            return praise;
        }

        public void setPraise(String praise) {
            this.praise = praise;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getServerNum() {
            return serverNum;
        }

        public void setServerNum(int serverNum) {
            this.serverNum = serverNum;
        }

        public int getServicePrice() {
            return servicePrice;
        }

        public void setServicePrice(int servicePrice) {
            this.servicePrice = servicePrice;
        }
    }
}
