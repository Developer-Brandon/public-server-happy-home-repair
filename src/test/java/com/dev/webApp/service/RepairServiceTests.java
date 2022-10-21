package com.dev.webApp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class RepairServiceTests {

    // todo: 추후 mocking 개념 도입 후 자세한 테스트 예정

    private static final Logger logger = LoggerFactory.getLogger(RepairServiceTests.class);

    @Autowired
    private RepairService blogService;

    @Test
    public void serviceExistTest() {
        assertNotNull(blogService);
    }
}
