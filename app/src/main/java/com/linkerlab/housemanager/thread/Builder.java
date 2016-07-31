package com.linkerlab.housemanager.thread;

import java.util.concurrent.Executor;

/**
 * 构建器
 * @author rush_yu
 * @since 2015-11-4
 */
public class Builder {
	/** 默认线程池大小 */
	private static final int DEFAULT_THREAD_POOL_SIZE = 3;

	private int mThreadPoolSize;
	private int mThreadPriority;
	private Executor mExecutor;
	private boolean mIsBuild;

	/**
	 * 是否调用了构建方法
	 * @return
	 */
	public boolean isBuild() {
		return mIsBuild;
	}

	/**
	 * 设置线程池大小
	 * @param threadPoolSize
	 * @return Builder
	 */
	public Builder setThreadPoolSize(int threadPoolSize) {
		if (!mIsBuild) {
			this.mThreadPoolSize = threadPoolSize;
		}
		return this;
	}

	/**
	 * 设置线程优先级
	 * @param threadPriority
	 * @return Builder
	 */
	public Builder setThreadPriority(int threadPriority) {
		if (!mIsBuild) {
			if (mThreadPriority < Thread.MIN_PRIORITY) {
				this.mThreadPriority = Thread.MIN_PRIORITY;
			} else {
				if (mThreadPriority > Thread.MAX_PRIORITY) {
					this.mThreadPriority = Thread.MAX_PRIORITY;
				} else {
					this.mThreadPriority = threadPriority;
				}
			}
		}
		return this;
	}

	/**
	 * 设置任务执行器
	 * @param taskExecutor
	 * @return Builder
	 */
	public Builder setExecutor(Executor taskExecutor) {
		if (!mIsBuild) {//构建之后不可再重置
			this.mExecutor = taskExecutor;
		}
		return this;
	}

	/**
	 * 获取任务执行器
	 * @return
	 */
	public Executor getExecutor() {
		return mExecutor;
	}

	/**
	 * 构建Builder对象
	 * @return Builder
	 */
	public Builder build() {
		mIsBuild = true;
		if (mThreadPoolSize <= 0) {
			mThreadPoolSize = DEFAULT_THREAD_POOL_SIZE;
		}
		if (mThreadPriority <= 0) {
			mThreadPriority = Thread.NORM_PRIORITY - 2;
		}
		if (mExecutor == null) {
			mExecutor = DefaultThreadPoolExecutor.createExecutor(mThreadPoolSize, mThreadPriority, 0);
		}
		return this;
	}
}
