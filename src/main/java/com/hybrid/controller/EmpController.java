package com.hybrid.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hybrid.model.Emp;
import com.hybrid.service.EmpService;

@Controller
public class EmpController {	//Servlet 역할과 비슷하다.
	
	static Log log = LogFactory.getLog(EmpController.class);
	
	@Autowired
	EmpService empService;
	
	@RequestMapping(value="/emp/list/json")	//URL 설정
	@ResponseBody	//Spring이 json의 형태로 변환처리를 한다.
	public Map<String, Object> getList() {
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("message", "조회성공");
		
		result.put("data", empService.getList());
		return result;
	}
	
	@RequestMapping(value="/emp/list/xml", method=RequestMethod.GET)
	@ResponseBody
	public Emp getEmp(Integer empno) {
		log.info("getEmp() empno=" + empno);
		
		return empService.getEmp(empno);
	}
	
	@RequestMapping(value="/emp/list/xml", method=RequestMethod.POST)
	@ResponseBody
	public Emp postEmp(Emp emp) {
		log.info("empno = " + emp.getEmpno());
		log.info("ename = " + emp.getEname());
		log.info("job = " + emp.getJob());
		log.info("mgr = " + emp.getMgr());
		log.info("hiredate = " + emp.getHiredate());
		log.info("sal = " + emp.getSal());
		log.info("comm = " + emp.getComm());
		log.info("deptno = " + emp.getDeptno());
		
		empService.insert(emp);
		
		return emp;
	}
	
	@RequestMapping(value="/emp/list/xml", method=RequestMethod.DELETE)
	@ResponseBody
	public Emp deleteEmp(int empno) {
		log.info("empno = " + empno);

		return empService.deleteEmp(empno);
	}
	
	@RequestMapping(value="/emp/list/xml", method=RequestMethod.PUT)
	@ResponseBody
	public int updateDept(Emp emp) {
		log.info("update");
		log.info("empno = " + emp.getEmpno());
		log.info("ename = " + emp.getEname());
		log.info("job = " + emp.getJob());
		log.info("mgr = " + emp.getMgr());
		log.info("hiredate = " + emp.getHiredate());
		log.info("sal = " + emp.getSal());
		log.info("comm = " + emp.getComm());
		log.info("deptno = " + emp.getDeptno());
		return empService.update(emp);
	}

}
