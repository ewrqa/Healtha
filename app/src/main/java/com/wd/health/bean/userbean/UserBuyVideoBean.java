package com.wd.health.bean.userbean;

import java.util.List;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.bean.userbean</p>
 * <p>简述:用户购买视频的bean</p>
 *
 * @author 张凯涛
 * @date 2022/7/25
 */
public class UserBuyVideoBean {
    /**
     * result : [{"createTime":1658737798000,"duration":95,"id":200,"originalUrl":"http://172.17.8.100/video/health/original/ek/ek1.mp4","title":"小儿贫血的检查方法有哪些","videoId":1},{"createTime":1658737801000,"duration":98,"id":201,"originalUrl":"http://172.17.8.100/video/health/original/ek/ek2.mp4","title":"儿童错颌畸形如何预防","videoId":2},{"createTime":1658737804000,"duration":165,"id":202,"originalUrl":"http://172.17.8.100/video/health/original/ek/ek3.mp4","title":"小儿长期不爱吃饭是怎么回事","videoId":3}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * createTime : 1658737798000
     * duration : 95
     * id : 200
     * originalUrl : http://172.17.8.100/video/health/original/ek/ek1.mp4
     * title : 小儿贫血的检查方法有哪些
     * videoId : 1
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
        private long createTime;
        private int duration;
        private int id;
        private String originalUrl;
        private String title;
        private int videoId;

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
    }
}
