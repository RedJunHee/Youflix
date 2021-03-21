package com.youflix.cust.model;

/**
 * @FileName : M_SIGN_IN
 * @Project : CUST
 * @Date : 2020.12.09
 * @Author : 조 준 희
 * @Description : 
 * @ModelParameters : 
 * @History :
 */

public class M_INGEST_SYNC_SUB_PLAY_LIST implements BaseModel{

	private String PLAY_LIST_ID;
	private String VIDEO_ID;
	
	public String getPLAY_LIST_ID() {
		return PLAY_LIST_ID;
	}
	public void setPLAY_LIST_ID(String pLAY_LIST_ID) {
		PLAY_LIST_ID = pLAY_LIST_ID;
	}
	public String getVIDEO_ID() {
		return VIDEO_ID;
	}
	public void setVIDEO_ID(String vIDEO_ID) {
		VIDEO_ID = vIDEO_ID;
	}
	
	public M_INGEST_SYNC_SUB_PLAY_LIST(String pLAY_LIST_ID, String vIDEO_ID) {
		super();
		PLAY_LIST_ID = pLAY_LIST_ID;
		VIDEO_ID = vIDEO_ID;
	}
	@Override
	public String paramCheck() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
