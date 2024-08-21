package com.wd.health.bean.loginandregister;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.bean.loginandregister</p>
 * <p>简述:忘记密码的bean类</p>
 *
 * @author 张凯涛
 * @date 2022/7/17
 */
public class ForgetpasswordBean {
    /**
     * message : 密码重置成功
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
