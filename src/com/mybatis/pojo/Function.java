package com.mybatis.pojo;

/**
 * @Classname Function
 * @Description TODO 方法列表
 * @Author 将烬星火是否太炽热, 明日黄花会开败阡陌
 * @Date 2020-11-17 20:47
 * @Version 1.0
 **/
public class Function {
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return name;
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
