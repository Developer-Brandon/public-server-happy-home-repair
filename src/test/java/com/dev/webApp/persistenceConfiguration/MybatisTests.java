package com.dev.webApp.persistenceConfiguration;

import com.dev.webApp.mapper.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MybatisTests {

    private static Logger logger = LoggerFactory.getLogger(MybatisTests.class);

    @Autowired
    private TestMapper testMapper;

    @Test
    public void testTimeMapper() {
        logger.info("testMapper가 돌아가는 시간: {} ", testMapper.getTime());
    }
}
