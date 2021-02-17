package com.youflix.autoingest.jobs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.youflix.autoingest.model.M_INGEST_VIDEO_REGSTER;
import com.youflix.autoingest.model.M_INGEST_VIDEO_UPDATED_AT_UPDATE;
import com.youflix.autoingest.model.ResultMapType2;
import com.youflix.autoingest.service.CMSService;
import com.youflix.autoingest.service.LOGService;
import com.youflix.autoingest.util.DateTime;



@Component
public class GetYoutubeVideo {
	@Autowired
	CMSService cmsService;
	@Autowired
	LOGService logService;
    // 매일 5시 30분 0초에 실행한다.
//    @Scheduled(cron = "0 0 0 * * *")
//    public void Job() {
//    	List<ResultMapType2> youtubers = null;
//    	try {
//    		
//    		youtubers = cmsService.GetYoububers();
//    		
//    	}
//    	catch(Exception e)
//    	{    		
//    		logService.WriteServiceLog("GetYoububers", System.currentTimeMillis(), System.currentTimeMillis(), "유튜버 조회", 2, e.toString());
//    		return ;
//    	}
//    		
//		for(int i = 0 ; i < youtubers.size() ; i++)
//		{
//			try {
//				Thread.sleep(5000);
//				String youtuberID = youtubers.get(i).get("youtuber_id").toString();
//    			String uploadID = youtubers.get(i).get("upload_id").toString();
//    			String videoUpdatedAt = youtubers.get(i).get("video_updated_at").toString();
//    		    JsonArray playListItems = GetPlayListVideo(uploadID).get("items").getAsJsonArray();
//    		    
//    		    String startTime = DateTime.getCurrentDateTimeToString();
//    		    Long sTime = System.currentTimeMillis();
//    		    StringBuffer sb = new StringBuffer();
//    		    sb.append(String.format("YOUTUBER_ID : %s", youtuberID));
//    		    sb.append(System.getProperty("line.separator"));
//    		    sb.append("{");
//    		    sb.append(System.getProperty("line.separator"));
//    		    
//    		    for(int itemsCount=0 ; itemsCount < playListItems.size(); itemsCount++)
//    		    {	    			
//	    			String videoId = playListItems.get(itemsCount).getAsJsonObject().get("snippet").getAsJsonObject().get("resourceId").getAsJsonObject().get("videoId").getAsString() ;
//		            String title = playListItems.get(itemsCount).getAsJsonObject().get("snippet").getAsJsonObject().get("title").getAsString()  ;
//		            String description = playListItems.get(itemsCount).getAsJsonObject().get("snippet").getAsJsonObject().get("description").getAsString() ;
//		            String imageUrl = playListItems.get(itemsCount).getAsJsonObject().get("snippet").getAsJsonObject().get("thumbnails").getAsJsonObject().get("medium").getAsJsonObject().get("url").getAsString() ;
//		            String publishAt = playListItems.get(itemsCount).getAsJsonObject().get("snippet").getAsJsonObject().get("publishedAt").getAsString() ;
//		            
//		            if(!DateTime.compareDateToDate(publishAt.substring(0, publishAt.length()-1).replace('T', ' ') , videoUpdatedAt))
//		            	continue;
//		            JsonArray videoData;
//		            System.out.println(videoId);
//		            videoData = GetVideoDate(videoId).get("items").getAsJsonArray();
//		            
//		            String categoryId = videoData.get(0).getAsJsonObject().get("snippet").getAsJsonObject().get("categoryId").getAsString() ;
//		            String playYN = (videoData.get(0).getAsJsonObject().get("status").getAsJsonObject().get("embeddable").getAsBoolean())? "Y":"N" ;
//		            String isKids = (videoData.get(0).getAsJsonObject().get("status").getAsJsonObject().get("madeForKids").getAsBoolean())? "Y":"N"  ;
//		            StringBuffer tags = new StringBuffer(); 
//		            
//
//		            if(videoData.get(0).getAsJsonObject().get("snippet").getAsJsonObject().get("tags") != null)
//		            {
//		            JsonArray ja = videoData.get(0).getAsJsonObject().get("snippet").getAsJsonObject().get("tags").getAsJsonArray();
//		            
//		            for(int a = 0 ; a < ja.size();a++) {
//		            	if(a != 0) 
//		            		tags.append(",");
//	            		tags.append(ja.get(a).getAsString());
//            		}
//		            }
//		             cmsService.IngestVideoRegster(new M_INGEST_VIDEO_REGSTER(videoId, title, categoryId, youtuberID, description, "https://www.youtube.com/embed/"+videoId, publishAt, imageUrl,tags.toString(), isKids, playYN,uploadID));
//		             
//		             Long eTime = System.currentTimeMillis();
//		             sb.append(String.format("videoid : %s\n, title : %s\n, categoryID : %s\n,description : %s\n,videoURL : %s\n,publishAt : %s\n, imageURL : %s\n,isKids : %s\n, tags : %s\n"
//		            		 , videoId, title, categoryId, description, "https://www.youtube.com/embed/"+videoId, publishAt, imageUrl,tags.toString(), isKids, playYN,uploadID));
//		             sb.append("}");
//		             
//		             logService.WriteServiceLog("GetYoutubeVideo", sTime, eTime, "유튜버 비디오 입수", 1, sb.toString());
//    		    }
//    		    
//    		    cmsService.UpdateToVideoUpdatAt(new M_INGEST_VIDEO_UPDATED_AT_UPDATE(youtuberID,startTime));
//    		    
//    		    
//			}
//			catch(Exception e)
//			{
//				logService.WriteServiceLog("GetYoutubeVideo", System.currentTimeMillis(), System.currentTimeMillis(), "유튜버 비디오 입수", 2, e.toString());
//			}  
//		}
//    }

