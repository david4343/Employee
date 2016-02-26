package com.hybrid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.hybrid.model.Dept;

public class MybatisTest {

	static Log log =LogFactory.getLog(MybatisTest.class);
	
	public static void main(String[] args) throws SQLException {
//		test1();
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("spring/mybatis.xml");
		
		SqlSessionTemplate sqlSession = ctx.getBean(SqlSessionTemplate.class);
		
		
		//selectList의 인자 값 : Mapper XML의 내용 중 해당되는 namespace.selectID에 대한 쿼리
		List<Dept> depts = sqlSession.selectList("com.hybrid.mapper.DeptMapper.selectAll");
		
		for (Dept d : depts) {
			log.info(d.getDeptno() + " " + d.getDname() + " " + d.getLoc());
		}
		
	}
	
	//DataSource를 Test했던 것
	static void test1() throws SQLException {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("spring/mybatis.xml");
		
		DriverManagerDataSource ds = ctx.getBean(DriverManagerDataSource.class);
		
		log.info(ds.getUrl());
		log.info(ds.getUsername());
		log.info(ds.getPassword());
		
		Connection con = ds.getConnection();
		PreparedStatement pstmt = con.prepareStatement("select * from dept");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			log.info(rs.getString("deptno") + " " + rs.getString("daname"));
		}
		if (rs != null)	rs.close();
		if (pstmt != null) pstmt.close();
		if (con != null) con.close();
	}

}
