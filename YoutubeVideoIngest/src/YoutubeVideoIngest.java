import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

public class YoutubeVideoIngest {
	public static void main(String[] args) {
	}
	
	@Bean
	public TaskScheduler youtubeIngestSchduler()
	{
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(1);
		return taskScheduler;
	}
}
