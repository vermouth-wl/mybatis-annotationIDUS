package com.mybatis.pojo;

/**
 * @Classname Idcard
 * @Description TODO 身份证实体类
 * @Author 将烬星火是否太炽热, 明日黄花会开败阡陌
 * @Date 2020-11-14 12:33
 * @Version 1.0
 **/
public class Idcard {

    private Integer id;
    private String cno;

    // 一对一关联映射
    private UserInfo userInfo;

    @Override
    public String toString() {
        return userInfo.userInfo() + ", " +
                "身份证号为: " + cno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
