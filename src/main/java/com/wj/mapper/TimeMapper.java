package com.wj.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	@Select("Select sysdate() FROM dual")
	public String getTime();
	
	public String getTime2();
}