    // 매월 1일 0시 0분 0초에 실행한다.
//    @Scheduled(cron = "0 0 0 1 * *")
//    public void anotherJob() {
//
//        // 실행될 로직
//    }

    // 애플리케이션 시작 후 60초 후에 첫 실행, 그 후 매 60초마다 주기적으로 실행한다.
	
    @Scheduled(initialDelay = 1000, fixedDelay = 60000)
    public void Job() {
	List<ResultMapType2> youtubers = null;
	try {
		
		youtubers = cmsService.GetYoububers();
		
	}
	catch(Exception e)
	{    		
		logService.WriteServiceLog("GetYoububers", System.currentTimeMillis(), System.currentTimeMillis(), "유튜버 조회", 2, e.toString());
		return ;
	}
		
	for(int i = 0 ; i < youtubers.size() ; i++)
	{
		try {
			Thread.sleep(5000);
			String youtuberID = youtubers.get(i).get("youtuber_id").toString();
			String uploadID = youtubers.get(i).get("upload_id").toString();
			String videoUpdatedAt = youtubers.get(i).get("video_updated_at").toString();
		    JsonArray playListItems = GetPlayListVideo(uploadID).get("items").getAsJsonArray();
		    
		    String startTime = DateTime.getCurrentDateTimeToString();
		    StringBuffer sb = new StringBuffer();
		    sb.append(String.format("YOUTUBER_ID : %s", youtuberID));
		    sb.append(System.getProperty("line.separator"));
		    sb.append("{");
		    sb.append(System.getProperty("line.separator"));
		    
		    for(int itemsCount=0 ; itemsCount < playListItems.size(); itemsCount++)
		    {	    
		    	Thread.sleep(1000);
			    Long sTime = System.currentTimeMillis();

    			String videoId = playListItems.get(itemsCount).getAsJsonObject().get("snippet").getAsJsonObject().get("resourceId").getAsJsonObject().get("videoId").getAsString() ;
	            String title = playListItems.get(itemsCount).getAsJsonObject().get("snippet").getAsJsonObject().get("title").getAsString()  ;
	            String description = playListItems.get(itemsCount).getAsJsonObject().get("snippet").getAsJsonObject().get("description").getAsString() ;
	            String imageUrl = playListItems.get(itemsCount).getAsJsonObject().get("snippet").getAsJsonObject().get("thumbnails").getAsJsonObject().get("medium").getAsJsonObject().get("url").getAsString() ;
	            String publishAt = playListItems.get(itemsCount).getAsJsonObject().get("snippet").getAsJsonObject().get("publishedAt").getAsString() ;
	            
	            if(!DateTime.compareDateToDate(publishAt.substring(0, publishAt.length()-1).replace('T', ' ') , videoUpdatedAt))
	            	continue;
	            JsonArray videoData;
	            System.out.println(videoId);
	            videoData = GetVideoDate(videoId).get("items").getAsJsonArray();
	            
	            String categoryId = videoData.get(0).getAsJsonObject().get("snippet").getAsJsonObject().get("categoryId").getAsString() ;
	            String playYN = (videoData.get(0).getAsJsonObject().get("status").getAsJsonObject().get("embeddable").getAsBoolean())? "Y":"N" ;
	            String isKids = (videoData.get(0).getAsJsonObject().get("status").getAsJsonObject().get("madeForKids").getAsBoolean())? "Y":"N"  ;
	            StringBuffer tags = new StringBuffer(); 
	            

	            if(videoData.get(0).getAsJsonObject().get("snippet").getAsJsonObject().get("tags") != null)
	            {
	            JsonArray ja = videoData.get(0).getAsJsonObject().get("snippet").getAsJsonObject().get("tags").getAsJsonArray();
	            
	            for(int a = 0 ; a < ja.size();a++) {
	            	if(a != 0) 
	            		tags.append(",");
            		tags.append(ja.get(a).getAsString());
        		}
	            }
	             cmsService.IngestVideoRegster(new M_INGEST_VIDEO_REGSTER(videoId, title, categoryId, youtuberID, description, "https://www.youtube.com/embed/"+videoId, publishAt, imageUrl,tags.toString(), isKids, playYN,uploadID));
	             System.out.println('a');
	             Long eTime = System.currentTimeMillis();
	             sb.append(String.format("videoid : %s\n, title : %s\n, categoryID : %s\n,description : %s\n,videoURL : %s\n,publishAt : %s\n, imageURL : %s\n,isKids : %s\n, tags : %s\n"
	            		 , videoId, title, categoryId, description, "https://www.youtube.com/embed/"+videoId, publishAt, imageUrl,tags.toString(), isKids, playYN,uploadID));
	             sb.append("}");
	             
	             logService.WriteServiceLog("유튜버 비디오 입수", sTime, eTime, "GetYoutubeVideo", 1, sb.toString());
		    }
		    
		    cmsService.UpdateToVideoUpdatAt(new M_INGEST_VIDEO_UPDATED_AT_UPDATE(youtuberID,startTime));
		    System.out.println('b');
		    
		}
		catch(Exception e)
		{
			logService.WriteServiceLog("GetYoutubeVideo", System.currentTimeMillis(), System.currentTimeMillis(), "유튜버 비디오 입수", 0, e.toString());
		}  
	}
	}

