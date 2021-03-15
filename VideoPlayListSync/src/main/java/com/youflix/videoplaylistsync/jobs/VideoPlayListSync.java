package com.youflix.videoplaylistsync.jobs;

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
import com.youflix.videoplaylistsync.model.ResultMapType2;
import com.youflix.videoplaylistsync.service.CMSService;
import com.youflix.videoplaylistsync.service.LOGService;
import com.youflix.videoplaylistsync.util.DateTime;



@Component
public class VideoPlayListSync {
	@Autowired
	CMSService cmsService;
	@Autowired
	LOGService logService;
	/*
    // 매일 5시 30분 0초에 실행한다.
//    @Scheduled(cron = "0 0 0 * * *")
//    public void Job() {

//    }

    // 매월 1일 0시 0분 0초에 실행한다.
//    @Scheduled(cron = "0 0 0 1 * *")
//    public void anotherJob() {
//
//        // 실행될 로직
//    }
*/
    // 애플리케이션 시작 후 60초 후에 첫 실행, 그 후 매 60초마다 주기적으로 실행한다.
	
    @Scheduled(initialDelay = 1000, fixedDelay = 3600000)
    public void Job() {
    	for(int i = 0 ; i< 10 ; i++)
    	{
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		System.out.println("ss");
    	}
	}
   
}
