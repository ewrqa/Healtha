package com.wd.health.bean.circleoffriends;

import java.util.List;

/**
 * <p>项目名称:Dimensions of health</p>
 * <p>包名:com.wd.health.dimensionsofhealth.a.model.bean.circleofFragment</p>
 * <p>简述:病友群啊的详情数据</p>
 *
 * @author 张凯涛
 * @date 2022/4/29
 */
public class CircleofFragmentMessageBean {
    /**
     * result : [{"amount":10,"collectionNum":0,"commentNum":3,"detail":"????","releaseTime":1649865600000,"sickCircleId":21825,"title":"?????"},{"amount":10,"collectionNum":0,"commentNum":6,"detail":"????","releaseTime":1649865600000,"sickCircleId":21824,"title":"?????"},{"amount":10,"collectionNum":1,"commentNum":4,"detail":"????","releaseTime":1649865600000,"sickCircleId":21823,"title":"?????"},{"amount":0,"collectionNum":0,"commentNum":1,"detail":"????","releaseTime":1649779200000,"sickCircleId":21819,"title":"??"},{"amount":0,"collectionNum":0,"commentNum":1,"detail":"????","releaseTime":1649779200000,"sickCircleId":21818,"title":"??"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * amount : 10
     * collectionNum : 0
     * commentNum : 3
     * detail : ????
     * releaseTime : 1649865600000
     * sickCircleId : 21825
     * title : ?????
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
