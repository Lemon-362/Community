package com.lemon.community.dao;

import org.springframework.stereotype.Repository;

/**
 * @ClassName AlphaDaoHibernateImpl
 * @Description TODO 1.1 使用Hibernate实现AlphaDao接口
 * @Author Lemon
 * @Date 20.6.20 020 11:12:37
 * @Version 1.0
 */
@Repository("alphaHibernate") // TODO @Repository：数据库访问的注解，使得Spring容器可以自动扫描到该Bean
public class AlphaDaoHibernateImpl implements AlphaDao {
    @Override
    public String select() {
        return "Hibernate";
    }
}
