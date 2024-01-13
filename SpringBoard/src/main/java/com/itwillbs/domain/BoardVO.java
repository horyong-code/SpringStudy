package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardVO {
	
	// private int bno;
	private Integer bno; // 데이터 자체 : Integer
	private String title;
	private String content; // TEXT > VARCHAR -> String
	private String writer;
	private Timestamp regdate;
	private int viewcnt; // 연산 : int
	
}
