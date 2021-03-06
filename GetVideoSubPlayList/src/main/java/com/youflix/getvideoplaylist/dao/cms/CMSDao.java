package com.youflix.getvideoplaylist.dao.cms;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.youflix.getvideoplaylist.model.M_INGEST_PLAY_LIST_REGSTER;
import com.youflix.getvideoplaylist.model.M_INGEST_PLAY_LIST_UPDATED_AT_UPDATE;
import com.youflix.getvideoplaylist.model.ResultMapType2;


@Mapper
public interface CMSDao {
	public List<ResultMapType2> GetYoutubers ();
	public void UpdateToPlayListUpdatAt(M_INGEST_PLAY_LIST_UPDATED_AT_UPDATE dao);
	public void IngestPlayListRegster(M_INGEST_PLAY_LIST_REGSTER dao);
}


// mapper ( 쿼리문 ) - Dao ( 인터페이스 ) - Service ( 실제 사용하는 서비스 객체 )