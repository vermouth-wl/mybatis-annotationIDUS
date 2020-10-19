package com.mybatis.test;

import com.mybatis.mapper.UserInfoMapper;
import com.mybatis.pojo.UserInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @Classname UserInfoTest
 * @Description TODO 测试类
 * @Author 将烬星火是否太炽热, 明日黄花会开败阡陌
 * @Date 2020-10-18 15:59
 * @Version 1.0
 **/
public class UserInfoTest {
    // SqlSessionFactory工厂
    private SqlSessionFactory sqlSessionFactory;
    // SqlSession
    private SqlSession sqlSession;
    // 前置方法，加载资源文件
    @Before
    public void init() {
        // 读取mybatis配置文件
        String resource = "mybatis-config.xml";
        // 文件流
        InputStream inputStream;
        try {
            // 从配置文件中获取文件流
            inputStream = Resources.getResourceAsStream(resource);
            // 从获取到的文件流中得到会话工厂SqlSessionFactory
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 打开sqlSession
            sqlSession = sqlSessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 根据用户id查询用户
    @Test
    public void testFindUserInfoById() {
        // 获取UserInfoMapper的接口代理对象
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        // 直接调用接口方法
        UserInfo userInfo = userInfoMapper.findUserById(1);
        // 打印输出
        System.out.println(userInfo.userInfo());
    }

    // 根据用户名进行模糊查询
    @Test
    public void findUserInfoByUserNameLike() {
        // 获取UserInfoMapper的接口代理对象
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        // 调用接口方法
        List<UserInfo> userInfos = userInfoMapper.findUserByUsernameLike("测");
        // foreach()输出
        userInfos.forEach(e -> System.out.println(e.userInfo()));
    }

    // 插入数据
    @Test
    public void testAddNewUser() {
        // 获取UserInfoMapper的接口代理对象
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        // 实例化UserInfo对象，封装属性字段
        UserInfo userInfo = new UserInfo();
        userInfo.setUserCode("L001");
        userInfo.setUserName("轩轩");
        userInfo.setPassword("123456");
        userInfo.setName("凌之轩");
        userInfo.setEmail("lingzhixuan@vip.126.com");
        userInfo.setPhone("");
        userInfo.setAddress("北京市西城区");
        userInfo.setRegDate(new Date());
        userInfo.setStatus(1);
        userInfo.setClassId(1);
        // 调用接口方法
        int result = userInfoMapper.addNewUser(userInfo);
        if (result > -1) {
            System.out.println("成功插入" + result + "条数据");
            System.out.println("正在查询所有用户信息...");
            List<UserInfo> userInfoList = userInfoMapper.findAllUser();
            userInfoList.forEach(e -> System.out.println(e.userInfo()));
        } else {
            System.out.println("插入失败");
        }
    }

    // 更新方法
    @Test
    public void testUpdateUser() {
        // 获取UserInfoMapper的接口代理对象
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        // 实例化UserInfo对象，封装属性字段
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("测试账号");
        userInfo.setPassword("test");
        userInfo.setId(13);
        // 调用接口方法
        int result = userInfoMapper.updateUser(userInfo);
        if (result > -1) {
            System.out.println("成功更新" + result + "条数据");
            System.out.println("正在查询所有用户信息...");
            List<UserInfo> userInfoList = userInfoMapper.findAllUser();
            userInfoList.forEach(e -> System.out.println(e.userInfo()));
        } else {
            System.out.println("更新失败");
        }
    }

    // 删除方法
    @Test
    public void testDeleteUser() {
        // 获取接口代理对象
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        // 调用接口方法
        int result = userInfoMapper.deleteUser(15);
        if (result > -1) {
            System.out.println("成功删除" + result + "条数据");
            System.out.println("正在查询所有用户信息...");
            List<UserInfo> userInfoList = userInfoMapper.findAllUser();
            userInfoList.forEach(e -> System.out.println(e.userInfo()));
        }
    }

    // 后置方法
    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }
}
