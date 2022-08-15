package com.dev.webApp.persistence;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCconnectionTests {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection() throws SQLException {

        // todo: about connection pool
        // https://kimvampa.tistory.com/44

        // todo: about jdbc test
        // https://kimvampa.tistory.com/56?category=800652

        Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/web_app_test?serverTimezone=Asia/Seoul",
                            "root",
                            "MomentFactory1!1!1!");
            System.out.println(con);
    }
}
