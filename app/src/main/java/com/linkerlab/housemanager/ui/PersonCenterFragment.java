package com.linkerlab.housemanager.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linkerlab.housemanager.R;
import com.linkerlab.housemanager.adapter.PersonCenterAdapter;
import com.linkerlab.housemanager.base.BaseFragment;
import com.linkerlab.housemanager.thread.Task;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by x-man on 16/7/30.
 */
public class PersonCenterFragment extends BaseFragment{
    @ViewInject(id = R.id.title_name)
    TextView mTitleName;
    @ViewInject(id = R.id.person_gv)
    GridView mGridView;
    @ViewInject(id=R.id.layout_img)
    LinearLayout mLinearLayout;
    @ViewInject(id =R.id.back)
    ImageView mBack;
    private List<Map<String,Object>> mList = null;
    private String[] mStrs = {"我的日报","我的表单","我的照片","排查计划\n(未开放)"};
    private int[] mRes= {R.drawable.icon_my_rb,
            R.drawable.icon_my_bd,
            R.drawable.icon_my_zp,R.
            drawable.icon_my_pcjh,};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ViewRoot = inflater.inflate(R.layout.fragment_person,container,false);
        FinalActivity.initInjectedView(this,ViewRoot);
        //mTitleName = (TextView) ViewRoot.findViewById(R.id.title_name);
        mTitleName.setText(R.string.person);
        mBack.setVisibility(View.VISIBLE);
        initViews();
        loadData();
        return ViewRoot;
    }

    /**
     * 初始化视图
     */
    public void initViews(){
        mList = new ArrayList<>();
        for (int i=0;i<4;i++) {
            Map map = new HashMap();
            map.put("name",mStrs[i]);
            map.put("img",mRes[i]);
            mList.add(map);
        }
        PersonCenterAdapter adapter = new PersonCenterAdapter(getActivity(),mList);
        mGridView.setAdapter(adapter);
    }

    /**
     * 加载数据
     */
    public void loadData(){
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"hh",Toast.LENGTH_LONG).show();
                new Task(){

                    @Override
                    public void onRun() {
                        for(int i=0;i<100;i++){
                           String str = "http://ricoo.linkerlab.net/api/api/users/record";

//                            Request request = new Request.Builder()
//                                    .url(str)
//                                    .post()
//                                    .addHeader("Authorization","")
//                                    .build();
//                            mOkHttpClient.newCall(request).enqueue(new Callback(){});
                        }
                    }
                }.execute();
            }
        });

    }



}
