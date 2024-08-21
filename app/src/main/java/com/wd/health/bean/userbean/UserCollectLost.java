package com.wd.health.bean.userbean;

import java.util.List;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.bean.userbean</p>
 * <p>简述:用户收藏的健康列表</p>
 *
 * @author 张凯涛
 * @date 2022/7/25
 */
public class UserCollectLost {
    /**
     * result : [{"collectionNum":1,"commentNum":5,"createTime":1658713402000,"disease":"高脂蛋白血症","id":55,"sickCircleId":48,"title":"咿呀咿呀哟"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * collectionNum : 1
     * commentNum : 5
     * createTime : 1658713402000
     * disease : 高脂蛋白血症
     * id : 55
     * sickCircleId : 48
     * title : 咿呀咿呀哟
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
        private int collectionNum;
        private int commentNum;
        private long createTime;
        private String disease;
        private int id;
        private int sickCircleId;
        private String title;

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

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getDisease() {
            return disease;
        }

        public void setDisease(String disease) {
            this.disease = disease;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
