package com.hybrid.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hybrid.model.Dept;
import com.hybrid.service.DeptService;

@Controller
public class DeptController {	//Servlet 역할과 비슷하다.
	
	static Log log = LogFactory.getLog(DeptController.class);
	
	@Autowired
	DeptService deptService;
	
	@RequestMapping(value="/dept/list/json")	//URL 설정
	@ResponseBody	//Spring이 json의 형태로 변환처리를 한다.
	public List<Dept> getList() {
		
		return deptService.getList();
	}
	
	@RequestMapping(value="/dept/list/xml", method=RequestMethod.GET)
	@ResponseBody
	public Dept getDept(Integer deptno) {
		log.info("getDept() deptno=" + deptno);
		
		return deptService.getDept(deptno);
	}
	
	@RequestMapping(value="/dept/list/xml", method=RequestMethod.POST)
	@ResponseBody
	public Dept postDept(Dept dept) {
		log.info("deptno = " + dept.getDeptno());
		log.info("dname = " + dept.getDname());
		log.info("loc = " + dept.getLoc());
		
		deptService.insert(dept);
		
		return dept;
	}
	
	@RequestMapping(value="/dept/list/xml", method=RequestMethod.DELETE)
	@ResponseBody
	public Dept deleteDept(int deptno) {
		log.info("deptno = " + deptno);

		return deptService.deleteDept(deptno);
	}
	
	@RequestMapping(value="/dept/list/xml", method=RequestMethod.PUT)
	@ResponseBody
	public int updateDept(Dept dept) {
		log.info("update");
		log.info("123deptno = " + dept.getDeptno());
		log.info("123dname = " + dept.getDname());
		log.info("123loc = " + dept.getLoc());

		return deptService.update(dept);
	}

}
