package com.dev.webApp.config;

import com.googlecode.flyway.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

@Configuration
public class TestConfiguration {

    //    @Bean
    //    public DataSource getDataSource() {
    //
    //        return new DataSource() {
    //            @Override
    //            public Connection getConnection() throws SQLException {
    //                return null;
    //            }
    //
    //            @Override
    //            public Connection getConnection(String username, String password) throws SQLException {
    //                return null;
    //            }
    //
    //            @Override
    //            public <T> T unwrap(Class<T> iface) throws SQLException {
    //                return null;
    //            }
    //
    //            @Override
    //            public boolean isWrapperFor(Class<?> iface) throws SQLException {
    //                return false;
    //            }
    //
    //            @Override
    //            public PrintWriter getLogWriter() throws SQLException {
    //                return null;
    //            }
    //
    //            @Override
    //            public void setLogWriter(PrintWriter out) throws SQLException {
    //
    //            }
    //
    //            @Override
    //            public void setLoginTimeout(int seconds) throws SQLException {
    //
    //            }
    //
    //            @Override
    //            public int getLoginTimeout() throws SQLException {
    //                return 0;
    //            }
    //
    //            @Override
    //            public Logger getParentLogger() throws SQLFeatureNotSupportedException {
    //                return null;
    //            }
    //        };
    //    }
    //
    //    @Bean
    //    public Flyway flyway() {
    //
    //        Flyway flyway = new Flyway();
    //
    //        flyway.setDataSource(getDataSource());
    //
    //        return flyway;
    //    }
}