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
import com.hybrid.service.DeptService;

@Controller
public class DeptController {	//Servlet 역할과 비슷하다.
	
	static Log log = LogFactory.getLog(DeptController.class);
	
	@Autowired
	DeptService deptService;
	
	@RequestMapping(value="/dept/list")	//URL 설정
	@ResponseBody	//Spring이 json의 형태로 변환처리를 한다.
	public List<Dept> getList() {
		
		return deptService.getList();
	}
	
	@RequestMapping(value="/dept", method=RequestMethod.GET)
	@ResponseBody
	public Dept getDept(Integer deptno) {
		log.info("getDept() deptno=" + deptno);
		
		return deptService.getDept(deptno);
	}
	
	@RequestMapping(value="/dept", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> postDept(@RequestBody Dept dept) {	//@RequestBody는 Json으로 받은 객체를 Dept로 변환해주는 Annotation이다.
		log.info("deptno = " + dept.getDeptno());
		log.info("dname = " + dept.getDname());
		log.info("loc = " + dept.getLoc());
		Map<String, Object> response = new HashMap<>();
		
		try {
			deptService.insert(dept);
			response.put("success", true);
			response.put("message", "부서 정보 추가 성공");
		} catch (DuplicateKeyException ex) {
//			log.error("DB Error: 무결성 제약", ex);
//			log.info(ex.getMessage());
			response.put("success", false);
			response.put("message", "부서코드(PK)에 대해 무결성이 위반됐습니다. 다른 부서코드를 입력하세요.");
		}
		
		return response;
	}

//	내가 만든 update 부분.
//	@RequestMapping(value="/dept", method=RequestMethod.DELETE)
//	@ResponseBody
//	public Dept deleteDept(int deptno) {
//		log.info("deptno = " + deptno);
//
//		return deptService.deleteDept(deptno);
//	}


//	@RequestMapping(value="/dept", method=RequestMethod.PUT)
//	@ResponseBody
//	public Dept putDept(@RequestBody Dept dept) {
//		log.info("update");
//		log.info("123deptno = " + dept.getDeptno());
//		log.info("123dname = " + dept.getDname());
//		log.info("123loc = " + dept.getLoc());
//
//		return deptService.update(dept);
//	}
	
//	16.3.8 - 같이 새로 만든 update 부분 : POST방식 Method를 복붙하고 일부를 수정하여 만들었어. RequestMapping의 value 값을 나는 별도로 지정해서 넌 아마 수정 필요할거야(/dept/일듯).
	@RequestMapping(value="/dept", method=RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> putDept(@RequestBody Dept dept) {	//@RequestBody는 Json으로 받은 객체를 Dept로 변환해주는 Annotation!
		log.info("deptno = " + dept.getDeptno());
		log.info("dname = " + dept.getDname());
		log.info("loc = " + dept.getLoc());
		Map<String, Object> response = new HashMap<>();
		
		try {
			deptService.update(dept);	//insert에서 update로 바꿨어
			response.put("success", true);
			response.put("message", "부서 정보 수정 성공");	//알맞는 멘트로 수정했어.
		} catch (RuntimeException ex) {	//최초에 어떤 Exception을 던질지 몰라서 최초에 Runtime으로 지정했어.
			log.info(ex.getMessage());
			response.put("success", false);
			response.put("message", "부서수정 에러입니다. 확인하세요.");	//알맞는 멘트로 수정해따.
		}
		
		return response;
	}
	
//	16.3.8 - 같이 새로 만든 update 부분 : POST방식 Method를 복붙하고 일부를 수정하여 만들었어. RequestMapping의 value 값을 나는 별도로 지정해서 넌 아마 수정 필요할거야(/dept/일듯).
	@RequestMapping(value="/dept", method=RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> deleteDept(Integer deptno) {
		log.info("deptno = " + deptno);
		Map<String, Object> response = new HashMap<>();
		
		try {
			deptService.deleteDept(deptno);	//insert에서 update로 바꿨어
			response.put("success", true);
			response.put("message", "부서 정보 삭제 성공");	//알맞는 멘트로 수정했어.
		} catch (DataIntegrityViolationException ex) {
// 			외래키 참조무결성 제약 때문에 10, 20, 30, 40에 대한 삭제는 Exception을 발생시켜서 따로 처리해야해.
			response.put("success", false);
			response.put("message", "참조 무결성 제약 조건에 위반됩니다. \n해당 부서에 직원이 있는지 확인하세요.");	//알맞는 멘트로 수정해따.
		} catch (RuntimeException ex) {	//최초에 어떤 Exception을 던질지 몰라서 최초에 Runtime으로 지정했어.
//			log.info("error...", ex);	//Exception을 확인하기 위한 부분
			response.put("success", false);
			response.put("message", "부서삭제 에러입니다. 확인하세요.");	//알맞는 멘트로 수정해따.
		}
		
		return response;
	}
	
//	16.3.8 ViewResolver를 위한 메소드로 return type이 String이어야만 해. return하는 것은 논리적인 view이고.
//	☆☆☆☆☆WEB-INF 폴더에 dept폴더를 복사한 후 실행해야해.☆☆☆☆☆
//	localhost:8090/Employee/dept/listview를 호출하면 
//	아래의 ViewResolver beans_mvc.xml의 설정 때문에
//	<mvc:view-resolvers>
//		<mvc:jsp prefix="/WEB-INF/" suffix=".jsp"/>
//	</mvc:view-resolvers>
//	/Employee/WEB-INF/dept/list.jsp 경로를 탐색하게 되.
//	이전까진 "경로/파일이름.jsp"를 직접 호출했다면, 파일이름.jsp 안의 ajax 부분이 Spring을 통하여 해당경로로 접근했었어(ajax가 없다면 Spring 부분에 대한 접근이 불가능했겠지).
//	ViewResolver를 통하면 Spring의 Controller가 사용자가 요청한 논리적인 URL을 물리적인 URL로 변환하여 처리하게되
//	ViewResolver를 사용하면 서버에서 어떤 middleware(jsp, php 등)를 사용하는지 혹은 어떤 구조로 된 경로인지 알수가 없어. 다만 모든 URL에 대한 Mapping을 해야하므로 복잡할 수 있고.
//	다른말로는 jsp로 URL을 요청하면 그 안의 jsp파일에 작성한 ajax가 json을 요청, ViewResolver는 문서를 요청이라는 차이가 있다는거지.
	
	@RequestMapping(value="/dept/listview", method=RequestMethod.GET)
	public String getListView() {
		log.info("");
		return "dept/list";
	}

}
