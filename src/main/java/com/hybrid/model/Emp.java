package com.hybrid.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Emp {
	//Nullable이란 것은 null의 값이 올 수 있으므로 primitive타입을 사용할 수 없다. 따라서 reference variable을 Wrapper type으로 선언해야한다.
	private int empno;
	private String ename;
	private String job;
	private Integer mgr;	//Nullable이기에 객체로 만들었다!
//	@JsonFormat(pattern = "yyyy-MM-dd")	//Date의 표기방식을 설정하는 부분이다.
	private Date hiredate;
	private Double sal;		//Nullable이기에 객체로 만들었다!
	private Double comm;	//Nullable이기에 객체로 만들었다!
	private Integer deptno;	//Nullable이기에 객체로 만들었다!
	private Dept dept;		//1:1관계이기 때문에 Dept를 사용. Mapper.xml파일에 association으로 resultMApping을 해줘야한다.
//	만약 1:n이었다면 List<Dept> depts로 만들어야하고, Mapper.xml파일에 collection으로 resultMapping을 해줘야한다.
	
	@Override
	public String toString() {
		//JsonMapper
		ObjectMapper mapper = new ObjectMapper();
		String value=null;
		try {
			value = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Integer getMgr() {
		return mgr;
	}
	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public Double getSal() {
		return sal;
	}
	public void setSal(Double sal) {
		this.sal = sal;
	}
	public Double getComm() {
		return comm;
	}
	public void setComm(Double comm) {
		this.comm = comm;
	}
	public Integer getDeptno() {
		return deptno;
	}
	public void setDeptno(Integer depno) {
		this.deptno = depno;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
}
