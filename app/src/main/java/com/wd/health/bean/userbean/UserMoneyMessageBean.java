package com.wd.health.bean.userbean;

import java.util.List;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.bean.userbean</p>
 * <p>简述:用户消费记录</p>
 *
 * @author 张凯涛
 * @date 2022/7/22
 */
public class UserMoneyMessageBean {
    /**
     * result : [{"changeNum":-500,"createTime":1658233655000,"direction":2,"type":9},{"changeNum":-500,"createTime":1658230601000,"direction":2,"type":9},{"changeNum":-500,"createTime":1658129333000,"direction":2,"type":9},{"changeNum":1000,"createTime":1658129322000,"direction":1,"type":15},{"changeNum":1000,"createTime":1658129322000,"direction":1,"type":15},{"changeNum":1000,"createTime":1658129321000,"direction":1,"type":15},{"changeNum":1000,"createTime":1658129321000,"direction":1,"type":15},{"changeNum":1000,"createTime":1658129321000,"direction":1,"type":15},{"changeNum":1000,"createTime":1658129320000,"direction":1,"type":15},{"changeNum":1000,"createTime":1658129320000,"direction":1,"type":15},{"changeNum":1000,"createTime":1658129320000,"direction":1,"type":15},{"changeNum":1000,"createTime":1658129320000,"direction":1,"type":15},{"changeNum":1000,"createTime":1658129320000,"direction":1,"type":15},{"changeNum":1000,"createTime":1658129319000,"direction":1,"type":15},{"changeNum":1000,"createTime":1658129319000,"direction":1,"type":15},{"changeNum":1000,"createTime":1658129318000,"direction":1,"type":15},{"changeNum":1000,"createTime":1658129318000,"direction":1,"type":15},{"changeNum":1000,"createTime":1658129317000,"direction":1,"type":15},{"changeNum":1000,"createTime":1658129316000,"direction":1,"type":15},{"changeNum":1000,"createTime":1658129315000,"direction":1,"type":15}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * changeNum : -500
     * createTime : 1658233655000
     * direction : 2
     * type : 9
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
        private int changeNum;
        private long createTime;
        private int direction;
        private int type;

        public int getChangeNum() {
            return changeNum;
        }

        public void setChangeNum(int changeNum) {
            this.changeNum = changeNum;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
