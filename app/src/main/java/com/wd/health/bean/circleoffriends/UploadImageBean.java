package com.wd.health.bean.circleoffriends;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.bean.circleoffriends</p>
 * <p>简述:上传病友圈图片bean</p>
 *
 * @author 张凯涛
 * @date 2022/8/2
 */
public class UploadImageBean {
    /**
     * message : 上传成功
     * status : 0000
     */

    private String message;
    private String status;

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
