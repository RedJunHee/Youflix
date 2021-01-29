package com.youflix.cust.util;

public final class Secure {
	

	public String sqlFilter(String str) {
		
		return sqlFilter(str, false);
		
	}
	
	
	public String sqlFilter(String str, boolean allowSpace) {
		
		if(str == null || str.length() <= 0)
			return "";
		
		//-- --//
		
		str = str.replaceAll("'","''");
		str = str.replaceAll("\"","\"\"");
		//str = str.replaceAll("\\","\\\\");
		str = str.replaceAll(";","");
		str = str.replaceAll("#","");
		str = str.replaceAll("--","");
		
		if(!allowSpace)
			str = str.replaceAll(" ","");
		
		//-- --//
		
		return (str);
		
	}
	
	

	public String clearXSS(String str) {
		
		return clearXSS(str, "");
		
	}
	
	

	public String clearXSS(String str, String avatag) {
		
		str = str.replaceAll("<","&lt;");
		str = str.replaceAll("\0","");
		

		if (!avatag.equals("")) {
			avatag.replaceAll(" ","");
			
			String [] st = avatag.split(",");
			

			for(int x = 0; x < st.length; x++ ) {
			
				str = str.replaceAll("&lt;"+st[x]+" ", "<"+st[x]+" ");
				str = str.replaceAll("&lt;"+st[x]+">", "<"+st[x]+">");
				str = str.replaceAll("&lt;/"+st[x], "</"+st[x]);
			
			}
			
		}
		
		return (str);
	
	}
	

	public String checkpath(String dn_path, String fname) {
		

		if((dn_path.indexOf("..\\") != -1) || (dn_path.indexOf("../") != -1)) {
			return "error";
		}
		

		if (dn_path.equals("")) {
			
		} else {
			dn_path = dn_path + "/";
		}
		
		String origfile = dn_path + fname;
		
		
		@SuppressWarnings("unused")
		String filename3 = fname.substring(fname.lastIndexOf('/') + 1);

		String filename4 = fname.substring(fname.lastIndexOf('\\') + 1);
		

		String FilePath = dn_path + filename4;
		

		if (origfile.equals(FilePath)) {
			return (FilePath);
		} else {
			return "error";
		}
		
	}
	
	

	public String checkext(String fileName, String avaExt) {
		
		String chkExt = "false";
		
		if (fileName.indexOf("\0") > -1) { chkExt = "false"; }
		
		String file_ext = fileName.substring(fileName.lastIndexOf('.') + 1);
		if(( file_ext.equalsIgnoreCase("jsp") || file_ext.equalsIgnoreCase("htm") || file_ext.equalsIgnoreCase("html")) ) {

			chkExt = "false";
		}
		

		if (!avaExt.equals("")) {

			avaExt.replaceAll(" ","");
			
			String compStr[] = avaExt.split(",");
			
			for (int i = 0;i < compStr.length; i++) {
				if (file_ext.equalsIgnoreCase(compStr[i])) {
					chkExt = "true";
				}
			}
			
		} else {
			chkExt = "true";
		}
		
		return chkExt;
		
	}

}
