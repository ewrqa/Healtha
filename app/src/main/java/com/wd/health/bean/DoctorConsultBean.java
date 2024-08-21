package com.wd.health.bean;

/**
 * <p>项目名称:Dimensions of health</p>
 * <p>包名:com.wd.health.dimensionsofhealth.a.model.bean</p>
 * <p>简述:咨询医生的接口</p>
 *
 * @author 张凯涛
 * @date 2022/4/26
 */
public class DoctorConsultBean {
    /**
     * doctorUserName : e8hA4NsoCS8vO7wXdjRfXPceuf3GlGrsMwOoNH4FfbqVL2oAr1NaImm8MraGdFzaQ7vuPGQnX3LFH+0FYh9yUJ8UgZd6MAGY+MT/juT+jCvLfk3Pcb5lTl5o2EOg7R4ctOUARnbTnXyXtWBf56N3ypK8XlxFkd6uHDLzA5FkhxU=
     * message : 查询成功
     * status : 0000
     */
    private String doctorUserName;
    private String message;
    private String status;

    public String getDoctorUserName() {
        return doctorUserName;
    }

    public void setDoctorUserName(String doctorUserName) {
        this.doctorUserName = doctorUserName;
    }

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
}
