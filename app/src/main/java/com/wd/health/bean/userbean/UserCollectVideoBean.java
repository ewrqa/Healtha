package com.wd.health.bean.userbean;

import java.util.List;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.bean.userbean</p>
 * <p>简述:用户收藏的视频</p>
 *
 * @author 张凯涛
 * @date 2022/7/25
 */
public class UserCollectVideoBean {
    /**
     * result : [{"buyNum":25,"createTime":1658713331000,"duration":98,"id":261,"originalUrl":"http://172.17.8.100/video/health/original/ek/ek2.mp4","price":50,"shearUrl":"http://mobile.bwstudent.com/video/health/cut/ek/ek2.mp4","title":"儿童错颌畸形如何预防","videoId":2,"whetherBuy":2},{"buyNum":34,"createTime":1658713329000,"duration":95,"id":260,"originalUrl":"http://172.17.8.100/video/health/original/ek/ek1.mp4","price":100,"shearUrl":"http://mobile.bwstudent.com/video/health/cut/ek/ek1.mp4","title":"小儿贫血的检查方法有哪些","videoId":1,"whetherBuy":2}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * buyNum : 25
     * createTime : 1658713331000
     * duration : 98
     * id : 261
     * originalUrl : http://172.17.8.100/video/health/original/ek/ek2.mp4
     * price : 50
     * shearUrl : http://mobile.bwstudent.com/video/health/cut/ek/ek2.mp4
     * title : 儿童错颌畸形如何预防
     * videoId : 2
     * whetherBuy : 2
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
        private int buyNum;
        private long createTime;
        private int duration;
        private int id;
        private String originalUrl;
        private int price;
        private String shearUrl;
        private String title;
        private int videoId;
        private int whetherBuy;

        public int getBuyNum() {
            return buyNum;
        }

        public void setBuyNum(int buyNum) {
            this.buyNum = buyNum;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOriginalUrl() {
            return originalUrl;
        }

        public void setOriginalUrl(String originalUrl) {
            this.originalUrl = originalUrl;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getShearUrl() {
            return shearUrl;
        }

        public void setShearUrl(String shearUrl) {
            this.shearUrl = shearUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getVideoId() {
            return videoId;
        }

        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }

        public int getWhetherBuy() {
            return whetherBuy;
        }

        public void setWhetherBuy(int whetherBuy) {
            this.whetherBuy = whetherBuy;
        }
    }
}
