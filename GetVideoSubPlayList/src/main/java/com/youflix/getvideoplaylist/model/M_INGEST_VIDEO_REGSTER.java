package com.youflix.getvideoplaylist.model;

/**
 * @FileName : M_INGEST_VIDEO_REGSTER
 * @Project : INGEST
 * @Date : 2021.02.10
 * @Author : 조 준 희
 * @Description : 
 * @ModelParameters : 
 * @History :
 */

public class M_INGEST_VIDEO_REGSTER  {

	private String VIDEO_ID;
	private String TITLE;
	private String VIDEO_TYPE;
	private String YOUTUBER_ID;
	private String DESCRIPTION;
	private String VIDEO_URL;
	private String PUBLISHED_AT;
	private String PICTURE_URL;
	private String TAGS;
	private String KIDS_YN;
	private String PLAY_YN;
	private String MAIN_PLAY_LIST;
	private Integer VIDEO_LENGTH;
	
	
	public M_INGEST_VIDEO_REGSTER(String vIDEO_ID, String tITLE, String vIDEO_TYPE, String yOUTUBER_ID,
			String dESCRIPTION, String vIDEO_URL, String pUBLISHED_AT, String pICTURE_URL, String tAGS, String kIDS_YN,
			String pLAY_YN, String mAIN_PLAY_LIST, Integer vIDEO_LENGTH) {
		super();
		VIDEO_ID = vIDEO_ID;
		TITLE = tITLE;
		VIDEO_TYPE = vIDEO_TYPE;
		YOUTUBER_ID = yOUTUBER_ID;
		DESCRIPTION = dESCRIPTION;
		VIDEO_URL = vIDEO_URL;
		PUBLISHED_AT = pUBLISHED_AT;
		PICTURE_URL = pICTURE_URL;
		TAGS = tAGS;
		KIDS_YN = kIDS_YN;
		PLAY_YN = pLAY_YN;
		MAIN_PLAY_LIST = mAIN_PLAY_LIST;
		VIDEO_LENGTH = vIDEO_LENGTH;
	}
	public String getMAIN_PLAY_LIST() {
		return MAIN_PLAY_LIST;
	}
	public void setMAIN_PLAY_LIST(String mAIN_PLAY_LIST) {
		MAIN_PLAY_LIST = mAIN_PLAY_LIST;
	}
	public String getVIDEO_ID() {
		return VIDEO_ID;
	}
	public void setVIDEO_ID(String vIDEO_ID) {
		VIDEO_ID = vIDEO_ID;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public String getVIDEO_TYPE() {
		return VIDEO_TYPE;
	}
	public void setVIDEO_TYPE(String vIDEO_TYPE) {
		VIDEO_TYPE = vIDEO_TYPE;
	}
	public String getYOUTUBER_ID() {
		return YOUTUBER_ID;
	}
	public void setYOUTUBER_ID(String yOUTUBER_ID) {
		YOUTUBER_ID = yOUTUBER_ID;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	public String getVIDEO_URL() {
		return VIDEO_URL;
	}
	public void setVIDEO_URL(String vIDEO_URL) {
		VIDEO_URL = vIDEO_URL;
	}
	public String getPUBLISHED_AT() {
		return PUBLISHED_AT;
	}
	public void setPUBLISHED_AT(String pUBLISHED_AT) {
		PUBLISHED_AT = pUBLISHED_AT;
	}
	public String getPICTURE_URL() {
		return PICTURE_URL;
	}
	public void setPICTURE_URL(String pICTURE_URL) {
		PICTURE_URL = pICTURE_URL;
	}
	public String getTAGS() {
		return TAGS;
	}
	public void setTAGS(String tAGS) {
		TAGS = tAGS;
	}
	public String getKIDS_YN() {
		return KIDS_YN;
	}
	public void setKIDS_YN(String kIDS_YN) {
		KIDS_YN = kIDS_YN;
	}
	public String getPLAY_YN() {
		return PLAY_YN;
	}
	public void setPLAY_YN(String pLAY_YN) {
		PLAY_YN = pLAY_YN;
	}
	public Integer getVIDEO_LENGTH() {
		return VIDEO_LENGTH;
	}
	public void setVIDEO_LENGTH(Integer vIDEO_LENGTH) {
		VIDEO_LENGTH = vIDEO_LENGTH;
	}


}
