package com.wd.health.bean;/**
 * 工程名：project
 * 类名：ChaXunKeShiBean
 */
/**
 类名：ChaXunKeShiBean
 */

import java.util.List;
// todo 根据科室查询对应病症
public class ChaXunKeShiBean {

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
         * departmentId
         */
        private Integer departmentId;
        /**
         * id
         */
        private Integer id;
        /**
         * name
         */
        private String name;

        public Integer getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(Integer departmentId) {
            this.departmentId = departmentId;
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
    }
}
