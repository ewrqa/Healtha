package com.wd.health.bean.video;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.bean.video</p>
 * <p>简述:用户购买视频的bean</p>
 *
 * @author 张凯涛
 * @date 2022/7/26
 */
public class BuyVideoBean {
    /**
     * message : 购买成功
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
