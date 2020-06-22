package com.lemon.community;

import com.lemon.community.dao.AlphaDao;
import com.lemon.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Community01Application.class) // TODO 以主程序的配置为配置类
class Community01ApplicationTests implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    /*TODO 获取Spring容器
     * 得到Spring容器ApplicationContext：只需要让该类implements ApplicationContextAware
     * 原来的方式：自己实例化一个Spring容器
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Test
    public void testApplicationContext(){
        System.out.println(applicationContext);

        /*TODO 一、Spring容器
         * TODO 1.1 从容器中获取自动装配的Bean(AlphaDaoHibernateImpl)
         *  ApplicationContext.getBean()
         *  (1) 按照Dao接口类型获取
         *      现在想要用MyBatis替换掉Hibernate，只需要在希望获得的Bean上加上@Primary注解即可
         *      但是现在有两个实现了该接口，所以可以在希望获得的Bean上加上@Primary注解即可
         *  (2) 按照名字和接口类型获取
         *      可以获取指定的Bean
         */
        AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
        System.out.println(alphaDao.select());

        alphaDao = applicationContext.getBean("alphaHibernate", AlphaDao.class);
        System.out.println(alphaDao.select());
    }

    /*
     * TODO 1.2 Bean的初始化和销毁
     *  (1) @PostConstruct：在构造器实例化之后调用该方法
     *  (2) @PreDestroy：在销毁之前调用该方法
     *  被Spring容器管理的Bean默认是单例的，只会实例化一次
     *  @Scope("prototype")：指定为多例
     */
    @Test
    public void testBeanManagement(){
        AlphaService alphaService = applicationContext.getBean(AlphaService.class);

        System.out.println(alphaService);
    }

    /*
     * TODO 1.3 使用配置类加载第三方（jar包）的Bean
     *  使用@Configuration表明这是一个配置类
     *  使用@Bean装配到Spring容器中
     */
    @Test
    public void testBeanConfig(){
        SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));
    }

    /*
     * TODO 1.4 依赖注入DI：不需要使用ApplicationContext.getBean()主动获得Bean
     *  (1) @Autowired：自动注入
     *  (2) @Qualifier("alphaHibernate")：指定名称的Bean
     */
    @Autowired
    @Qualifier("alphaHibernate")
    private AlphaDao alphaDao;

    @Test
    public void testDI(){
        System.out.println(alphaDao.select());
    }

    /*
     * TODO 1.5 controller --> service --> dao
     */
}
