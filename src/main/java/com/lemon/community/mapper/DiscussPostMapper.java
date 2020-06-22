package com.lemon.community.mapper;

import com.lemon.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName DiscussPostMapper
 * @Description TODO 4.1.2 Mapper接口
 * @Author Lemon
 * @Date 20.6.21 021 14:51:24
 * @Version 1.0
 */
@Mapper
public interface DiscussPostMapper {

    /*
     * 1.一页中查询到的帖子
     *   后续用户个人主页中查询我自己的帖子，传入userId调用此功能
     *   首页中不传入userId，默认是0；传入userId时，需要将userId拼接到SQL语句的where中
     *
     * 后面需要分页查询功能，也需要拼接到SQL语句的limit中
     * (1) offset：每一页中起始行的行号
     * (2) limit：每一页最多显示多少条数据
     */
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    /*
     * 2.查询表中帖子的行数（一共有多少条数据）：用于计算一共有多少页
     *
     * TODO @Param("userId")：给参数取别名
     *  如果方法只有一个参数，并且在<if>里使用，需要对SQL语句拼接，动态使用SQL时，则必须使用@Param取别名
     */
    int selectDiscussPostRows(@Param("userId") int userId);

}
