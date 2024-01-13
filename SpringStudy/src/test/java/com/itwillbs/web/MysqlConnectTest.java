package com.itwillbs.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

/**
 * MySQL 데이터베이스 연결을 테스트하기 위한 클래스
 */

public class MysqlConnectTest {
	// 디비 연결 정보
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/springdb";
	private static final String DBID = "root";
	private static final String DBPW = "1234";
	
	// @Test : 테스트하고자 하는 내용을 메서드 안에 작성 후 
	//         해당 어노테이션을 작성하면, JUnit이 해당 코드를 
	//         테스트용 코드로 인식하고 자동 실행 및 테스트 진행
	@Test
	public void testConnection() {
		try {
			// 1. 드라이버 로드
			Class.forName(DRIVER);
			System.out.println("드라이버 로드 성공!");
			
			// 2. DB 연결
			Connection con = 
			DriverManager.getConnection(DBURL, DBID, DBPW);
			System.out.println(" DB 연결 성공! ");
			System.out.println(" con : " + con);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				System.out.println("자원해제 코드 작성");
			}
			
			// try-with (1.7 ~ 사용) => try, catch, finally 한번에 처리하는 동작
			// => AutoClosable 인터페이스를 구현한 객체를 try() 안에 작성하면
			//    사용 후 자동으로 자원 해제 처리
			try (Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);){
				System.out.println("DB 연결 성공 2!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} 
	}
}

