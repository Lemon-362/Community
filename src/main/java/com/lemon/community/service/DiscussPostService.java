package com.lemon.community.service;

import com.lemon.community.entity.DiscussPost;
import com.lemon.community.mapper.DiscussPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName DiscussPostService
 * @Description TODO 4.2.1 DiscussPostService
 * @Author Lemon
 * @Date 20.6.21 021 15:36:02
 * @Version 1.0
 */
@Service
public class DiscussPostService {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    // List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);
    public List<DiscussPost> findDiscussionPosts(int userId, int offset, int limit){
        return discussPostMapper.selectDiscussPosts(userId, offset, limit);
    }

    // int selectDiscussPostRows(@Param("userId") int userId);
    public int findDiscussPostRows(int userId){
        return discussPostMapper.selectDiscussPostRows(userId);
    }

}
