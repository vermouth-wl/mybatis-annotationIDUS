package com.mybatis.pojo;

import java.util.List;

/**
 * @Classname Clazz
 * @Description TODO 班级实体类，与UserInfo为一对多关系
 * @Author 将烬星火是否太炽热, 明日黄花会开败阡陌
 * @Date 2020-11-15 14:35
 * @Version 1.0
 **/
public class Clazz {
    private Integer id;
    private String classCode;
    private String className;
    private List<UserInfo> userInfos;

    public String classMessage() {
        return "班级编号: " + classCode + ", 班级名称: " + className + ", 该班级学生信息: " + userInfos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

}
