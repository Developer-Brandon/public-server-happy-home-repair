package com.dev.webApp.persistenceConfiguration;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DBConnectionTest {

    // todo: 추후 아래 문서 보고 추가할 내용 있으면 추가. connection pool 설정하는법 나와잇음
    // https://kimvampa.tistory.com/57

    private static Logger logger = LoggerFactory.getLogger(DBConnectionTest.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testDBConnection() throws Exception {

        Connection connection = dataSource.getConnection();

        logger.info("connection={}", connection);
    }

    @Test
    public void testMyBatisConnection() throws Exception {

        SqlSession session = sqlSessionFactory.openSession();

        logger.info("session={}", session);

        Connection connection = session.getConnection();

        logger.info("connection={}", connection);
    }
}
