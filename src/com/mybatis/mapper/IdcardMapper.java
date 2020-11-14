package com.mybatis.mapper;

import com.mybatis.pojo.Idcard;
import com.mybatis.pojo.UserInfo;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @InterfaceName IdcardMapper
 * @Description TODO 身份证查询接口
 * @Author 将烬星火是否太炽热, 明日黄花会开败阡陌
 * @Date 2020-11-14 12:49
 * @Version 1.0
 **/
public interface IdcardMapper {

    // 根据uid查询身份证信息, 外键字段为userinfo表中的uid字段
    // column属性用于findUserById(Integer id)的参数
    @Select("select * from idcard t where t.id = #{id}")
    @Results({
            @Result(column = "uid", property = "userInfo", one = @One(
                    select = "com.mybatis.mapper.UserInfoMapper.findUserById"
            ))
    })
    public Idcard findIdcardById(Integer id);
}
