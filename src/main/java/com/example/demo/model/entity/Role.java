package com.example.demo.model.entity;

import java.util.Date;

/**
 * 角色实体类
 *
 * @author XieLongzhen
 * @date 2018/12/1 16:05
 */
public class Role {

    public static final String PREFIX = "ROLE_";

    /**
     * 主键id
     */
    private Long id;

    /**
     * 角色代号
     */
    private String code;

    /**
     * 角色名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    private Long operator;

    private Date operateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}
