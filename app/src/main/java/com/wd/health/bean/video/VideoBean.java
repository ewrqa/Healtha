package com.wd.health.bean.video;

import java.util.List;

/**
 * <p>项目名称:Dimensions of health</p>
 * <p>包名:com.wd.health.dimensionsofhealth.a.model.bean</p>
 * <p>简述:</p>
 *
 * @author 张凯涛
 * @date 2022/4/20
 */
public class VideoBean {

    /**
     * result : [{"abstracts":"宝宝腹泻时家长要避免的几个误区","buyNum":160,"categoryId":1,"duration":55,"id":2,"originalUrl":"http://mobile.bwstudent.com/video/health/original/ek/ek2.mp4","price":500,"shearUrl":"http://mobile.bwstudent.com/video/health/cut/ek/ek2.mp4","title":"儿科医生雨滴","whetherBuy":2,"whetherCollection":2},{"abstracts":"影响长高的3种食物，快看看你中枪了没","buyNum":90,"categoryId":1,"duration":26,"id":1,"originalUrl":"http://mobile.bwstudent.com/video/health/original/ek/ek1.mp4","price":500,"shearUrl":"http://mobile.bwstudent.com/video/health/cut/ek/ek1.mp4","title":"南方健康","whetherBuy":2,"whetherCollection":2},{"abstracts":"宝宝一哭就抱，会被惯坏吗？","buyNum":57,"categoryId":1,"duration":53,"id":3,"originalUrl":"http://mobile.bwstudent.com/video/health/original/ek/ek3.mp4","price":500,"shearUrl":"http://mobile.bwstudent.com/video/health/cut/ek/ek3.mp4","title":"儿科医生雨滴","whetherBuy":2,"whetherCollection":2},{"abstracts":"宝宝入睡困难的一剂良药：如何养成良好的睡眠习惯！","buyNum":46,"categoryId":1,"duration":61,"id":4,"originalUrl":"http://mobile.bwstudent.com/video/health/original/ek/ek4.mp4","price":500,"shearUrl":"http://mobile.bwstudent.com/video/health/cut/ek/ek4.mp4","title":"儿科医生鱼小南","whetherBuy":2,"whetherCollection":2},{"abstracts":"如何正确冲奶粉，奶爸们快快收藏！","buyNum":32,"categoryId":1,"duration":15,"id":5,"originalUrl":"http://mobile.bwstudent.com/video/health/original/ek/ek5.mp4","price":500,"shearUrl":"http://mobile.bwstudent.com/video/health/cut/ek/ek5.mp4","title":"儿童育婴师教你如何育儿","whetherBuy":2,"whetherCollection":2}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * abstracts : 宝宝腹泻时家长要避免的几个误区
     * buyNum : 160
     * categoryId : 1
     * duration : 55
     * id : 2
     * originalUrl : http://mobile.bwstudent.com/video/health/original/ek/ek2.mp4
     * price : 500
     * shearUrl : http://mobile.bwstudent.com/video/health/cut/ek/ek2.mp4
     * title : 儿科医生雨滴
     * whetherBuy : 2
     * whetherCollection : 2
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
        private String abstracts;
        private int buyNum;
        private int categoryId;
        private int duration;
        private int id;
        private String originalUrl;
        private int price;
        private String shearUrl;
        private String title;
        private int whetherBuy;
        private int whetherCollection;

        public String getAbstracts() {
            return abstracts;
        }

        public void setAbstracts(String abstracts) {
            this.abstracts = abstracts;
        }

        public int getBuyNum() {
            return buyNum;
        }

        public void setBuyNum(int buyNum) {
            this.buyNum = buyNum;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
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

        public int getWhetherBuy() {
            return whetherBuy;
        }

        public void setWhetherBuy(int whetherBuy) {
            this.whetherBuy = whetherBuy;
        }

        public int getWhetherCollection() {
            return whetherCollection;
        }

        public void setWhetherCollection(int whetherCollection) {
            this.whetherCollection = whetherCollection;
        }
    }
}
