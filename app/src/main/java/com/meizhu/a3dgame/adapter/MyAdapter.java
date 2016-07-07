package com.meizhu.a3dgame.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by Kun Yu on 2016/6/25.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String,Object>> data;
    public MyAdapter() {
    }

    public MyAdapter(Context context, List<Map<String, Object>> data) {
        this.context = context;
        this.data = data;
    }

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
        return null;
    }

    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item,null);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_second);
            holder.tv01 = (TextView) convertView.findViewById(R.id.tv01_second);
            holder.tv02 = (TextView) convertView.findViewById(R.id.tv02_second);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Log.i("aaa",""+data.get(position).get("shorttitle").toString()+"");
        holder.tv01.setText(data.get(position).get("shorttitle").toString());
        holder.tv02.setText(data.get(position).get("description").toString());
        Bitmap bitmap = BitmapFactory.decodeFile(data.get(position).get("litpic").toString());
        holder.iv.setImageBitmap(bitmap);

        return convertView;
    }*/

     class ViewHolder{
         ImageView iv = null;
         TextView tv01 = null;
         TextView tv02 = null;
    }
}
