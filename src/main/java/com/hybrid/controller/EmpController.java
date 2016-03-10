package com.hybrid.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hybrid.model.Dept;
import com.hybrid.model.Emp;
import com.hybrid.model.Mgr;
import com.hybrid.service.EmpService;

@Controller
public class EmpController {	//Servlet 역할과 비슷하다.
	
	static Log log = LogFactory.getLog(EmpController.class);
	
	@Autowired
	EmpService empService;
	
	@RequestMapping(value="/emp/list")	//URL 설정
	@ResponseBody	//Spring이 json의 형태로 변환처리를 한다.
	public Map<String, Object> getList() {
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("message", "조회성공");
		
		result.put("data", empService.getList());
		return result;
	}
	
	@RequestMapping(value="/emp", method=RequestMethod.GET)
	@ResponseBody
	public Emp getEmp(Integer empno) {
		log.info("getEmp() empno=" + empno);
		
		Emp emp = empService.getEmp(empno);
		//하드코딩용
//		Dept dept = new Dept();
//		dept.setDeptno(emp.getDeptno());
//		dept.setDname("총무부");
//		emp.setDept(dept);
		
		return emp;
	}
	
	@RequestMapping(value="/emp/mgrs")
	@ResponseBody
	public List<Mgr> getMgrs() {
		
		return empService.getMgrs();
	}
	
	@RequestMapping(value="/emp", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> postEmp(@RequestBody Emp emp) {
//		log.info("empno = " + emp.getEmpno());
//		log.info("ename = " + emp.getEname());
//		log.info("job = " + emp.getJob());
//		log.info("mgr = " + emp.getMgr());
//		log.info("hiredate = " + emp.getHiredate());
//		log.info("sal = " + emp.getSal());
//		log.info("comm = " + emp.getComm());
//		log.info("deptno = " + emp.getDeptno());
		log.info("emp = " + emp) ;
		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("message", "직원 추가 성공");
		try {
			empService.insert(emp);
		} catch(DuplicateKeyException ex) {
			response.put("success",	false);
			response.put("message", "직원 번호가 이미 존재합니다.");
		} catch(RuntimeException ex) {
			log.error("emp 추가 error", ex);
			response.put("success",	false);
			response.put("message", "직원을 추가할 수 없습니다.");
		}
		
		return response;
	}
	
	@RequestMapping(value="/emp", method=RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> deleteEmp(Integer empno) {
		log.info("empno = " + empno);
		Map<String, Object> response = new HashMap<>();
		
		try {
			empService.deleteEmp(empno);
			response.put("success", true);
			response.put("message", "직원 정보 삭제 성공");
		} catch (DataIntegrityViolationException ex) {
			response.put("success", false);
			response.put("message", "참조 무결성 제약 조건에 위반됩니다.");
		} catch (RuntimeException ex) {
			response.put("success", false);
			response.put("message", "직원정보 삭제 에러입니다. 확인하세요.");
		}
		
		return response;
	}
	
	@RequestMapping(value="/emp", method=RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> updateDept(@RequestBody Emp emp) {
		log.info("update");
		log.info("empno = " + emp.getEmpno());
		log.info("ename = " + emp.getEname());
		log.info("job = " + emp.getJob());
		log.info("mgr = " + emp.getMgr());
		log.info("hiredate = " + emp.getHiredate());
		log.info("sal = " + emp.getSal());
		log.info("comm = " + emp.getComm());
		log.info("deptno = " + emp.getDeptno());
		Map<String, Object> response = new HashMap<>();
		
		try {
			empService.update(emp);
			response.put("success", true);
			response.put("message", "직원정보 수정 성공");
		} catch (RuntimeException ex) {
			log.info(ex.getMessage());
			response.put("success", false);
			response.put("message", "직원정보 수정 에러입니다. 확인하세요.");	
		}

		return response;
	}

}
