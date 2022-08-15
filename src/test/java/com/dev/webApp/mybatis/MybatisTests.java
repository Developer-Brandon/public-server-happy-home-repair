package com.dev.webApp.mybatis;

import com.dev.webApp.mapper.TestMapper;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MybatisTests {

    @Autowired
    private TestMapper testMapper;

    @Test
    public void testMapper() {
        log.info(testMapper.getClass().getName());
    }

    @Test
    public void testTimeMapper() {
        log.info(testMapper.getTime());
    }
}
