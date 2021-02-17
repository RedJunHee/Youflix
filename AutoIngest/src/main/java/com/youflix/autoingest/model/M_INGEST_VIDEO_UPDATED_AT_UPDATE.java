package com.youflix.autoingest.model;

/**
 * @FileName : M_INGEST_VIDEO_REGSTER
 * @Project : INGEST
 * @Date : 2021.02.10
 * @Author : 조 준 희
 * @Description : 
 * @ModelParameters : 
 * @History :
 */

public class M_INGEST_VIDEO_UPDATED_AT_UPDATE  {


	private String YOUTUBER_ID;
	private String VIDEO_UPDATED_AT;
	
	
	
	public M_INGEST_VIDEO_UPDATED_AT_UPDATE(String yOUTUBER_ID, String vIDEO_UPDATED_AT) {
		super();
		YOUTUBER_ID = yOUTUBER_ID;
		VIDEO_UPDATED_AT = vIDEO_UPDATED_AT;
	}
	public String getYOUTUBER_ID() {
		return YOUTUBER_ID;
	}
	public void setYOUTUBER_ID(String yOUTUBER_ID) {
		YOUTUBER_ID = yOUTUBER_ID;
	}
	public String getVIDEO_UPDATED_AT() {
		return VIDEO_UPDATED_AT;
	}
	public void setVIDEO_UPDATED_AT(String vIDEO_UPDATED_AT) {
		VIDEO_UPDATED_AT = vIDEO_UPDATED_AT;
	}
	
	


}
