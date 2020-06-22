package com.lemon.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName LoggerTests
 * @Description TODO 5.3.3
 * @Author Lemon
 * @Date 20.6.22 022 10:32:59
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Community01Application.class) // TODO 以主程序的配置为配置类
public class LoggerTests {

    // 实例化，传入当前类的class作为looger的名字
    private static final Logger logger = LoggerFactory.getLogger(LoggerTests.class);

    @Test
    public void testLogger(){
        System.out.println(logger.getName());

        logger.debug("debug log");
        logger.info("info log");
        logger.warn("warn log");
        logger.error("error log");
    }
}
