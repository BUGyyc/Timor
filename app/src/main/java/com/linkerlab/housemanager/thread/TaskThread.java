package com.linkerlab.housemanager.thread;

import java.util.concurrent.Executor;

/**
 * 线程任务管理 器（单例类）
 * @author rush_yu
 * @since 2015-11-4
 */
public class TaskThread {
	/* 单例模式 */
	private static TaskThread self = new TaskThread();
	private TaskThread() {}
	public static TaskThread getInstance() {
		return self;
	}
	
	private Executor mTaskExecutor;//任务执行器

	public void init(Builder builder) {
		if (builder == null || !builder.isBuild()) {
			throw new IllegalArgumentException("builder didn't call a build method");
		}
		mTaskExecutor = builder.getExecutor();
	}

	/**
	 * 执行任务
	 * @param task
	 */
	public synchronized void execute(ITask task) {
		if (mTaskExecutor == null) {
			init(new Builder().build());//用默认值初始化该对象
		}
		mTaskExecutor.execute(task);
	}
}
