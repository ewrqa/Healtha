package com.wd.health.bean.userbean;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.bean.userbean</p>
 * <p>简述:用户档案上传图片</p>
 *
 * @author 张凯涛
 * @date 2022/7/20
 */
public class UserMessageImageBean {

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
