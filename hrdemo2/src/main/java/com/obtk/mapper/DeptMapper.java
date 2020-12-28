package com.obtk.mapper;

import com.obtk.entity.Dept;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DeptMapper {

    @Select("select * from dept")
    List<Dept> findAll();
    @Select("select * from dept where deptno=#{deptno}")
    Dept findById(@Param("deptno") int deptno);
}
