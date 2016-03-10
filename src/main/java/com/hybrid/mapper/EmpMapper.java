package com.hybrid.mapper;

import java.util.List;

import com.hybrid.model.Emp;
import com.hybrid.model.Mgr;

public interface EmpMapper {

	List<Emp> selectAll();
	Emp selectByEmpno(int empno);
	List<Mgr> selectMgrs();
	int insert(Emp emp);
	int updateByEmpno(Emp emp);
	int deleteByEmpno(int empno);
}
