package com.linkerlab.housemanager.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.linkerlab.housemanager.R;
import com.linkerlab.housemanager.adapters.TimeLineAdapter;
import com.linkerlab.housemanager.base.BaseFragment;

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

    private TimeLineAdapter timelineAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ViewRoot = inflater.inflate(R.layout.fragment_paper,container,false);
        FinalActivity.initInjectedView(this,ViewRoot);
        mTitleName.setText(R.string.title_paper);
        return ViewRoot;
    }

    @Override
    public void onResume() {
        super.onResume();
        /**
         * 以下是测试数据
         */
        timelineAdapter = new TimeLineAdapter(this.getActivity(), setData2ListView());
        mListView.setAdapter(timelineAdapter);
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
