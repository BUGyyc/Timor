package com.linkerlab.housemanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.linkerlab.housemanager.R;
import com.linkerlab.housemanager.base.BaseActivity;
import com.linkerlab.housemanager.util.Tool;
import com.linkerlab.housemanager.util.Uploading_Dialog;

import net.tsz.afinal.annotation.view.ViewInject;

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
    private String user_str;
    private String password_str;

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
        user_str = met_user_id.getText().toString();
        password_str = met_password.getText().toString();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.job_login:
                mbt_job.setTextColor(getResources().getColor(R.color.white));
                mbt_job.setBackgroundResource(R.drawable.semi_round_button2);
                mbt_iphone.setTextColor(getResources().getColor(R.color.login_text));
                mbt_iphone.setBackgroundResource(R.color.transparency);
                mbt_gat.setVisibility(View.GONE);
                met_user_id.setHint(R.string.input_id);
                met_password.setHint(R.string.input_password);
                break;
            case R.id.iphone_login:
                mbt_job.setTextColor(getResources().getColor(R.color.login_text));
                mbt_job.setBackgroundResource(R.color.transparency);
                mbt_iphone.setTextColor(getResources().getColor(R.color.white));
                mbt_iphone.setBackgroundResource(R.drawable.semi_round_button2);
                mbt_gat.setVisibility(View.VISIBLE);
                met_user_id.setHint(R.string.input_iphone_number);
                met_password.setHint(R.string.input_dynamic_code);
                break;
            case R.id.get_auth_code:

                break;
            case R.id.login:
                login();
                new Tool().toNextDelete(this, MainActivity.class, null);
                break;
        }
    }

    private void login() {
        if (!TextUtils.isEmpty(user_str) || !TextUtils.isEmpty(password_str)) {

        } else {

        }
    }
}
