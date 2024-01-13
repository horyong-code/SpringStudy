package com.itwillbs.web;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * DB 연결 테스트 (Spring - DataSource 사용)
 */

// @RunWith(SpringJUnit4ClassRunner.class)
// => 스프링으로 테스트하도록 세팅

// @ContextConfiguration(
//		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
//		)
// => 스프링에서 설정해놓은 파일의 정보를 불러오기

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class DataSourceTest {
	
	// 디비 연결 정보가 필요함 => 디비 연결 정보를 의존하고 있음 (의존 관계 - 주입)
	// 필요함 -> 변수 생성 -> 의존 관계 주입
	// @Inject : 의존 관계의 객체를 주입하는 어노테이션
	// @Inject
	@Autowired
	private DataSource ds;
	
	// @Test : 테스트하고자 하는 메서드에 반드시 작성
	@Test
	public void dataSourceTest() {
		System.out.println("의존 객체 주입 성공 여부 확인!");
		System.out.println(ds);
	}
	
	@Test
	public void 디비연결테스트() {
		try {
			// 1. 2. DB 연결
			Connection con = 
			ds.getConnection();
			System.out.println("DB 연결 성공!");
			System.out.println(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
