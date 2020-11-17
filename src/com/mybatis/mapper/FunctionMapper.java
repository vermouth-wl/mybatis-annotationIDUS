package com.mybatis.mapper;

import com.mybatis.pojo.Function;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @InterfaceName Funcion
 * @Description TODO 功能接口
 * @Author 将烬星火是否太炽热, 明日黄花会开败阡陌
 * @Date 2020-11-17 21:27
 * @Version 1.0
 **/
public interface FunctionMapper {
    // 根据管理员列表获取功能列表
    @Select("select * from functions t where t.id in (select fid from admin_functions_mapper a where a.aid = #{id})")
    public List<Function> findFunctionByAid(Integer aid);
}
