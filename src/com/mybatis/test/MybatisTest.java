package com.mybatis.test;

import com.mybatis.mapper.*;
import com.mybatis.pojo.Admin;
import com.mybatis.pojo.Clazz;
import com.mybatis.pojo.Idcard;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname UserInfoTest
 * @Description TODO 测试类
 * @Author 将烬星火是否太炽热, 明日黄花会开败阡陌
 * @Date 2020-10-18 15:59
 * @Version 1.0
 **/
public class MybatisTest {
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

    // 一对一关联查询，查询用户信息查询出对应的身份证号信息
    @Test
    public void testOne2one() {
        // 获取接口代理对象
        IdcardMapper idcardMapper = sqlSession.getMapper(IdcardMapper.class);
        // 调用接口方法
        Idcard idcard = idcardMapper.findIdcardById(1);
        System.out.println(idcard);
    }

    // 一对多关联查询，查询班级信息查询出对应的班级用户信息
    @Test
    public void testOne2Many() {
        // 获取接口代理对象
        ClassMapper classMapper = sqlSession.getMapper(ClassMapper.class);
        // 直接调用接口对象，查询出班级对象及其关联的用户对象
        List<Clazz> classList = classMapper.findClassById(4);
        classList.forEach(e -> System.out.println(e.classMessage()));
    }

    // 多对一关联查询，通过查询用户信息查询出对应班级信息
    @Test
    public void testMany2one() {
        // 获取接口代理对象
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        // 调用接口方法，查询userInfo对象
        UserInfo userInfo = userInfoMapper.findUserInfoById(1);
        System.out.println("用户信息: " + userInfo.userInfo());
        System.out.println("班级信息: " + userInfo.getClazz().getClassName());
    }

    // 一对多嵌套结果查询
    @Test
    public void testOne2manyResult() {
        // 获取接口代理对象
        ClassMapper classMapper = sqlSession.getMapper(ClassMapper.class);
        // 实例化Clazz对象，封装条件
        Clazz clazz = new Clazz();
        clazz.setClassCode("109");
        // 执行接口方法
        List<Clazz> clazzes = classMapper.findClazzByClassCode(clazz);
        clazzes.forEach(e -> System.out.println(e.classMessage()));
    }

    // 多对多查询
    @Test
    public void testMany2Many() {
        // 获取接口代理对象
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        // 执行接口方法
        Admin admin = adminMapper.findAdminById(1);
        System.out.println(admin);
    }

    // 动态SQL之SelectProvider
    @Test
    public void testFindUserInfoByCond() {
        // 获取UserInfoMapperDyna的接口代理对象
        UserInfoMapperDyna userInfoMapperDyna = sqlSession.getMapper(UserInfoMapperDyna.class);
        // 使用map类型对象封装查询条件
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userCode", "W001");
        param.put("userName", "王磊");
        // 调用接口方法
        List<UserInfo> userInfos = userInfoMapperDyna.findUserInfoByCond(param);
        userInfos.forEach(e -> System.out.println(e.userInfo()));
    }

    // 动态SQL之InsertProvider
    @Test
    public void testInsertUserInfo() {
        // 获取UserInfoMapperDyna的接口代理对象
        UserInfoMapperDyna userInfoMapperDyna = sqlSession.getMapper(UserInfoMapperDyna.class);
        // 实例化UserInfo对象，对属性赋值
        UserInfo userInfo = new UserInfo();
        userInfo.setUserCode("H001");
        userInfo.setUserName("荒天帝");
        userInfo.setPassword("123456");
        userInfo.setName("石昊");
        userInfo.setEmail("shihao@163.com");
        userInfo.setPhone("13195765622");
        userInfo.setAddress("罪洲");
        userInfo.setRegDate(new Date());
        userInfo.setStatus(1);

        // 判断用户编码是否存在，已存在则不能添加
        // 使用map对象封装查询条件
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userCode", userInfo.getUserCode());
        List<UserInfo> userInfoList = userInfoMapperDyna.findUserInfoByCond(param);
        if (userInfoList.isEmpty()) {
            // 调用接口方法
            int result = userInfoMapperDyna.insertUserInfo(userInfo);
            if (result > 0) {
                System.out.println("新增用户成功, 正在重新执行查询...");
                // 查询所有用户
                Map<String, Object> params = new HashMap<String, Object>();
                // 调用接口方法
                List<UserInfo> userInfos = userInfoMapperDyna.findUserInfoByCond(params);
                userInfos.forEach(e -> System.out.println(e.userInfo()));
            } else {
                System.out.println("新增用户失败");
            }
        } else {
            System.out.println("该用户已存在");
        }
    }

    // 动态SQL之@UpdateProvider
    @Test
    public void testUpdateUserInfo() {
        // 获取UserInfoMapperDyna的接口代理对象
        UserInfoMapperDyna userInfoMapperDyna = sqlSession.getMapper(UserInfoMapperDyna.class);
        // 使用Map封装查询条件
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", 16);
        // 调用接口方法，查询id为16的用户
        UserInfo userInfo = userInfoMapperDyna.findUserInfoByCond(param).get(0);
        // 修改密码
        userInfo.setPassword("ye_fan123");
        // 调用接口更新
        int result = userInfoMapperDyna.updateUserInfo(userInfo);
        if (result > -1) {
            System.out.println("更新用户成功, 正在重新执行查询...");
            // 查询所有用户
            // Map<String, Object> params = new HashMap<String, Object>();
            List<UserInfo> userInfos = userInfoMapperDyna.findUserInfoByCond(param);
            userInfos.forEach(e -> System.out.println(e.userInfo()));
        } else {
            System.out.println("更新用户失败");
        }
    }

    // 动态SQL之@DeleteProvider
    @Test
    public void testDeleteUserInfo() {
        // 获取UserInfoMapperDyna的接口代理对象
        UserInfoMapperDyna userInfoMapperDyna = sqlSession.getMapper(UserInfoMapperDyna.class);
        // 使用map对象封装条件
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userCode", "C001");
        // 先调用查询接口查询是否有符合条件的记录
        List<UserInfo> userInfos = userInfoMapperDyna.findUserInfoByCond(param);
        if (userInfos.isEmpty()) {
            System.out.println("没有找到符合条件的记录");
        } else {
            int result = userInfoMapperDyna.deleteUserInfo(param);
            if (result > 0) {
                System.out.println("删除成功, 正在执行重新查询...");
                // 使用map对象封装查询对象
                Map<String, Object> params = new HashMap<String, Object>();
                // 调用查询接口查询所有用户
                List<UserInfo> userInfos1 = userInfoMapperDyna.findUserInfoByCond(params);
                userInfos1.forEach(e -> System.out.println(e.userInfo()));
            }
        }
    }

    // 后置方法
    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }
}
