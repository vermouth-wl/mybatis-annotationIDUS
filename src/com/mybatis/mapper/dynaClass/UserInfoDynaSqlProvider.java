package com.mybatis.mapper.dynaClass;

import com.mybatis.pojo.UserInfo;
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

    // 新增方法
    // 拼接SQL
    public String insertUserInfo(UserInfo userInfo) {
        return new SQL() {
            {
                INSERT_INTO("userinfo");
                if (userInfo.getUserCode() != null) {
                    VALUES("user_code", "#{userCode}");
                }
                if (userInfo.getUserName() != null) {
                    VALUES("user_name", "#{userName}");
                }
                if (userInfo.getPassword() != null) {
                    VALUES("password", "#{password}");
                }
                if (userInfo.getName() != null) {
                    VALUES("name", "#{name}");
                }
                if (userInfo.getEmail() != null) {
                    VALUES("email", "#{email}");
                }
                if (userInfo.getPhone() != null) {
                    VALUES("phone", "#{phone}");
                }
                if (userInfo.getAddress() != null) {
                    VALUES("address", "#{address}");
                }
                if (userInfo.getRegDate() != null) {
                    VALUES("regDate", "#{regDate}");
                }
                if (userInfo.getStatus() != null) {
                    VALUES("status", "#{status}");
                }
                if (userInfo.getClazz() != null) {
                    VALUES("class_id", "#{clazz}");
                }
            }
        }.toString();
    }

    // 更新方法
    // 拼接SQL
    public String updateUserInfo(UserInfo userInfo) {
        return new SQL() {
            {
                UPDATE("userinfo");
                if (userInfo.getUserName() != null) {
                    SET("user_name = #{userName}");
                }
                if (userInfo.getPassword() != null) {
                    SET("password = #{password}");
                }
                if (userInfo.getEmail() != null) {
                    VALUES("email", "#{email}");
                }
                if (userInfo.getPhone() != null) {
                    VALUES("phone", "#{phone}");
                }
                if (userInfo.getAddress() != null) {
                    VALUES("address", "#{address}");
                }
                WHERE("id = #{id}");
            }
        }.toString();
    }
}
