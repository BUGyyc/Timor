package com.linkerlab.housemanager.thread;

/**
 * 异步执行单任务类
 * @author rush_yu
 * @since 2016-04-29
 */
public abstract class SingleTask implements ITask {

	@Override
	public final void run() {
		onRun();
	}

	public final void execute() {
		SingleTaskThread.getInstance().execute(this);
	}

	/**
	 * 相同组名使用同一个线程执行，不同组名开启不同线程执行
	 * @param group 组名
	 */
	public final void execute(String group) {
		SingleTaskThread.getInstance().execute(group, this);
	}
}
