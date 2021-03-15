package com.youflix.videoplaylistsync.model;

import java.io.Reader;
import java.io.Serializable;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


public class ResultMapType2 implements Map<String,Object>, Serializable {
	private static final long serialVersionUID = 4701628342002111517L;
	//-- Global Fields --//
	//
	private final Map<String,Object> data = new LinkedHashMap<String,Object>();
	//
	//-- Global Fields --//

    @Override
	public int size() {
        return this.data.size();        
    }
    
    @Override
	public boolean isEmpty() {    	
        return this.data.isEmpty();        
    }
    
    @Override
	public boolean containsKey(Object key) {    	
        return this.data.containsKey(key);        
    }
    
    @Override
	public boolean containsValue(Object value) {    	
        return this.data.containsValue(value);       
    }
    
    @Override
	public Object get(Object key) {    	
        return this.data.get(key);       
    }
    
    public String getString(String key) {    	
    	Object obj = this.data.get(key);    	
        return (obj == null ? "" : String.valueOf(obj));        
    }
    
    @Override
	public Object remove(Object key) {   	
        return this.data.remove(key);        
    }
    
    @Override
	public void clear() {    	
        this.data.clear();        
    }
    
    @Override
	public Set<String> keySet() {   	
        return this.data.keySet();       
    }
    
    @Override
    public void putAll(Map<? extends String, ? extends Object> map) {    	
        this.data.putAll(map);        
    }
    
    @Override
    public Set<Map.Entry<String, Object>> entrySet() {   	
        return this.data.entrySet();       
    }
    
    @Override
    public Collection<Object> values() { 	
        return this.data.values();      
    }
    
    @Override
    public String toString() {   	
        return this.data.toString();      
    }
    
	@Override
	public Object put(String key, Object value) {		
		key = key.toLowerCase();
		//
        Reader input  = null;
        String cValue = "";
        //
        if (value instanceof java.util.Date) {       	
        	value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value);        	
        }        
        if (value instanceof Clob) {        	
            try {           	
                Clob clob = (Clob) value;
                //
                StringBuilder output = new StringBuilder();
                //
                input = clob.getCharacterStream();
                //
                char[] buffer = new char[1024];
                //
                int byteRead;
                //
                while ((byteRead = input.read(buffer, 0, 1024)) != -1) {                	
                    output.append(buffer, 0, byteRead);                   
                }             
                cValue = output.toString();               
            } catch (Exception ex) {        	
                cValue = "Exception";
                //
                ex.printStackTrace();              
            } finally {
            	
                try {             	
                    if (input != null) {
                        input.close();
                    }                   
                } catch (Exception ex) {
                    
                	System.out.println("ResultMap Stream Close Error : " + ex.toString());
                	//
                	ex.printStackTrace();               	
                }             
            }        
            return data.put(key, cValue);  
        } 
        else if(value == null)
        {
        	return data.put(key, "");
        }
        else {
            return data.put(key, value);
        }
	}
}