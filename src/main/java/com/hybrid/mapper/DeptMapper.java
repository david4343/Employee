package com.hybrid.mapper;

import java.util.List;

import com.hybrid.model.Dept;

public interface DeptMapper {

	List<Dept> selectAll();				//결과가 여럿일 수 있기 때문에 List로 return하도록 정의했다.
	Dept selectByDeptno(int deptno);	//primary key 1개 값에 대한 결과를 return하므로 1개의 return값을 Dept로 정의했다.
	int insert(Dept dept);				//하나의 인스턴스에 대한 값을 입력해야하므로 parameter를 DTO로 정의했다.
	int updateByDeptno(Dept dept);		//하나의 인스턴스에 대한 값을 수정해야하므로 parameter를 DTO로 정의했다.
	int deleteByDeptno(int deptno);		//primary key로 인스턴스가 구별되므로 primary key에 대한 deptno만 parameter로 정의했다.
}
