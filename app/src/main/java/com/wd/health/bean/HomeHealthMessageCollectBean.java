package com.wd.health.bean;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.bean</p>
 * <p>简述:用户数收藏健康资讯的bean</p>
 *
 * @author 张凯涛
 * @date 2022/7/21
 */
public class HomeHealthMessageCollectBean {
    /**
     * message : 资讯收藏成功
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
