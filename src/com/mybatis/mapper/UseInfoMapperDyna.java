package com.mybatis.mapper;

import com.mybatis.mapper.dynaClass.UserInfoDynaSqlProvider;
import com.mybatis.pojo.UserInfo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName UseInfoMapperDyna
 * @Description TODO 使用基于注解的动态SQL，对userinfo进行增删改查
 * @Author 将烬星火是否太炽热, 明日黄花会开败阡陌
 * @Date 2020-11-21 15:53
 * @Version 1.0
 **/
public interface UseInfoMapperDyna {

    @SelectProvider(type = UserInfoDynaSqlProvider.class, method = "selectWithParam")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "userCode", column = "user_code"),
            @Result(property = "userName", column = "user_name")
    })
    public List<UserInfo> findUserInfoByCond(Map<String, Object> param);
}
