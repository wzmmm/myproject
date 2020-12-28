package com.obtk.service;

import com.obtk.entity.Dept;
import com.obtk.mapper.DeptMapper;
import com.obtk.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 业务层：分隔控制层和持久层，分别组合事务和各种接口的调用
 */
public class DeptService {
    private SqlSession sqlSession;
    private DeptMapper deptMapper;

    public void init(){
        //初始化数据库连接和映射器接口
        sqlSession= SqlSessionUtil.getSqlSession();
        deptMapper=sqlSession.getMapper(DeptMapper.class);
    }

    public List<Dept> findAll(){
        init();
        List<Dept> list=deptMapper.findAll();
        SqlSessionUtil.close(sqlSession);
        return list;
    }



}
