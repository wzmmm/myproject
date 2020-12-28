package com.obtk.web.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.obtk.entity.Dept;
import com.obtk.entity.Emp;
import com.obtk.entity.PageBean;
import com.obtk.service.EmpService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

@WebServlet("/emp")
public class EmpServlet extends HttpServlet {

    private EmpService empService=new EmpService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String action=req.getParameter("action");
        if(Objects.isNull(action)){
            //查分页
            //默认查询第一页，每页7条记录
            int pageNo=1,pageSize=7;
            if(!Objects.isNull(req.getParameter("pageNo"))){
                //如果请求有传页号，按请求的页号查询
                pageNo=Integer.parseInt(req.getParameter("pageNo"));
            }
            PageBean<Emp> pb=empService.findByPage(pageNo,pageSize);
            Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            resp.getWriter().print(gson.toJson(pb));
        }else if("add".equals(action)){
            addEmp(req, resp);
        }else if("findByNo".equals(action)){
            int empno=Integer.parseInt(req.getParameter("empno"));
            Emp emp=empService.findByNo(empno);
            Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            resp.getWriter().print(gson.toJson(emp));
        }
    }

    private void addEmp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Emp emp=new Emp();
        try {
            //将请求参数自动映射到emp实例属性
            BeanUtils.populate(emp,req.getParameterMap());
            //处理无法自动映射的属性
            int deptno=Integer.parseInt(req.getParameter("deptno"));
            Dept dept=new Dept();
            dept.setDeptNo(deptno);
            emp.setDept(dept);
            //调用业务对象的添加员工方法
            int count=empService.addEmp(emp);
            //向客户端响应受影响的行数
            resp.getWriter().print(count);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
