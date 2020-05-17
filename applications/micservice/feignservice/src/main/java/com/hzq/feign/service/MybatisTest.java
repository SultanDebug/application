package com.hzq.feign.service;

import com.hzq.feign.entity.User;
import com.hzq.feign.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-02-28
 */
public class MybatisTest {
    public static void main(String[] args) throws IOException {
        //加载全局配置文件
        String resource = "mybatis-config.xml_bak";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession openSession = sqlSessionFactory.openSession();

        SqlSession openSession1 = sqlSessionFactory.openSession();

        boolean cache = false;

        if(cache){
            try {


                //第一个参数：命名空间+配置文件id
                //第二个参数：要查询的数据库表id值
//            User user = openSession.selectOne("com.hzq.framework.test.mybatis.mapper.UserMapper.getUser",1);

                UserMapper mapper = openSession.getMapper(UserMapper.class);

                UserMapper mapper1 = openSession1.getMapper(UserMapper.class);

                //一级缓存写入
                User user = mapper.getUser(3);

                System.out.println("session 1 写入 :"+user);

//            mapper.updateUser(3,"测试3");

                //删除一级缓存
                mapper.updateUser(3,"测试3");
                openSession.commit();

                //测试一级缓存
                User user2 = mapper.getUser(3);

                System.out.println("session1 获取 :"+user2);

                //跨会话缓存
                User user1 = mapper1.getUser(3);

                System.out.println("session2 获取 :"+user1);



            }finally {
                openSession.close();
                openSession1.close();
            }
        }else{
            UserMapper mapper = openSession.getMapper(UserMapper.class);

            UserMapper mapper1 = openSession1.getMapper(UserMapper.class);

            //二级缓存
            User user = mapper.getUser(3);

            System.out.println("session1 1 :"+user);

            //二级缓存写入  必须
//            mapper.updateUser(3,"测试4");
            openSession.commit();

            //跨会话缓存
            User user1 = mapper1.getUser(3);

            System.out.println("session2 1 :"+user1);

            openSession.close();
            openSession1.close();
        }


    }
}
