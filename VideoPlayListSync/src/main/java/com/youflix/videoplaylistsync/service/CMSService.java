package com.youflix.videoplaylistsync.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youflix.videoplaylistsync.dao.cms.CMSDao;
import com.youflix.videoplaylistsync.model.ResultMapType2;

@Service
public class CMSService {

	@Autowired
	CMSDao cmsDao;
	
	
}
