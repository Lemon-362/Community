package com.lemon.community.controller;

import com.lemon.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author Lemon
 * @Date 20.6.20 020 11:02:36
 * @Version 1.0
 */
@Controller
@RequestMapping("/alpha")
public class HelloController {

    // TODO 1.5 controller --> service --> dao
    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello Spring Boot";
    }

    // 1.5 controller --> service --> dao
    @RequestMapping("/data")
    @ResponseBody
    public String getData() {
        return alphaService.find();
    }

    /*
     * TODO 2.2 SpringMVC 处理请求和响应
     *  (1) HttpServletRequest：http请求
     *  (2) HttpServletResponse：http响应
     */
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        /*
         * 获取请求数据：HttpServletRequest
         */
        // 获取请求方法
        System.out.println(request.getMethod());
        // 获取请求路径
        System.out.println(request.getServletPath());
        // 获取请求头（多行）
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        // 获取传入的请求参数 TODO 在url上输入：?参数名=值&参数名=值
        System.out.println(request.getParameter("code"));

        /*
         * 返回响应数据：HttpServletResponse
         */
        //
        // 返回响应的类型
        response.setContentType("text/html;charset=utf-8");
        try ( // Java7特性：自动关闭
              // 获取输出流
              PrintWriter writer = response.getWriter();
        ) {
            // 打印输出
            writer.write("<h1>牛客网<h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * TODO 2.3 Request请求
     *  (1) GET请求：向服务器获取数据
     *      查询所有学生：当前页数current，每页最多显示的数据limit
     *          @RequestParam：/students?current=1&limit=20
     *      查询一个学生：id
     *          @PathVariable：/students/123
     *  (2) POST请求：向服务器提交数据
     *      获取POST请求的参数：入参名称和表单名称相同
     */
    // (1) GET请求传参方式一：/students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {

        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    // (1) GET请求传参方式二：/students/123
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){

        System.out.println(id);
        return "a student";
    }

    // (2) POST请求：获取POST请求的参数
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    /*
     * TODO 2.4 Response响应：响应HTML数据
     *   thymeleaf需要动态数据和模板，通过模板引擎解析即可生成动态的HTML页面
     *  (1) 返回ModelAndView模板
     *      http://localhost:8080/alpha/teacher
     *  (2) 将Model作为参数,返回View视图的路径（推荐）
     *      http://localhost:8080/alpha/school
     */
    // 响应HTML数据方式一：返回ModelAndView模板
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        // 往模板中传入参数
        mav.addObject("name", "张三");
        mav.addObject("age", 30);
        // 设置模板的路径和名字：路径是从templates往下找，名字不需要写后缀
        mav.setViewName("/demo/view");
        return mav;
    }

    // 响应HTML数据方式二：返回View视图的路径，将Model作为参数
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model){

        model.addAttribute("name", "北京大学");
        model.addAttribute("age", 80);
        return "/demo/view";
    }

    /*
     * TODO 2.5 Response响应：响应JSON数据
     *   一般出现在异步请求中：当前网页没有刷新，但查询了服务器，在页面上返回了一个结果
     *   （例如：注册时昵称显示已被占用）
     *  @ResponseBody + Map<String, Object>返回类型：自动将Map转换成JSON字符串发送给浏览器
     *      http://localhost:8080/alpha/emp
     *
     *  Java对象 -> JSON字符串 -> JS对象
     *   代码使用Java对象，而浏览器解析对象使用JS对象
     *   JSON字符串在中间起到过渡作用
     */
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        return emp;
    }

    // 返回多个数据的集合，转换成JSON数据
    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "李四");
        emp.put("age", 24);
        emp.put("salary", 9000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "王五");
        emp.put("age", 25);
        emp.put("salary", 10000.00);
        list.add(emp);

        return list;
    }
}
