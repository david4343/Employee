package com.hybrid.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.hybrid.model.Dept;

public class DeptMapperTest {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = null;
		ctx = new GenericXmlApplicationContext("classpath:spring/beans_db.xml");
		
		DeptMapper mapper = ctx.getBean(DeptMapper.class);
		
		List<Dept> depts = mapper.selectAll();
		
		for (Dept d : depts) {
			System.out.println(d.getDeptno() + " " + d.getDname() + " " + d.getLoc());
		}

		
		if(ctx!=null) ctx.close();
	}
	
	void test1() {
		
		GenericXmlApplicationContext ctx = null;
		ctx = new GenericXmlApplicationContext("classpath:spring/beans_db.xml");
		
		//해당하는 bean클래스가 한개일때만 parameter를 이와같이 한개만 작성 가능하다.
		SqlSessionTemplate sqlSession = ctx.getBean(SqlSessionTemplate.class);
		
		//id만으로도 호출 가능한데, 이 때는 Object로 반환하므로 downcasting이 필요하다.
//		SqlSessionTemplate sqlSession = (SqlSessionTemplate) ctx.getBean("sqlSessionTemplate");
		
		//id와 type을 둘 다 명시하여 표현도 가능하다.
//		SqlSessionTemplate sqlSession = ctx.getBean("sqlSessionTemplate", SqlSessionTemplate.class);
		
		
		//아래의 DeptMapper.class는 MyBatis가 DeptMapper.interface를 implements한다. 즉, 객체 생성은 MyBatis가 수행한다.
		//즉, Mapper interface가 없다면 다르게 작성해야한다. 아래아래 줄 참고.
		DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
		List<Dept> depts = deptMapper.selectAll();
		//Mapper Interface가 없다면 아래와 같이 전통적인 방식으로 작성해야한다.
		//Mapper Interface가 있으므로인해 작성시 오류를 잡을 수 있게 된다. 아래의 String은 실시간으로 오류 검사가 불가능.
		//또, Mapper Interface가 있으므로 인해 type을 정할 수 있다.
//		sqlSession.selectList("com.hybrid.mapper.DepMapper.selectAll");

		for (Dept d : depts) {
			System.out.println(d.getDeptno() + " " + d.getDname() + " " + d.getLoc());
		}
		
		
		if(ctx!=null) ctx.close();
	}

}
