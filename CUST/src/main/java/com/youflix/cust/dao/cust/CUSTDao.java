package com.youflix.cust.dao.cust;

import java.util.List;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.youflix.cust.model.ResultMapType2;

@Mapper
public interface CUSTDao {
	public List<ResultMapType2>  GetUserData(HashMap<String, Object> params) throws Exception;
}
