package com.wd.health.bean;/**
 * 工程名：project
 * 类名：YaoPinDuiYingBean
 */
/**
 类名：YaoPinDuiYingBean
 */

import java.util.List;

//todo 根据药品类目查询常见药品
public class YaoPinDuiYingBean {

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
         * drugsCategoryId
         */
        private Integer drugsCategoryId;
        /**
         * id
         */
        private Integer id;
        /**
         * name
         */
        private String name;
        /**
         * picture
         */
        private String picture;

        public Integer getDrugsCategoryId() {
            return drugsCategoryId;
        }

        public void setDrugsCategoryId(Integer drugsCategoryId) {
            this.drugsCategoryId = drugsCategoryId;
        }

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

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
