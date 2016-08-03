package com.linkerlab.housemanager.core;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.linkerlab.housemanager.models.MyPageItem;
import com.linkerlab.housemanager.net.OkHttpClientManager;
import com.linkerlab.housemanager.thread.Task;
import com.linkerlab.housemanager.util.Log;
import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rush_Yu on 2016/8/2.
 * 1124047290@qq.com
 * 我的日报相关网络操作管理
 */
public class MyPaperNetManager {
    private final String API="http://ricoo.linkerlab.net/";
    private final String JSON_PARMS="data";
    private final String ID="id";
    private final String USER_ID="user_id";
    private final String DATA="data";
    private final String CREATE_AT="created_at";
    private final String UPDATE_AT="updated_at";
    private final String USER_PARMS="user";
    private final int NET=1001;
    public MyPaperNetManager(){

    }




    /**
     * 上传我的日报数据、文件？
     */
    public void popDate(){

    }
    /**
     *请求单条json
     * @param strResult
     */
    public MyPageItem getJsonData(String strResult){
        MyPageItem myPageItem=new MyPageItem();
        try {
            JSONObject jsonObj = new JSONObject(strResult).getJSONObject(JSON_PARMS);
            myPageItem.setId(jsonObj.getInt(ID));
            myPageItem.setUser_id(jsonObj.getInt(USER_ID));
            myPageItem.setData(jsonObj.getString(DATA));
            myPageItem.setCreate_at(jsonObj.getString(CREATE_AT));
            myPageItem.setUpdate_at(jsonObj.getString(UPDATE_AT));
            //JSONObject userObj=jsonObj.getJSONObject(USER_PARMS);
            return myPageItem;
        } catch (JSONException e) {
            System.out.println("Json parse error");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 请求多条Json
     * @param str
     * @return
     */
    public List<MyPageItem> getJsonDatas(String str){
        List<MyPageItem> list=new ArrayList<MyPageItem>();
        MyPageItem myPageItem = null;
        try {
            JSONArray jsonObjs = new JSONObject(str).getJSONArray(JSON_PARMS);
            for(int i = 0; i < jsonObjs.length() ; i++){
                JSONObject jsonObj = jsonObjs.getJSONObject(i);
                myPageItem.setId(jsonObj.getInt(ID));
                myPageItem.setUser_id(jsonObj.getInt(USER_ID));
                myPageItem.setData(jsonObj.getString(DATA));
                myPageItem.setCreate_at(jsonObj.getString(CREATE_AT));
                myPageItem.setUpdate_at(jsonObj.getString(UPDATE_AT));
                list.add(myPageItem);
            }
            return list;
        } catch (JSONException e) {
            System.out.println("Jsons parse error !");
            e.printStackTrace();
        }
        return null;
    }
}
