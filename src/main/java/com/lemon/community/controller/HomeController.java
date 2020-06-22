package com.lemon.community.controller;

import com.lemon.community.entity.DiscussPost;
import com.lemon.community.entity.Page;
import com.lemon.community.entity.User;
import com.lemon.community.service.DiscussPostService;
import com.lemon.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName HomeController
 * @Description TODO 4.3.2 HomeController
 * @Author Lemon
 * @Date 20.6.21 021 15:48:41
 * @Version 1.0
 */
@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    // 查询帖子后返回首页index.html
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page){
        /*
         * TODO 4.5 增加分页功能
         */
        // (1) 服务端：设置总行数
        page.setRows(discussPostService.findDiscussPostRows(0));
        // (2) 服务端：设置当前访问路径
        page.setPath("/index");

        // 查询首页，userId=0，限制一页显示10条
//        List<DiscussPost> list = discussPostService.findDiscussionPosts(0, 0, 10);
        // TODO 4.5 (3) 设置动态的offset和limit
        List<DiscussPost> list = discussPostService.findDiscussionPosts(0, page.getOffset(), page.getLimit());

        // 遍历list，查询每个User，组装到新的list中返回
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null){
            // 在list的每个map中存储post和user两个数据
            for (DiscussPost post : list) {
                Map<String, Object> map = new HashMap<>();

                map.put("post", post);

                User user = userService.findUserById(post.getUserId());
                map.put("user", user);

                discussPosts.add(map);
            }
        }

        // 将存储有post和user的list存储到model中，给页面使用
        model.addAttribute("discussPosts", discussPosts);
        model.addAttribute("page", page);

        return "/index";
    }
}
