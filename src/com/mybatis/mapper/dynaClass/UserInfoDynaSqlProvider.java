package com.mybatis.mapper.dynaClass;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @Classname UserInfoDynaSqlProvider
 * @Description TODO 基于注解动态类
 * @Author 将烬星火是否太炽热, 明日黄花会开败阡陌
 * @Date 2020-11-21 15:55
 * @Version 1.0
 **/
public class UserInfoDynaSqlProvider {

    // 查询方法
    // selectWithParam(Map<String, Object> param)方法中，根据参数param构建动态SQL
    public String selectWithParam(Map<String, Object> param) {
        return new SQL() {
            {
                SELECT("*");
                FROM("userinfo");
                if (param.get("id") != null) {
                    WHERE("id = #{id}");
                }
                if (param.get("userName") != null) {
                    WHERE("user_name = #{userName}");
                }
                if (param.get("userCode") != null) {
                    WHERE("user_code = #{userCode}");
                }
            }
        }.toString();
    }
}
