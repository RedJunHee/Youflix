package com.youflix.cust.dao.cms;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.youflix.cust.model.M_INGEST_SYNC_SUB_PLAY_LIST;
import com.youflix.cust.model.M_PLAY_VIDEO;
import com.youflix.cust.model.ResultMapType2;

@Mapper
public interface CMSDao {
	public List<ResultMapType2> Play_Video(M_PLAY_VIDEO mPlayVideo) throws Exception;
	public List<ResultMapType2> Top_10_list() throws Exception; 
	public List<ResultMapType2> GetYoutuberPlayList() throws Exception;
	public void IngestPlayListVideoRegster(M_INGEST_SYNC_SUB_PLAY_LIST item) throws Exception;
	
}
