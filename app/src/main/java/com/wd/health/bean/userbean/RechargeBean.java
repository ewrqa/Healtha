package com.wd.health.bean.userbean;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.bean.userbean</p>
 * <p>简述:用户充值bean</p>
 *
 * @author 张凯涛
 * @date 2022/7/28
 */
// todo  充值bean
public class RechargeBean {

    /**
     * result
     */
    private String result;
    /**
     * message
     */
    private String message;
    /**
     * status
     */
    private String status;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
