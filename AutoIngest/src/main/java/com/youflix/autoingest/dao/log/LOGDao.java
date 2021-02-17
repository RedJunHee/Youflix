package com.youflix.autoingest.dao.log;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LOGDao {
	public void SendServiceLog(HashMap<String,String> params) throws Exception;
	
}