    private JsonObject GetPlayListVideo(String playListId) throws Exception
    {
		String playListRequestURL = 
		String.format("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet,contentDetails&maxResults=50&playlistId=%s&key=AIzaSyCi-S47mjwlbn4pENPMmexzuewKKvAX0_E"
				,playListId);//youtubers.get(i).get("CHANNEL_ID").toString());
		URL url = new URL( playListRequestURL);
		HttpURLConnection request = (HttpURLConnection)url.openConnection();
		request.setRequestProperty("Content-Type", "application/json");
		request.setRequestProperty("Accept-Charset", "UTF-8");
		
		request.setConnectTimeout(2000);
		
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        
        StringBuffer result = new StringBuffer() ;
        String jsonData = null;
        while( (jsonData = br.readLine()) != null )
        {
        	result.append(jsonData);
        }
         
        Gson gson = new Gson();
        
        JsonObject jo = new JsonObject();
        
        jo = gson.fromJson(result.toString(),jo.getClass());
    	
        return jo;

    }
    
    private JsonObject GetVideoDate(String videoId) throws Exception
    {

		String RequestURL = 
		String.format("https://www.googleapis.com/youtube/v3/videos?part=snippet,contentDetails,status&id=%s&key=AIzaSyCi-S47mjwlbn4pENPMmexzuewKKvAX0_E"
				,videoId);
		URL url = new URL( RequestURL);
		HttpURLConnection request = (HttpURLConnection)url.openConnection();
		request.setRequestProperty("Content-Type", "application/json");
		request.setRequestProperty("Accept-Charset", "UTF-8");
		
		request.setConnectTimeout(2000);
		
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        
        StringBuffer result = new StringBuffer() ;
        String jsonData = null;
        while( (jsonData = br.readLine()) != null )
        {
        	result.append(jsonData);
        }
         
        Gson gson = new Gson();
        
        JsonObject jo = new JsonObject();
        
        jo = gson.fromJson(result.toString(),jo.getClass());
    	
        return jo;

    }
    
}
