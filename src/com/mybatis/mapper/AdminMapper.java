package com.mybatis.mapper;

import com.mybatis.pojo.Admin;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @InterfaceName AdminMapper
 * @Description TODO 管理员接口
 * @Author 将烬星火是否太炽热, 明日黄花会开败阡陌
 * @Date 2020-11-17 21:33
 * @Version 1.0
 **/
public interface AdminMapper {
    // 根据管理员id查询出对应的管理员信息
    @Select("select * from admin t where t.id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            // 多对多关联属性
            @Result(column = "id", property = "funcions", many = @Many(select =
            "com.mybatis.mapper.FunctionMapper.findFunctionByAid"))
    })
    public Admin findAdminById(Integer id);
}
