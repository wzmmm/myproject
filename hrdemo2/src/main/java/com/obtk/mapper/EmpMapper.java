package com.obtk.mapper;

import com.obtk.entity.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface EmpMapper {
    @Insert("insert into emp values(null,#{ename}," +
            "#{sal},#{hiredate},#{dept.deptNo},#{job})")
    int addEmp(Emp emp);

    @Results(value = {@Result(property = "empno",column = "empno",id = true),
                      @Result(property = "ename",column = "ename"),
                      @Result(property = "sal",column = "sal"),
                      @Result(property = "job",column = "job"),
                      @Result(property = "hiredate",column = "hiredate"),
                      @Result(property = "dept",column = "deptno",
                              javaType = com.obtk.entity.Dept.class,
                              one = @One(select = "com.obtk.mapper.DeptMapper.findById"))})
    @Select("select * from emp where empno=#{empno}")
    Emp findByNo(@Param("empno") int empno);
    //查询单页数据
    List<Emp> findByPage(Map<String,Object> params);
    @Select("select count(empno) from emp")
    int findCount();

}
