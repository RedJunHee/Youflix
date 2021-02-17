package com.youflix.autoingest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youflix.autoingest.dao.cms.CMSDao;
import com.youflix.autoingest.model.M_INGEST_VIDEO_REGSTER;
import com.youflix.autoingest.model.M_INGEST_VIDEO_UPDATED_AT_UPDATE;
import com.youflix.autoingest.model.ResultMapType2;

@Service
public class CMSService {

	@Autowired
	CMSDao cmsDao;
	
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
	
	
	public void UpdateToVideoUpdatAt(M_INGEST_VIDEO_UPDATED_AT_UPDATE dao) 
	{
		try {
			 cmsDao.UpdateToVideoUpdatAt(dao);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
	}
	
	public void IngestVideoRegster(M_INGEST_VIDEO_REGSTER dao)
	{
		try {
			 cmsDao.IngestVideoRegster(dao);
		
		} 
		catch(Exception e)
		{
			e.printStackTrace();

		}
	}
}
