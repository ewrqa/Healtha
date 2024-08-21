package com.wd.health.bean;/**
 * 工程名：project
 * 类名：YaoPinListBean
 */
/**
 类名：YaoPinListBean
 */

import java.util.List;

// todo 药品科目分类列表查询
public class YaoPinListBean {

    /**
     * result
     */
    private List<ResultDTO> result;
    /**
     * message
     */
    private String message;
    /**
     * status
     */
    private String status;

    public List<ResultDTO> getResult() {
        return result;
    }

    public void setResult(List<ResultDTO> result) {
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

    public static class ResultDTO {
        /**
         * id
         */
        private Integer id;
        /**
         * name
         */
        private String name;
        /**
         * rank
         */
        private Integer rank;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getRank() {
            return rank;
        }

        public void setRank(Integer rank) {
            this.rank = rank;
        }
    }
}
