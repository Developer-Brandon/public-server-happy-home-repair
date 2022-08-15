package com.dev.webApp.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DataSourceTest {

    // todo: 추후 아래 문서 보고 추가할 내용 있으면 추가. connection pool 설정하는법 나와잇음
    // https://kimvampa.tistory.com/57

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException {

        Connection connection = dataSource.getConnection();

        System.out.println("connection="+connection);
    }
}
