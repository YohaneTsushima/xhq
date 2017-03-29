package service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.jfinal.aop.Duang;

public class TimeJob implements Job{

	/**
	 * 执行方法
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("任务开始:");
		System.out.println("任务执行");
		Duang.duang(UserServiceImpl.class).testQuartz();
		System.out.println("任务结束");
	}

	public TimeJob() {
		// TODO Auto-generated constructor stub
	}
}
