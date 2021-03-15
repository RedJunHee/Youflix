package com.youflix.getvideoplaylist.jobs;

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
import com.youflix.getvideoplaylist.model.M_INGEST_PLAY_LIST_REGSTER;
import com.youflix.getvideoplaylist.model.M_INGEST_PLAY_LIST_UPDATED_AT_UPDATE;
import com.youflix.getvideoplaylist.model.ResultMapType2;
import com.youflix.getvideoplaylist.service.CMSService;
import com.youflix.getvideoplaylist.service.LOGService;
import com.youflix.getvideoplaylist.util.DateTime;



@Component
public class GetVideoPlayList {
	@Autowired
	CMSService cmsService;
	@Autowired
	LOGService logService;
	/*
    // 매일 5시 30분 0초에 실행한다.
    @Scheduled(cron = "0 0 0 * * *")

    // 매월 1일 0시 0분 0초에 실행한다.
    @Scheduled(cron = "0 0 0 1 * *")

   // 애플리케이션 시작 후 60초 후에 첫 실행, 그 후 매 60초마다 주기적으로 실행한다.
   @Scheduled(initialDelay = 1000, fixedDelay = 3600000)
*/
    // 애플리케이션 시작 후 60초 후에 첫 실행, 그 후 매 60초마다 주기적으로 실행한다.
	
	//@Scheduled(cron = "0 0 10 * * *")
	@Scheduled(initialDelay = 1000, fixedDelay = 3600000)
    public void Job() {
		List<ResultMapType2> youtubers = null;
		try {
			
			youtubers = cmsService.GetYoububers();
			
		}
		catch(Exception e)
		{    		
			logService.WriteServiceLog("유튜버 조회", System.currentTimeMillis(), System.currentTimeMillis(), "GetVideoSubPlayList", 0, e.toString());
			return ;
		}
		
		
		for(int i = 0 ; i < youtubers.size() ; i++)
		{
			try {
				Thread.sleep(5000);
				String youtuberID = youtubers.get(i).get("youtuber_id").toString();
				String playListUpdatedAt = youtubers.get(i).get("play_list_updated_at").toString();
			    JsonArray playListItems = GetPlayListVideo(youtuberID).get("items").getAsJsonArray();
			    
			    String startTime = DateTime.getCurrentDateTimeToString();
			    StringBuffer sb ;
	
			    for(int itemsCount=0 ; itemsCount < playListItems.size(); itemsCount++)
			    {	    
			    	Thread.sleep(1000);
			    	sb = new StringBuffer();
				    sb.append(String.format("YOUTUBER_ID : %s", youtuberID));
				    sb.append(System.getProperty("line.separator"));
				    sb.append("{");
				    sb.append(System.getProperty("line.separator"));
				    Long sTime = System.currentTimeMillis();
				    
		            String publishAt = playListItems.get(itemsCount).getAsJsonObject().get("snippet").getAsJsonObject().get("publishedAt").getAsString() ;
		            
				    
				    if(!DateTime.compareDateToDate(publishAt.substring(0, publishAt.length()-1).replace('T', ' ') , playListUpdatedAt))
		            	continue;
				    
	    			String playListId = playListItems.get(itemsCount).getAsJsonObject().get("id").getAsString();
		            String title = playListItems.get(itemsCount).getAsJsonObject().get("snippet").getAsJsonObject().get("title").getAsString()  ;
		            
		            
		             cmsService.IngestPlayListRegster(new M_INGEST_PLAY_LIST_REGSTER(youtuberID, title, playListId));
	
		             Long eTime = System.currentTimeMillis();
		             sb.append(String.format("youtuber ID : %s\n, title : %s\n, playListID : %s\n"
		            		 , youtuberID, title, playListId));
		             sb.append("}");
	
		             logService.WriteServiceLog("유튜버 플레이 리스트 입수", sTime, eTime, "GetVideoPlayList", 1, sb.toString());
		             System.out.println(playListId + " " + title);
			    }
	
			    cmsService.UpdateToPlayListUpdatAt(new M_INGEST_PLAY_LIST_UPDATED_AT_UPDATE(youtuberID,startTime));
	
			   
				}
				catch(Exception e)
				{
					e.printStackTrace();
					logService.WriteServiceLog("GetYoutubeVideo", System.currentTimeMillis(), System.currentTimeMillis(), "유튜버 비디오 입수", 0, e.toString());
				}  
		}
	}
    private Integer PTTimeParseToInt(String PT) 
    {
        int temp;
        //PT문자열 삭제
        PT = PT.substring(2);
        String[] del =  {"H","M","S"};
        int[] time =  {0,0,0};

        for (int i = 0 ; i < 3 ; i++)
        {
          temp = PT.indexOf(del[i]);
          
          if(temp < 0)
            continue;
          
          for(int j = 0 ; j < temp  ; j++)
          {
            time[i] += (PT.charAt(j) - 48) * ( Math.pow( 10 , (temp -j-1) ) );
            
          }
      	
          if(temp >= 0)
          {
          	PT = PT.substring(PT.indexOf(del[i])+1);
          }
        }

        return time[0]*3600 + time[1]*60 + time[2];
    }
    
    private JsonObject GetPlayListVideo(String channelId) throws Exception
    {
		String playListRequestURL = 
		String.format("https://www.googleapis.com/youtube/v3/playlists?part=snippet,contentDetails&maxResults=50&channelId=%s&key=AIzaSyCi-S47mjwlbn4pENPMmexzuewKKvAX0_E"
				,channelId);//youtubers.get(i).get("CHANNEL_ID").toString());
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
}
