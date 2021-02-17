package com.youflix.cust.dao.cms;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.youflix.cust.model.M_PLAY_VIDEO;
import com.youflix.cust.model.ResultMapType2;

@Mapper
public interface CMSDao {
	public List<ResultMapType2> Play_Video(M_PLAY_VIDEO mPlayVideo) throws Exception;
}
