package com.linkerlab.housemanager;

/**
 * 环境[environment]
 * @author rush_yu
 * @since 2015-12-24
 */
public class Env {
	/** 发布环境(线上运行) */
	public static final int ENV_RELEASE = 0x00;
	/** 开发环境 */
	public static final int ENV_DEV = 0x01;
	/** 测试环境(QA测试) */
	public static final int ENV_TEST = 0x02;

	private static int mCurEnv = ENV_RELEASE;//当前选择的环境，默认发布环境

	/***
	 * 仅在Application.onCreate方法开头设置不同环境
	 * @param env
	 */
	public static final void setEnv(int env) {
		mCurEnv = env;
	}

	/** 是否开发阶段 */
	public static final boolean isDevelop() {
		return mCurEnv == ENV_DEV;
	}

	/** 是否测试阶段 */
	public static final boolean isTest() {
		return mCurEnv == ENV_TEST;
	}

	/** 是否是发布阶段 */
	public static final boolean isRelease() {
		return mCurEnv == ENV_RELEASE;
	}
}
