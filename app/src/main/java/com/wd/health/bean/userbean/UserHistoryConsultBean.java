package com.wd.health.bean.userbean;

import java.util.List;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.bean.userbean</p>
 * <p>简述:用户历史咨询</p>
 *
 * @author 张凯涛
 * @date 2022/7/19
 */
public class UserHistoryConsultBean {
    /**
     * result : [{"departmentId":0,"doctorId":34,"doctorName":"czy","evaluateStatus":1,"imagePic":"http://mobile.bwstudent.com/images/health/doctor/system_image_pic/system_image3.jpg","inquiryTime":1658129333000,"jiGuangPwd":"Th9DYT/yb6IjpDJ5t0F8cHoykU23JCZCe7rv1TgsAY3w9/sLU0IpLqncMwfkbR0hC90U1+K9FQJt92Mxu98xUtPawIbS3LbPWyDCUe8tGapKeac9d0nMsMJYwfRpr1331AqLZZJimyU7orwylSiR9kWh4xbMDOYjYHClR2KjSe0=","jobTitle":"主治医师","recordId":2415,"userName":"KVDNq83177287241"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * departmentId : 0
     * doctorId : 34
     * doctorName : czy
     * evaluateStatus : 1
     * imagePic : http://mobile.bwstudent.com/images/health/doctor/system_image_pic/system_image3.jpg
     * inquiryTime : 1658129333000
     * jiGuangPwd : Th9DYT/yb6IjpDJ5t0F8cHoykU23JCZCe7rv1TgsAY3w9/sLU0IpLqncMwfkbR0hC90U1+K9FQJt92Mxu98xUtPawIbS3LbPWyDCUe8tGapKeac9d0nMsMJYwfRpr1331AqLZZJimyU7orwylSiR9kWh4xbMDOYjYHClR2KjSe0=
     * jobTitle : 主治医师
     * recordId : 2415
     * userName : KVDNq83177287241
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
        private int departmentId;
        private int doctorId;
        private String doctorName;
        private int evaluateStatus;
        private String imagePic;
        private long inquiryTime;
        private String jiGuangPwd;
        private String jobTitle;
        private int recordId;
        private String userName;

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
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

        public int getEvaluateStatus() {
            return evaluateStatus;
        }

        public void setEvaluateStatus(int evaluateStatus) {
            this.evaluateStatus = evaluateStatus;
        }

        public String getImagePic() {
            return imagePic;
        }

        public void setImagePic(String imagePic) {
            this.imagePic = imagePic;
        }

        public long getInquiryTime() {
            return inquiryTime;
        }

        public void setInquiryTime(long inquiryTime) {
            this.inquiryTime = inquiryTime;
        }

        public String getJiGuangPwd() {
            return jiGuangPwd;
        }

        public void setJiGuangPwd(String jiGuangPwd) {
            this.jiGuangPwd = jiGuangPwd;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public int getRecordId() {
            return recordId;
        }

        public void setRecordId(int recordId) {
            this.recordId = recordId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
