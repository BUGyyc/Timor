package com.linkerlab.housemanager.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 默认线程池执行器
 * @author rush_yu
 * @since 2015-11-4
 */
class DefaultThreadPoolExecutor {

	/**
	 * 创建线程池执行器
	 * @param threadPoolSize
	 * @param threadPriority
	 * @param keepAliveTime
	 * @return
	 */
	static Executor createExecutor(int threadPoolSize, int threadPriority, long keepAliveTime) {
		return new ThreadPoolExecutor(
				  threadPoolSize
				, threadPoolSize
				, keepAliveTime
				, TimeUnit.MILLISECONDS
				, new LinkedBlockingQueue<Runnable>()
				, new DefaultThreadFactory(threadPriority));
	}

	private static class DefaultThreadFactory implements ThreadFactory {
		/** 线程池Number */
		private static final AtomicInteger POOL_NO = new AtomicInteger(1);

		private final AtomicInteger threadNumber = new AtomicInteger(1);
		private final ThreadGroup group;
		private final String namePrefix;
		private final int threadPriority;

		DefaultThreadFactory(int threadPriority) {
			this.threadPriority = threadPriority;
			group = Thread.currentThread().getThreadGroup();
			namePrefix = String.format("pool-%d-thread-", POOL_NO.getAndIncrement());
		}

		@Override
		public Thread newThread(Runnable r) {
			String threadName = namePrefix + threadNumber.getAndIncrement();
			Thread t = new Thread(group, r, threadName, 0);
			if (t.isDaemon()) t.setDaemon(false);
			t.setPriority(threadPriority);
			return t;
		}
	}
}
