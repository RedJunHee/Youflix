package com.youflix.autoingest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@EnableScheduling
@SpringBootApplication
public class AutoIngestApplication {

	public static void main(String[] args) {
		 ApplicationContext ctx = SpringApplication.run(AutoIngestApplication.class, args);
	}
	


	// 단일 쓰레드
//	@Bean
//	public TaskScheduler youtubeIngestSchduler()
//	{
//		return new ConcurrentTaskScheduler();
//	}

	@Bean
	public TaskScheduler youtubeIngestSchduler()
	{
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(1);
		return taskScheduler;
	}
}
