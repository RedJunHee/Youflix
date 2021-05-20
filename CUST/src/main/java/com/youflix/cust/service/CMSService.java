package com.youflix.cust.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youflix.cust.dao.cms.CMSDao;
import com.youflix.cust.dao.cust.CUSTDao;
import com.youflix.cust.model.M_CATEGORY_VIDEO_LIST;
import com.youflix.cust.model.M_CHECK_CUST_EMAIL_DUPLICATE;
import com.youflix.cust.model.M_INGEST_SYNC_SUB_PLAY_LIST;
import com.youflix.cust.model.M_PLAY_VIDEO;
import com.youflix.cust.model.M_POPULAR_TYPE_LIST;
import com.youflix.cust.model.M_SESSION_CHECK;
import com.youflix.cust.model.M_LOG_IN;
import com.youflix.cust.model.M_SIGN_UP;
import com.youflix.cust.model.ResultMapType2;


@Service
public class CMSService extends BaseService{

//   @Autowired
//   private LogService logService;
//    
	@Autowired
	private CMSDao cmsDao;

	/**
	 * @FileName : 컨텐츠 재생 (Play_Video)
	 * @Project : CMS
	 * @Date : 2021.02.07
	 * @Author : 조 준 희
	 * @Description : 컨텐츠 재생
	 * @History :
	 */
	public HashMap<String, Object> Play_Video(M_PLAY_VIDEO mPlayVideo) throws Exception {	

		List<ResultMapType2> result = null ;
		
		try {
			result = cmsDao.Play_Video(mPlayVideo);
			
			if( result.size() > 0)
				return ResponseDatatoController(200, result);
			
			return ResponseDatatoController(301, "");

		} catch (Exception ex) {
			throw ex;
		}
	}
	/**
	 * @FileName : 로그아웃 (Log_Out)
	 * @Project : CMS
	 * @Date : 2021.02.07
	 * @Author : 조 준 희
	 * @Description : 로그아웃
	 * @History :
	 */
	public HashMap<String, Object> Top_10_list() throws Exception {	

		List<ResultMapType2> result ;
		try {
			result = cmsDao.Top_10_list();

			return ResponseDatatoController(200, result);
			
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * @FileName : 유튜버 SubPlayList 가져오기 (GetYoutuberPlayList)
	 * @Project : CMS
	 * @Date : 2021.03.15
	 * @Author : 조 준 희
	 * @Description : 유튜버 SubPlayList 가져오기
	 * @History :
	 */
	public List<ResultMapType2> GetYoutuberPlayList() throws Exception {	

		List<ResultMapType2> result ;
		try {
			result = cmsDao.GetYoutuberPlayList();

			return result;
			
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	
	/**
	 * @FileName : 카테고리 비디오박스 리스트 (CategoryVideoList)
	 * @Project : CMS
	 * @Date : 2021.03.15
	 * @Author : 조 준 희
	 * @Description : 카테고리 비디오박스 리스트
	 * @History :
	 */
	public HashMap<String, Object>  CategoryVideoList(M_CATEGORY_VIDEO_LIST mCategoryVideoList) throws Exception {	

		List<ResultMapType2> result ;
		try {
			result = cmsDao.CategoryVideoList(mCategoryVideoList);
			return ResponseDatatoController(200, result);

			
		} catch (Exception ex) {
			throw ex;
		}
	}	
	
	/**
	 * @FileName : 유저별 인기있는 비디오타입 리스트 (PopularTypeList)
	 * @Project : CMS
	 * @Date : 2021.03.15
	 * @Author : 조 준 희
	 * @Description : 유저별 인기있는 비디오타입 리스트
	 * @History :
	 */
	public HashMap<String, Object>  PopularTypeList(M_POPULAR_TYPE_LIST mPopularTypeList) throws Exception {	

		List<ResultMapType2> result ;
		try {
			result = cmsDao.PopularTypeList(mPopularTypeList);
			return ResponseDatatoController(200, result);

			
		} catch (Exception ex) {
			throw ex;
		}
	}	
	
	/**
	 * @FileName : 시청중인 비디오 리스트 (PopularTypeList)
	 * @Project : CMS
	 * @Date : 2021.05.20
	 * @Author : 조 준 희
	 * @Description : 시청중인 비디오 리스트
	 * @History :
	 */
	public HashMap<String, Object>  VideoKeepWatching(String CUST_EMAIL) throws Exception {	

		List<ResultMapType2> result ;
		try {
			result = cmsDao.VideoKeepWatching(CUST_EMAIL);
			return ResponseDatatoController(200, result);

			
		} catch (Exception ex) {
			throw ex;
		}
	}	
	
	
	/**
	 * @FileName : 로그아웃 (Log_Out)
	 * @Project : CMS
	 * @Date : 2021.02.07
	 * @Author : 조 준 희
	 * @Description : 로그아웃
	 * @History :
	 */
	public void IngestPlayListVideoRegster(M_INGEST_SYNC_SUB_PLAY_LIST item) throws Exception {	

		try {
			cmsDao.IngestPlayListVideoRegster(item);

		} catch (Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * @FileName : Youflix 메인 추천 비디오 박스 (RecommendVideo)
	 * @Project : CMS
	 * @Date : 2021.05.20
	 * @Author : 조 준 희
	 * @Description : Youflix 메인 추천 비디오 박스
	 * @History :
	 */
	public HashMap<String, Object>  RecommendVideo() throws Exception {	

		List<ResultMapType2> result ;
		try {
			result = cmsDao.RecommendVideo();
			return ResponseDatatoController(200, result);

			
		} catch (Exception ex) {
			throw ex;
		}
	}
}

