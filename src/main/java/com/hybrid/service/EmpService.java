package com.hybrid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hybrid.mapper.EmpMapper;
import com.hybrid.model.Emp;
import com.hybrid.model.Mgr;

@Service	//이 Bean을 자동으로 등록
public class EmpService {
	
	//field 주입
	@Autowired	//아래의 타입이 있으면 자동으로 injection.
	EmpMapper empMapper;
	
	@Transactional //Transaction을 시작.
	public List<Emp> getList() {
		
		empMapper.selectAll();
		
		List<Emp> emps = empMapper.selectAll();
		
		return emps;
	}
	
	@Transactional
	public Emp getEmp(Integer empno) {
		return empMapper.selectByEmpno(empno);
	}
	

	@Transactional //Transaction을 시작.
	public List<Mgr> getMgrs() {
		
		List<Mgr> mgrs = empMapper.selectMgrs();
		
		return mgrs;
	}
	
	@Transactional
	public void insert(Emp emp) {
		empMapper.insert(emp);
	}
	
	@Transactional
	public Emp deleteEmp(Integer empno) {
		Emp emp = empMapper.selectByEmpno(empno);
		empMapper.deleteByEmpno(empno);
		return emp;
	}
	
	@Transactional
	public Emp update(Emp emp) {
		int i = empMapper.updateByEmpno(emp);
		Emp emp1 = empMapper.selectByEmpno(emp.getEmpno());
		return emp1;
	}
	
}
