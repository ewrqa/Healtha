package com.wd.health.bean.userbean;

import java.util.List;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.bean.userbean</p>
 * <p>简述:用户收藏资讯的bean</p>
 *
 * @author 张凯涛
 * @date 2022/7/25
 */
public class UserCollcetMessageBean {
    /**
     * result : [{"createTime":1658713458000,"id":153,"infoId":2,"thumbnail":"https://jkcdn.pajk.com.cn/image/T1wFLSBCLT1RCvBVdK","title":"黄芪泡水喝，能给我们的身体带来什么变化？医生用临床经验说话"},{"createTime":1658713386000,"id":152,"infoId":1,"thumbnail":"https://jkcdn.pajk.com.cn/image/T1slZcBvEg1RCvBVdK;https://jkcdn.pajk.com.cn/image/T1YYVOBvYT1RCvBVdK;https://jkcdn.pajk.com.cn/image/T1mRDSB7xT1RCvBVdK","title":"春季预防三高，预防心脑血管疾病，不得不提到的三个\u201c笋\u201d子！"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    /**
     * createTime : 1658713458000
     * id : 153
     * infoId : 2
     * thumbnail : https://jkcdn.pajk.com.cn/image/T1wFLSBCLT1RCvBVdK
     * title : 黄芪泡水喝，能给我们的身体带来什么变化？医生用临床经验说话
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
        private int id;
        private int infoId;
        private String thumbnail;
        private String title;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getInfoId() {
            return infoId;
        }

        public void setInfoId(int infoId) {
            this.infoId = infoId;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
