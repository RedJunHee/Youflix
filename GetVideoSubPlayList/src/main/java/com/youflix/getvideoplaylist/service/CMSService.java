package com.youflix.getvideoplaylist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youflix.getvideoplaylist.dao.cms.CMSDao;
import com.youflix.getvideoplaylist.model.M_INGEST_VIDEO_UPDATED_AT_UPDATE;
import com.youflix.getvideoplaylist.model.ResultMapType2;

@Service
public class CMSService {

	@Autowired
	CMSDao cmsDao;
	
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

}
