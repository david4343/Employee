package com.hybrid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hybrid.mapper.DeptMapper;
import com.hybrid.model.Dept;

@Service	//이 Bean을 자동으로 등록
public class DeptService {
	
	//field 주입
	@Autowired	//아래의 타입이 있으면 자동으로 injection.
	DeptMapper deptMapper;
	
	@Transactional //Transaction을 시작.
	public List<Dept> getList() {
		
		deptMapper.selectAll();
		
		List<Dept> depts = deptMapper.selectAll();
		
		return depts;
	}
	
	@Transactional
	public Dept getDept(Integer deptno) {
		return deptMapper.selectByDeptno(deptno);
	}
	
	@Transactional
	public void insert(Dept dept) {
		deptMapper.insert(dept);
	}
	
	@Transactional
	public Dept deleteDept(Integer deptno) {
		Dept dept = deptMapper.selectByDeptno(deptno);
		deptMapper.deleteByDeptno(deptno);
		return dept;
	}
	
	@Transactional
	public Dept update(Dept dept) {
//		16.3.8 추가한 부분.
		int i = deptMapper.updateByDeptno(dept);
		Dept dept1 = deptMapper.selectByDeptno(dept.getDeptno());
		return dept1;
	}
	
}
