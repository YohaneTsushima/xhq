package service;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 任务计划
 * @author chika
 */
public class JobSchedule {

	private static Scheduler schedule = null;
	private static final Object lock = new Object();
	
	/**
	 * 实例化JobScheduler
	 * @return
	 */
	public static Scheduler getSchedulerInstance(){
		if(schedule == null){
			synchronized (lock) {
				if(schedule == null){
					try {
						schedule = StdSchedulerFactory.getDefaultScheduler();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						return null;
					}
				}
			}
		}
		return schedule;
	}
	
	/**
	 * 添加工作任务
	 * @param jobName
	 * @param jobGroup
	 * @param triggerName
	 * @param jobClass
	 * @param cronExpress
	 */
	public void addJob(String jobName, String jobGroup, String triggerName, Class<? extends Job> jobClass, String cronExpress){
		try {
			JobDetail job = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroup).build();
			/*Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity(new TriggerKey(triggerName, jobGroup))
					.startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
					.build();*/
			CronTrigger cronTrigger = TriggerBuilder.newTrigger()
					.withIdentity(new TriggerKey(triggerName, jobGroup))
					.withSchedule(CronScheduleBuilder.cronSchedule(cronExpress))
					.build();
			JobSchedule.getSchedulerInstance().scheduleJob(job, cronTrigger);
			Thread.sleep(5L * 1000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 启动调度器
	 */
	public void startScheduler(){
		try {
			if(!JobSchedule.getSchedulerInstance().isStarted()){
				JobSchedule.getSchedulerInstance().start();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 停止调度器
	 */
	public void stopScheduler(){
		try {
			if(JobSchedule.getSchedulerInstance().isStarted()){
				JobSchedule.getSchedulerInstance().shutdown(true);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
