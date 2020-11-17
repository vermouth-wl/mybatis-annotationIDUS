package com.mybatis.mapper;

import com.mybatis.pojo.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname UserInfoMapper
 * @Description TODO 实现对用户增删改查
 * @Author 将烬星火是否太炽热, 明日黄花会开败阡陌
 * @Date 2020-10-18 15:02
 * @Version 1.0
 **/
public interface UserInfoMapper {

    // 查询所有用户
    @Select("select * from userinfo")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userCode", column = "user_code"),
    })
    public List<UserInfo> findAllUser();

    // 根据id查询用户
    @Select("select * from userinfo t where t.id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userCode", column = "user_code"),
    })
    public UserInfo findUserById(Integer id);

    // 根据用户名模糊查询用户
    @Select("select * from userinfo t where t.user_name like concat(concat('%', #{userName}), '%')")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userCode", column = "user_code")
    })
    public List<UserInfo> findUserByUsernameLike(String userName);

    // 一对多查询
    // 根据班级编号查询所有用户
    @Select("select * from userinfo t where t.class_id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "userCode", column = "user_code"),
            @Result(property = "userName", column = "user_name")
    })
    public List<UserInfo> findUserInfoByClassId(Integer id);

    // 一对一查询
    // 根据用户编号查询用户
    @Select("select * from userinfo t where t.id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "userCode", column = "user_code"),
            @Result(property = "userName", column = "user_name"),
            // 一对一关联属性字段
            @Result(property = "clazz", column = "class_id", one = @One(select = "com.mybatis.mapper.ClassMapper.findClassById"))
    })
    public UserInfo findUserInfoById(Integer id);

    // 插入数据
    @Insert("insert into userinfo(user_code, user_name, password, name, email, phone, address, regDate, status, class_id) " +
            "values(#{userCode}, #{userName}, #{password}, #{name}, #{email}, #{phone}, #{address}, #{regDate}, #{status}, #{classId})")
    public Integer addNewUser(UserInfo userInfo);

    // 更新数据
    @Update("update userinfo set user_name = #{userName}, password = #{password} where id = #{id}")
    public Integer updateUser(UserInfo userInfo);

    // 删除数据
    @Delete("delete from userinfo where id = #{id}")
    public Integer deleteUser(Integer id);

}
