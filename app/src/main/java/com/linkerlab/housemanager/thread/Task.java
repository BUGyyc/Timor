package com.linkerlab.housemanager.thread;

/**
 * 异步执行任务类
 * @author rush_yu
 * @since 2015-11-4
 */
public abstract class Task implements ITask {

	@Override
	public final void run() {
		onRun();
	}

	public final void execute() {
		TaskThread.getInstance().execute(this);
	}
}
