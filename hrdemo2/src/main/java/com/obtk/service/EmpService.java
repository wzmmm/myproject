package com.obtk.service;

import com.obtk.entity.Emp;
import com.obtk.entity.PageBean;
import com.obtk.mapper.EmpMapper;
import com.obtk.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpService {

    private SqlSession sqlSession;
    private EmpMapper empMapper;

    private void init(){
        sqlSession= SqlSessionUtil.getSqlSession();
        empMapper=sqlSession.getMapper(EmpMapper.class);
    }

    /**
     * 添加单个员工信息
     * @param emp
     * @return
     */
    public int addEmp(Emp emp){
        init();
        int count=empMapper.addEmp(emp);
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
        return count;
    }

    public Emp findByNo(int empno){
        init();
        Emp emp=empMapper.findByNo(empno);
        SqlSessionUtil.close(sqlSession);
        return emp;
    }

    /**
     * 查询分页数据对象
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageBean<Emp> findByPage(int pageNo,int pageSize){
        init();
        Map<String,Object> params=new HashMap<>();
        params.put("pageNo",pageNo);
        params.put("pageSize",pageSize);
        //查单页数据
        List<Emp> list=empMapper.findByPage(params);
        //查总记录数
        int count=empMapper.findCount();
        PageBean<Emp> pb=new PageBean<>();
        pb.setData(list);
        pb.setPageNo(pageNo);
        pb.setPageSize(pageSize);
        //计算总页数
        int total=(count+pageSize-1)/pageSize;
        pb.setTotal(total);
        SqlSessionUtil.close(sqlSession);
        return pb;
    }
}
