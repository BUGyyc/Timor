package com.linkerlab.housemanager.util;


import com.linkerlab.housemanager.Env;

/**
 * 打印日志
 * @author rush_yu
 * @since 2015-10-27
 */
public class Log {
	public static final int VERBOSE = 0x0001;
	public static final int DEBUG = 0x0002;
	public static final int INFO = 0x0004;
	public static final int WARN = 0x0008;
	public static final int ERROR = 0x0010;
	/** 不显示LOG */
	public static final int LEVEL_NONE = 0x0000;
	/** 显示全部LOG */
	public static final int LEVEL_ALL = VERBOSE | DEBUG | INFO | WARN | ERROR;

	private static int LEVEL = LEVEL_NONE;
	
	private static String TAG = "PYH";
	
	public static final void setTag(String tag) {
		TAG = tag;
	}
	
	public static final void setLevel(int level) {
		LEVEL = level;
	}
	
	private static boolean check(int level) {
		if ((LEVEL & level) > 0) {
			return true;
		}
		return false;
	}
	
	public static void v(String text) {
		if (check(VERBOSE)) android.util.Log.v(TAG, text == null ? "" : text);
	}
	
	public static void v(String tag, String text) {
		if (check(VERBOSE)) android.util.Log.v(tag, text == null ? "" : text);
	}
	
	public static void d(String text) {
		if (check(DEBUG)) android.util.Log.d(TAG, text == null ? "" : text);
	}

	public static void d(String text, Throwable tr) {
		if (check(DEBUG)) android.util.Log.d(TAG, text == null ? "" : text, tr);
	}
	
	public static void d(String tag, String text) {
		if (check(DEBUG)) android.util.Log.d(tag, text == null ? "" : text);
	}

	public static void d(String tag, String text, Throwable tr) {
		if (check(DEBUG)) android.util.Log.d(tag, text == null ? "" : text, tr);
	}

	public static void i(String text) {
		if (check(INFO)) android.util.Log.i(TAG, text == null ? "" : text);
	}

	public static void i(String text, Throwable tr) {
		if (check(INFO)) android.util.Log.i(TAG, text == null ? "" : text, tr);
	}

	public static void i(String tag, String text) {
		if (check(INFO)) android.util.Log.i(tag, text == null ? "" : text);
	}
	
	public static void i(String tag, String text, Throwable tr) {
		if (check(INFO)) android.util.Log.i(tag, text == null ? "" : text, tr);
	}
	
	public static void w(String text) {
		if (check(WARN)) android.util.Log.w(TAG, text == null ? "" : text);
	}
	
	public static void w(String text, Throwable tr) {
		if (check(WARN)) android.util.Log.w(TAG, text == null ? "" : text, tr);
	}
	
	public static void w(String tag, String text) {
		if (check(WARN)) android.util.Log.w(tag, text == null ? "" : text);
	}
	
	public static void w(String tag, String text, Throwable tr) {
		if (check(WARN)) android.util.Log.w(tag, text == null ? "" : text, tr);
	}
	
	public static void e(String text) {
		if (check(ERROR)) android.util.Log.e(TAG, text == null ? "" : text);
		logFile(text);
	}
	
	public static void e(String text, Throwable tr) {
		if (check(ERROR)) android.util.Log.e(TAG, text == null ? "" : text, tr);
		logFile(text);
	}
	
	public static void e(String tag, String text) {
		if (check(ERROR)) android.util.Log.e(tag, text == null ? "" : text);
		logFile(text);
	}
	
	public static void e(String tag, String text, Throwable tr) {
		if (check(ERROR)) android.util.Log.e(tag, text == null ? "" : text, tr);
		logFile(text);
	}
	
	private static void logFile(String error) {
		if (Env.isTest()) {//测试阶段
			FileLog.log(error);
		}
	}

    public static String getStackTraceString(Throwable tr) {
        return android.util.Log.getStackTraceString(tr);
    }

    public static int println(int priority, String tag, String msg) {
        return android.util.Log.println(priority, tag == null ? "" : tag, msg == null ? "" : msg);
    }
}
