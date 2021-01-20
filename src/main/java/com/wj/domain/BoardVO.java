package com.wj.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private Long id;
	private String writer;
	private String category;
	private String file;
	private String content;
	private Date regDate;
	private Date editDate;
}
