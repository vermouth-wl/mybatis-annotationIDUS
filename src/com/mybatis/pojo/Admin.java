package com.mybatis.pojo;

import java.util.List;

/**
 * @Classname Admin
 * @Description TODO 角色实体类
 * @Author 将烬星火是否太炽热, 明日黄花会开败阡陌
 * @Date 2020-11-17 20:40
 * @Version 1.0
 **/
public class Admin {
    private Integer id;
    private String name;

    private List<Function> funcions;

    @Override
    public String toString() {
        return "角色为: " + name + ", 功能是: " + funcions;
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

    public List<Function> getFuncions() {
        return funcions;
    }

    public void setFuncions(List<Function> funcions) {
        this.funcions = funcions;
    }
}
