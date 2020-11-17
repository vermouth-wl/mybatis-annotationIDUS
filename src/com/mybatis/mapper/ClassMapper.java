package com.mybatis.mapper;

import com.mybatis.pojo.Clazz;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @InterfaceName ClassMapper
 * @Description TODO 班级接口类，和用户为一对多关系
 * @Author 将烬星火是否太炽热, 明日黄花会开败阡陌
 * @Date 2020-11-15 14:56
 * @Version 1.0
 **/
public interface ClassMapper {
    // 根据班级id查询出班级编码和班级名称
    @Select("select * from class t where t.id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "classCode", column = "class_code"),
            @Result(property = "className", column = "class_name"),
            // 一对多关联属性设置，column属性表示传入findUserInfoByClassId()的值,
            @Result(property = "userInfos", column = "id", many = @Many(select = "com.mybatis.mapper.UserInfoMapper.findUserInfoByClassId"))
    })
    public List<Clazz> findClassById(Integer id);

    // 通过嵌套结果查询
    @Select("select t1.class_code,t1.class_name, t2.* from class t1 " +
            "left join userinfo t2 on t1.id = t2.class_id where t1.class_code = #{classCode}")
    // 结果xml实现嵌套结果查询
    @ResultMap("com.mybatis.mapper.xml.ClassMapper.classMap")
    public List<Clazz> findClazzByClassCode(Clazz clazz);
}
