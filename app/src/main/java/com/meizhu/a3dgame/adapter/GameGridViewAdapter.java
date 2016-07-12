package com.meizhu.a3dgame.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.meizhu.a3dgame.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Kun Yu on 2016/7/9.
 */
public class GameGridViewAdapter extends BaseAdapter {

    public GameGridViewAdapter(List<HashMap<String, Object>> data, Context context) {
        this.data = data;
        this.context = context;
    }

    List<HashMap<String,Object>> data;
    Context context;



    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.article_fragment_listview_item,null);
            holder.iv = (ImageView) convertView.findViewById(R.id.fragment_item_imageview);
            holder.tv = (TextView) convertView.findViewById(R.id.fragment_item_tv1);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(data.get(position).get("title").toString());
        holder.iv.setImageBitmap((Bitmap) data.get(position).get("pic"));

        return convertView;
    }

    class ViewHolder{
        ImageView iv = null;
        TextView tv = null;

    }
}
