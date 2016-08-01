package com.linkerlab.housemanager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.linkerlab.housemanager.R;
import com.linkerlab.housemanager.ui.PersonCenterFragment;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by x-man on 16/8/1.
 * 适配器
 */
public class PersonCenterAdapter extends BaseAdapter {
    private Context mContext;
    private List<Map<String,Object>> mList;
    public PersonCenterAdapter(Context context, List<Map<String,Object>> mList){
        this.mContext = context;
        this.mList = mList;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHodler viewHodler = null;
        if(view == null){
            view = LinearLayout.inflate(mContext, R.layout.item_grid,null);
            viewHodler = new ViewHodler();
            viewHodler.tv = (TextView) view.findViewById(R.id.tv);
            viewHodler.iv = (ImageView) view.findViewById(R.id.iv);
            view.setTag(viewHodler);
        }else{
            viewHodler = (ViewHodler) view.getTag();
        }
        viewHodler.tv.setText(mList.get(i).get("name").toString());
        viewHodler.iv.setImageResource(Integer.valueOf(mList.get(i).get("img").toString()));
        return view;
    }
    class ViewHodler{
        TextView tv;
        ImageView iv;
    }
}
