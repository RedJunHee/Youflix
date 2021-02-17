package com.youflix.cust.util;

import java.io.IOException;

public class StringUtil {


	/** 설명    : String배열 구분자로 조인
	 *  작성자 : 조준희 
	 *  비   고 : 2021-01- 29
	 * @param request
	 * @param response
	 * @throws 
	 */
	public static String String_Array_Join(String[] str , String del)
	{
	    String result = "";

	    for (int i = 0; i < str.length; i++) {
	      result += str[i];
	      if (i < str.length - 1) result += del;
	    }
	    return result;
	}
	
	
	/** 설명    : String 구분자별 Split => 콤마로 조인
	 *  작성자 : 조준희 
	 *  비   고 : 2021-01- 29
	 * @param request
	 * @param response
	 * @throws
	 */
    public static String String_Array_Query_Join(String[] idList, String del)
    {
    	String rtn = "";
        for (int i = 0; i < idList.length; i++)
        {
            if (i == 0)
                rtn += String.format("'%s'", idList[i]);
            else
            {
                if (!idList[i].isEmpty())
                    rtn += String.format(",'%s'", idList[i]);
            }

        }
        return rtn;
    }
    
	/** 설명    :Value 널 체크
	 *  작성자 : 조준희 
	 *  비   고 : 2021-01- 29
	 * @param rValue 체크 벨류
	 * @param nullValue null값 일 경우 사용 값
	 * @throws IOException
	 */
	public static String NVL(String rValue, String nullValue) {
		
		String result = "";
		if(rValue != null && rValue.length() > 0) {
			result = rValue.trim();
		} else {			
			if(nullValue != null && nullValue.length() > 0) {				
				result = nullValue;				
			} else {				
				result = "";				
			}			
		}		
		return result;		
	}
}
