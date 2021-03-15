package com.youflix.getvideoplaylist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youflix.getvideoplaylist.dao.cms.CMSDao;
import com.youflix.getvideoplaylist.model.M_INGEST_PLAY_LIST_REGSTER;
import com.youflix.getvideoplaylist.model.M_INGEST_PLAY_LIST_UPDATED_AT_UPDATE;
import com.youflix.getvideoplaylist.model.ResultMapType2;

@Service
public class CMSService {

	@Autowired
	CMSDao cmsDao;
	
	public void UpdateToPlayListUpdatAt(M_INGEST_PLAY_LIST_UPDATED_AT_UPDATE dao) 
	{
		try {
			 cmsDao.UpdateToPlayListUpdatAt(dao);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
	}
	
	public List<ResultMapType2> GetYoububers ()
	{
		List<ResultMapType2> youtubers = null;
		try {
		 youtubers =  cmsDao.GetYoutubers();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
		
		return youtubers;
	}
	
	public void IngestPlayListRegster(M_INGEST_PLAY_LIST_REGSTER dao)
	{
		try {
			 cmsDao.IngestPlayListRegster(dao);
		
		} 
		catch(Exception e)
		{
			e.printStackTrace();

		}
	}
}
