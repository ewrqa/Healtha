package com.wd.health.bean.userbean;

import java.util.List;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.bean.userbean</p>
 * <p>简述:用户收藏朋友圈bean类</p>
 *
 * @author 张凯涛
 * @date 2022/7/25
 */
public class UserCollectCircleBean {

    /**
     * result : [{"amount":0,"collectionNum":0,"commentNum":0,"detail":"111111111","releaseTime":1658678400000,"sickCircleId":50,"title":"11111"},{"amount":0,"collectionNum":2,"commentNum":5,"detail":"哈哈哈哈哈","releaseTime":1658419200000,"sickCircleId":48,"title":"咿呀咿呀哟"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * amount : 0
     * collectionNum : 0
     * commentNum : 0
     * detail : 111111111
     * releaseTime : 1658678400000
     * sickCircleId : 50
     * title : 11111
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
        private int amount;
        private int collectionNum;
        private int commentNum;
        private String detail;
        private long releaseTime;
        private int sickCircleId;
        private String title;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getCollectionNum() {
            return collectionNum;
        }

        public void setCollectionNum(int collectionNum) {
            this.collectionNum = collectionNum;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public int getSickCircleId() {
            return sickCircleId;
        }

        public void setSickCircleId(int sickCircleId) {
            this.sickCircleId = sickCircleId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
