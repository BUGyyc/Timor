package com.linkerlab.housemanager.thread;

import android.os.Handler;
import android.os.Looper;

/**
 * UI线程任务类<br>
 * 在异步线程执行完onRun()方法后，UI线程再执行onUiRun()方法
 * @author rush_yu
 * @since 2015-11-4
 */
public abstract class UiTask implements ITask {
	private static Handler sUiHandler = new Handler(Looper.getMainLooper());//用来执行UI方法

	@Override
	public final void run() {
		onRun();//异步执行

		sUiHandler.post(new Runnable() {
			@Override
			public void run() {
				onUiRun();//UI执行
			}
		});
	}

	/** UI线程执行方法 */
	public abstract void onUiRun();

	public final void execute() {
		TaskThread.getInstance().execute(this);
	}
}
