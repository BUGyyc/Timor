package com.linkerlab.housemanager.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.linkerlab.housemanager.R;
import com.linkerlab.housemanager.adapters.TimeLineAdapter;
import com.linkerlab.housemanager.base.BaseFragment;
import com.linkerlab.housemanager.core.MyPaperNetManager;
import com.linkerlab.housemanager.models.MyPageItem;
import com.linkerlab.housemanager.net.OkHttpClientManager;
import com.linkerlab.housemanager.thread.Task;
import com.linkerlab.housemanager.util.Log;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by x-man on 16/7/30.
 */
public class PaperFragment extends BaseFragment {
    @ViewInject(id=R.id.title_name)
    TextView mTitleName;
    @ViewInject(id=R.id.paper_listview)
    ListView mListView;
    @ViewInject(id=R.id.back)
    ImageView mBack;

    private final String API="http://ricoo.linkerlab.net/";
    private TimeLineAdapter timelineAdapter;
    private SharedPreferences preferences;
    private MyPaperNetManager myPaperNetManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ViewRoot = inflater.inflate(R.layout.fragment_paper,container,false);
        FinalActivity.initInjectedView(this,ViewRoot);
        mTitleName.setText(R.string.title_paper);
        return ViewRoot;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = this.getActivity().getSharedPreferences("house",
                this.getActivity().MODE_PRIVATE);
        myPaperNetManager=new MyPaperNetManager();

    }

    @Override
    public void onResume() {
        super.onResume();
        /**
         * 以下是测试数据
         */

        timelineAdapter = new TimeLineAdapter(this.getActivity(), setData2ListView());
        mListView.setAdapter(timelineAdapter);

        final String tokenStr = preferences.getString("token", null);
        loadMore(1,tokenStr);
    }

    /**
     * 加载更多我的日报
     * @param pageNum
     */
    public void loadMore(final int pageNum,final String params){
        /**
         * 有待优化
         */
        new Task(){
            @Override
            public void onRun() {
                try {
                    String returnData = OkHttpClientManager
                            .getToken(API + "api/users/record?(page)=" + pageNum+"&(show_my_record)=1",params);
                    Log.i("getData", returnData);
                    List<MyPageItem> list = myPaperNetManager.getJsonDatas(returnData);
                }catch(Exception e){
                    Log.i("getData", e.toString());
                }
            }
        };

    }

    /**
     * 设置数据
     */
    private List<Map<String, Object>> setData2ListView(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "11111111");
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("title", "22222222");
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("title", "34523452435");
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("title", "345dsfgsdfg");
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("title", "243652346");
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("title", "23423454");
        list.add(map);
        return list;
    }
}
