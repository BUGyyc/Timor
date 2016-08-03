package com.linkerlab.housemanager.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.linkerlab.housemanager.App;
import com.linkerlab.housemanager.R;
import com.linkerlab.housemanager.base.BaseActivity;
import com.linkerlab.housemanager.net.OkHttpClientManager;
import com.linkerlab.housemanager.thread.Task;
import com.linkerlab.housemanager.thread.UiTask;
import com.linkerlab.housemanager.util.Log;
import com.linkerlab.housemanager.util.Tool;

import net.tsz.afinal.annotation.view.ViewInject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;


/**
 * Created by Administrator on 2016/7/30.
 * wzc
 */

public class Login_Activity extends BaseActivity implements View.OnClickListener {

    @ViewInject(id = R.id.job_login)
    Button mbt_job;                      //工号登录
    @ViewInject(id = R.id.iphone_login)
    Button mbt_iphone;                  //手机登录
    @ViewInject(id = R.id.user_id)
    EditText met_user_id;               //工号 or 手机号
    @ViewInject(id = R.id.password)
    EditText met_password;              //密码 or 动态密码
    @ViewInject(id = R.id.get_auth_code)
    Button mbt_gat;                      //获取动态密码
    @ViewInject(id = R.id.login)
    Button mbt_login;                    //登录
    private String user_str;            //输入的帐号or iphone
    private String password_str;        //输入的密码or code
    private Context context = this;
    private String msg = null;           //获取动态验证码的提示
    private int MODEL = 0;               //登录模式
    private int JOB_LOGIN = 0;           //工号模式登录
    private int IPHONE_LOGIN = 1;        //手机号模式登录
    private SharedPreferences preferences;
    private Boolean token = false;
    private String returnData;

    @Override
    protected void initVariables() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mbt_job.setOnClickListener(this);
        mbt_iphone.setOnClickListener(this);
        met_user_id.setOnClickListener(this);
        met_password.setOnClickListener(this);
        mbt_gat.setOnClickListener(this);
        mbt_login.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.job_login:
                MODEL = JOB_LOGIN;
                mbt_job.setTextColor(getResources().getColor(R.color.white));
                mbt_job.setBackgroundResource(R.drawable.semi_round_button2);
                mbt_iphone.setTextColor(getResources().getColor(R.color.login_text));
                mbt_iphone.setBackgroundResource(R.color.transparency);
                mbt_gat.setVisibility(View.GONE);
                met_user_id.setHint(R.string.input_id);
                met_user_id.setInputType(InputType.TYPE_CLASS_TEXT);
                met_password.setHint(R.string.input_password);
                met_password.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case R.id.iphone_login:
                MODEL = IPHONE_LOGIN;
                mbt_job.setTextColor(getResources().getColor(R.color.login_text));
                mbt_job.setBackgroundResource(R.color.transparency);
                mbt_iphone.setTextColor(getResources().getColor(R.color.white));
                mbt_iphone.setBackgroundResource(R.drawable.semi_round_button2);
                mbt_gat.setVisibility(View.VISIBLE);
                met_user_id.setHint(R.string.input_iphone_number);
                met_user_id.setInputType(InputType.TYPE_CLASS_NUMBER);
                met_password.setHint(R.string.input_dynamic_code);
                met_password.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case R.id.get_auth_code:
                getauthcode();
                break;
            case R.id.login:
                login();
                break;
        }
    }

    /**
     * 登录
     */
    private void login() {
        user_str = met_user_id.getText().toString();           //得到输入框中用户名并转化成String
        password_str = met_password.getText().toString();      //得到输入框中密码并转化成String
        if (!TextUtils.isEmpty(user_str) && !TextUtils.isEmpty(password_str)) {
            if (MODEL == 0) {
                setToast("http://ricoo.linkerlab.net/api/users/login", new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("worker_number", user_str), new OkHttpClientManager.Param("password", password_str)});
           Log.d("wzc","工号模式登录");
            } else if (MODEL == 1) {
                setToast("http://ricoo.linkerlab.net/api/users/login", new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("mobile", user_str), new OkHttpClientManager.Param("code", password_str)});
                Log.d("wzc","手机号模式登录");
            }
        } else {
            Toast.makeText(context, "帐号密码不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取验证码
     */
    private void getauthcode() {
        user_str = met_user_id.getText().toString();           //得到输入框中用户名并转化成String
        password_str = met_password.getText().toString();      //得到输入框中密码并转化成String
        if (!TextUtils.isEmpty(user_str)) {
            setToast("http://ricoo.linkerlab.net/api/users/code", new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("mobile", user_str), new OkHttpClientManager.Param("code", password_str)});
        } else {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 弹出Toast提示
     */
    private void setToast(final String url, final OkHttpClientManager.Param... params) {
        new Task() {
            @Override
            public void onRun() {
                String returnData = null;
                try {
                    returnData = OkHttpClientManager.postAsString(url, params);
                    if (returnData == null) {
                        Toast.makeText(context, "网络出现问题", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    JSONObject jsonObject = new JSONObject(returnData);
                    if (jsonObject.has("token")) {
                        SharedPreferences.Editor editor = getSharedPreferences("house", MODE_PRIVATE).edit();
                        editor.putString("token", jsonObject.get("token").toString());
                        editor.putString("user_id", jsonObject.get("user_id").toString());
                        editor.putString("user_name", met_user_id.getText().toString());
                        editor.putString("password", met_password.getText().toString());
                        editor.putLong("time", System.currentTimeMillis());
                        editor.commit();
                        Log.d("wzc", "上一次登录时间 : " + System.currentTimeMillis());
                        Log.d("wzc", "token : " + jsonObject.get("token").toString());
                        new Tool().toNextDelete((Activity) context, MainActivity.class, null);
                        msg = "登录成功";
                    } else if (jsonObject.has("error") && "0".equals(jsonObject.getString("error"))) {
                        msg = "获取验证码成功";
                    } else if (jsonObject.has("message")) {
                        msg = jsonObject.getString("message");
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("wzc", "setToast()");
                }
            }
        }.execute();
    }
}
