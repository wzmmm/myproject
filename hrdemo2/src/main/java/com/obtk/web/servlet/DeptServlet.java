package com.obtk.web.servlet;

import com.google.gson.Gson;
import com.obtk.entity.Dept;
import com.obtk.service.DeptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/dept")
public class DeptServlet extends HttpServlet {
    //定义业务类实例
    private DeptService deptService=new DeptService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用业务实例方法，向下继续调用持久层方法查询表格数据
        List<Dept> list=deptService.findAll();
        Gson gson=new Gson();
        //向页面返回json数据
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(gson.toJson(list));
    }
}
