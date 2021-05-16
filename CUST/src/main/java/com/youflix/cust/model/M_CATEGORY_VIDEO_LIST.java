package com.youflix.cust.model;

/**
 * @FileName : M_CATEGORY_VIDEO_LIST
 * @Project : CUST
 * @Date : 2020.12.09
 * @Author : 조 준 희
 * @Description : 
 * @ModelParameters : 
 * @History :
 */

public class M_CATEGORY_VIDEO_LIST implements BaseModel{

	private String VIDEO_TYPE;
	private String START ; // 페이지 수
	
	
	public M_CATEGORY_VIDEO_LIST(String vIDEO_TYPE, String sTART) {
		super();
		VIDEO_TYPE = vIDEO_TYPE;
		if(sTART == null)
			sTART = "1";
		
		START = sTART;
	}


	public String getVIDEO_TYPE() {
		return VIDEO_TYPE;
	}


	public void setVIDEO_TYPE(String vIDEO_TYPE) {
		VIDEO_TYPE = vIDEO_TYPE;
	}


	public String getSTART() {
		return START;
	}


	public void setSTART(String sTART) {
		START = sTART;
	}


	@Override
	public String paramCheck() {
		// TODO Auto-generated method stub
		if ( VIDEO_TYPE == null || VIDEO_TYPE.length() == 0)
			return "VIDEO_TYPE is null";

		return null;
	}
	

}
