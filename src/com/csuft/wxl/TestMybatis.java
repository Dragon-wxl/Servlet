package com.csuft.wxl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.csuft.wxl.pojo.User;

public class TestMybatis {
	public static SqlSession getSession() throws IOException {
		String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();
         
//        List<User> cs=session.selectList("selectAllUser");
//        for (User c : cs) {
//            System.out.println(c);
//        }
//        session.commit();
//        session.close();
		return session;
	}
}
