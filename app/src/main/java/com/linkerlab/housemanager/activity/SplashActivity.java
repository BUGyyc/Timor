package com.linkerlab.housemanager.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linkerlab.housemanager.R;
import com.linkerlab.housemanager.base.BaseActivity;
import com.linkerlab.housemanager.net.OkHttpClientManager;
import com.linkerlab.housemanager.thread.UiTask;
import com.linkerlab.housemanager.ui.FirstFragment;
import com.linkerlab.housemanager.util.Tool;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

/**
 * Created by x-man on 16/7/30.
 * 引导界面
 */
public class SplashActivity extends BaseActivity {
    RelativeLayout mRelativeLayout;
    private FragmentManager mFragmentManager;
    private SharedPreferences preferences;
    private Boolean token = false;
    private String returnData;
    private String msg = null;

    @Override
    protected void initVariables() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        preferences = getSharedPreferences("house", MODE_PRIVATE);
        long old_time = preferences.getLong("time", 0);
        long now_time = System.currentTimeMillis();
        if (now_time-old_time>=1000*60*60*3) {
            final String tokenStr = preferences.getString("token", null);
            new UiTask() {
                @Override
                public void onRun() {
                    try {
                        returnData = OkHttpClientManager.getToken("http://ricoo.linkerlab.net/api/users/check_token", tokenStr);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onUiRun() throws JSONException {
                    if (returnData != null) {
                        JSONObject jsonObject = new JSONObject(returnData);
                        if (jsonObject.has("is_valid")) {
                            if (jsonObject.getInt("is_valid") == 0) {
                                token = false;
                                msg = "token过期,请重新登录";
                                new Tool().toNextDelete(SplashActivity.this, Login_Activity.class, null);
                            } else if (jsonObject.getInt("is_valid") == 1) {
                                msg = "token有效";
                                token = true;
                                new Tool().toNextDelete(SplashActivity.this, MainActivity.class, null);
                            }
                        } else if (jsonObject.has("message")) {
                            msg = jsonObject.getString("message")+"请重新登录"; //token无效
                            new Tool().toNextDelete(SplashActivity.this, Login_Activity.class, null);
                        }
                        Toast.makeText(SplashActivity.this, msg, Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(SplashActivity.this, "网络出现问题", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }.execute();
        } else {
            new Tool().toNextDelete(SplashActivity.this, MainActivity.class, null);
        }

    }


    @Override
    protected void loadData() {

    }
}
