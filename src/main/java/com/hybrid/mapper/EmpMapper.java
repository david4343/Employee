package com.hybrid.mapper;

import java.util.List;

import com.hybrid.model.Dept;
import com.hybrid.model.Emp;

public interface EmpMapper {

	List<Emp> selectAll();
	Emp selectByEmpno(int empno);
	int insert(Emp emp);
	int updateByEmpno(Emp emp);
	int deleteByEmpno(int empno);
}
