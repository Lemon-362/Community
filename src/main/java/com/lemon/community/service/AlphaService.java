package com.lemon.community.service;

import com.lemon.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @ClassName AlphaService
 * @Description TODO 1.2 Bean的初始化和销毁
 * @Author Lemon
 * @Date 20.6.20 020 11:28:08
 * @Version 1.0
 */
@Service
//@Scope("prototype")
public class AlphaService {

    // TODO 1.5 controller --> service --> dao
    @Autowired
    private AlphaDao alphaDao;

    public AlphaService(){
        System.out.println("实例化AlphaService");
    }

    // 初始化方法
    @PostConstruct // @PostConstruct：在构造器实例化之后调用该方法
    public void init(){
        System.out.println("初始化AlphaService");
    }

    // 销毁方法
    @PreDestroy // @PreDestroy：在销毁之前调用该方法
    public void destroy(){
        System.out.println("销毁AlphaService");
    }

    // 1.5 controller --> service --> dao
    public String find(){
        return alphaDao.select();
    }
}
