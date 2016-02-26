package com.hybrid.model;

import java.util.Date;

public class Emp {
	//Nullable이란 것은 null의 값이 올 수 있으므로 primitive타입을 사용할 수 없다. 따라서 reference variable을 Wrapper type으로 선언해야한다.
	private int empno;
	private String ename;
	private String job;
	private Integer mgr;	//Nullable이기에 객체로 만들었다!
	private Date hiredate;
	private Double sal;		//Nullable이기에 객체로 만들었다!
	private Double comm;	//Nullable이기에 객체로 만들었다!
	private Integer depno;	//Nullable이기에 객체로 만들었다!
	
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
	public Integer getDepno() {
		return depno;
	}
	public void setDepno(Integer depno) {
		this.depno = depno;
	}
}
