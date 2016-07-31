package com.linkerlab.housemanager.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;

import android.os.Environment;
import android.text.TextUtils;

import com.linkerlab.housemanager.Env;


/**
 * 文件日志，用来记录一些很难发现的问题
 * @author rush_yu
 * @since 2016-03-28
 */
public class FileLog {
	private static DateFormat mFullDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);

	private static final String SD_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
	private static String mLogFilePath;
	private static String mErrorLogFilePath;

	/**
	 * 设置日志文件名
	 * @param logFileName eg:android_log
	 */
	public static void setLogFileName(String logFileName) {
		mLogFilePath = new StringBuilder(SD_ROOT)
			.append(File.separator)
			.append(logFileName)
			.toString();
	}

	/**
	 * 设置错误日志文件名
	 * @param errorLogFileName eg:android_error_2011-03-28
	 */
	public static void setErrorLogFileName(String errorLogFileName) {
		mErrorLogFilePath = new StringBuilder(SD_ROOT).append(File.separator)
			.append(errorLogFileName)
			.append(".log")
			.toString();
	}

	/**
	 * 记录日志到文件
	 * @param log
	 */
	public static void log(final String log) {
		if (Env.isRelease() || TextUtils.isEmpty(log)) {
			return;
		}

		if (TextUtils.isEmpty(mLogFilePath)) {
			mLogFilePath = new StringBuilder(SD_ROOT)
				.append(File.separator)
				.append("android_rush_yu.log")
				.toString();
		}
		log(mLogFilePath, log);
	}

	/**
	 * 记录错误日志到文件
	 * @param error
	 */
	public static void logError(final String error) {
		if (Env.isRelease() || TextUtils.isEmpty(error)) {
			return;
		}

		if (TextUtils.isEmpty(mErrorLogFilePath)) {
			mErrorLogFilePath = new StringBuilder(SD_ROOT).append(File.separator)
					.append("android_rush_yu_error_")
					.append(android.text.format.DateFormat.format("yyyy-MM-dd", System.currentTimeMillis()))
					.append(".log")
					.toString();
		}
		log(mErrorLogFilePath, error);
	}

	private static ArrayBlockingQueue<LogData> mLogDataQueue = new ArrayBlockingQueue<LogData>(20, true);

	private static void log(final String logFilePath, final String log) {
		if (Env.isRelease() || TextUtils.isEmpty(logFilePath) || TextUtils.isEmpty(log)) {
			return;
		}
		//判断是否存在SD卡
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			mLogDataQueue.add(new LogData(logFilePath, log));

			//启动一个线程记录日志
			synchronized (mLogDataQueue) {
				if (!FileLogThread.isRun) {
					FileLogThread.isRun = true;

					new FileLogThread().start();
				}
			}
		}
	}
	
	private static class LogData {
		String filePath;
		String log;
		LogData(String filePath, String log) {
			this.filePath = filePath;
			this.log = log;
		}
	}
	
	private static class FileLogThread extends Thread {
		static boolean isRun;
		public void run() {
			LogData logData;
			while (true) {
				try {
					logData = mLogDataQueue.take();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
					logData = null;
				}
				if (logData != null) {
					FileWriter fw = null;
					try {
						fw = new FileWriter(logData.filePath, true);
						fw.write(mFullDateFormat.format(System.currentTimeMillis()).toString());
						fw.write("\n");
						fw.write(logData.log);
						fw.write("\n\n");
						fw.flush();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (fw != null) {
							try {
								fw.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
}
