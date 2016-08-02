package com.linkerlab.housemanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linkerlab.housemanager.R;

import java.util.List;
import java.util.Map;

/**
 * 仿时间轴
 * Created by Rush_Yu on 2016/8/1.
 * 1124047290@qq.com
 */
public class TimeLineAdapter extends BaseAdapter{
    private Context context;
    private List<Map<String, Object>> list;
    private LayoutInflater inflater;

    public TimeLineAdapter(Context context,List<Map<String, Object>> l){
        this.context = context;
        this.list = l;
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.listview_item, null);
            viewHolder = new ViewHolder();

            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String titleStr = list.get(position).get("title").toString();


        viewHolder.title.setText(titleStr);

        return convertView;
    }

    static class ViewHolder {
        public TextView year;
        public TextView month;
        public TextView title;
    }
}
