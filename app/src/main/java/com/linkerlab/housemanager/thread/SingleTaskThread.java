package com.linkerlab.housemanager.thread;

import java.util.HashMap;
import java.util.concurrent.Executor;

/**
 * 单任务线程管理 器（单例类）
 * @author rush_yu
 * @since 2016-04-29
 */
public class SingleTaskThread {
	/* 单例模式 */
	private static SingleTaskThread self = new SingleTaskThread();
	private SingleTaskThread() {}
	public static SingleTaskThread getInstance() {
		return self;
	}

	private final String DEFAULT_GROUP = "default-group";

	private HashMap<String, Executor> mExecutorMap = new HashMap<String, Executor>();

	/**
	 * 执行任务
	 * @param task
	 */
	public synchronized void execute(ITask task) {
		execute(DEFAULT_GROUP, task);
	}

	/**
	 * 执行任务
	 * <p>相同组名使用同一个线程执行，不同组名开启不同线程执行
	 * @param group 组名
	 * @param task
	 */
	public synchronized void execute(String group, ITask task) {
		if (group == null) group = DEFAULT_GROUP;

		if (!mExecutorMap.containsKey(group)) {
			Builder builder = new Builder();
			builder.setThreadPoolSize(1);
			builder.build();
			mExecutorMap.put(group, builder.getExecutor());
		}
		mExecutorMap.get(group).execute(task);
	}
}
