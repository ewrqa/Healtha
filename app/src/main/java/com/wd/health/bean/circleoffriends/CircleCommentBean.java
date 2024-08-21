package com.wd.health.bean.circleoffriends;

import java.util.List;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.bean.circleoffriends</p>
 * <p>简述:病友圈评论列表</p>
 *
 * @author 张凯涛
 * @date 2022/7/28
 */
public class CircleCommentBean {
    /**
     * result : [{"commentId":24,"commentTime":1656854030000,"commentUserId":18,"content":"qweqwe\n\n","headPic":"http://mobile.bwstudent.com/images/health/user/head_pic/default/default_head_4.jpg","nickName":"zS_YKZHT","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":2}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * commentId : 24
     * commentTime : 1656854030000
     * commentUserId : 18
     * content : qweqwe
     * headPic : http://mobile.bwstudent.com/images/health/user/head_pic/default/default_head_4.jpg
     * nickName : zS_YKZHT
     * opinion : 0
     * opposeNum : 0
     * supportNum : 0
     * whetherDoctor : 2
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
        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String content;
        private String headPic;
        private String nickName;
        private int opinion;
        private int opposeNum;
        private int supportNum;
        private int whetherDoctor;

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getOpinion() {
            return opinion;
        }

        public void setOpinion(int opinion) {
            this.opinion = opinion;
        }

        public int getOpposeNum() {
            return opposeNum;
        }

        public void setOpposeNum(int opposeNum) {
            this.opposeNum = opposeNum;
        }

        public int getSupportNum() {
            return supportNum;
        }

        public void setSupportNum(int supportNum) {
            this.supportNum = supportNum;
        }

        public int getWhetherDoctor() {
            return whetherDoctor;
        }

        public void setWhetherDoctor(int whetherDoctor) {
            this.whetherDoctor = whetherDoctor;
        }
    }
}
