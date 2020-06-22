package com.lemon.community.mapper;

import com.lemon.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName UserMapper
 * @Description TODO 3.3 在DAO层创建Mapper(DAO)接口，不需要实现类
 * @Author Lemon
 * @Date 20.6.21 021 11:16:28
 * @Version 1.0
 */
@Mapper // @Mapper：表示这是一个MyBatis的Mapper接口
public interface UserMapper {

    // 按照Id查询User
    User selectById(int id);

    // 按照username查询User
    User selectByName(String username);

    // 按照email查询User
    User selectByEmail(String email);

    // 插入User数据
    int insertUser(User user);

    // 修改User状态（激活/未激活）
    int updateStatus(int id, int status);

    // 更新头像
    int updateHeader(int id, String headerUrl);

    // 更新密码
    int updatePassword(int id, String password);
}
