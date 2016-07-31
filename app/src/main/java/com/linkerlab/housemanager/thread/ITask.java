package com.linkerlab.housemanager.thread;

/**
 * 异步任务
 * @author rush_yu
 * @since 2015-11-5
 */
interface ITask extends Runnable {
	/** 异步线程执行方法 */
	void onRun();
}
