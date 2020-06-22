package com.lemon.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @ClassName AlphaDaoMyBatisImpl
 * @Description TODO 1.1
 * @Author Lemon
 * @Date 20.6.20 020 11:19:25
 * @Version 1.0
 */
@Repository
@Primary
public class AlphaDaoMyBatisImpl implements AlphaDao{
    @Override
    public String select() {
        return "MyBatis";
    }
}
