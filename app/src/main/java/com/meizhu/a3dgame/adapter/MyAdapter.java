package com.meizhu.a3dgame.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.meizhu.a3dgame.News;
import com.meizhu.a3dgame.R;

import java.util.List;

/**
 * Created by Kun Yu on 2016/6/25.
 */
public class MyAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{
    private Context context;
    private List<News> data;
    public MyAdapter() {
    }

    public MyAdapter(Context context, List<News> data) {
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
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.article_fragment_listview_item,null);
            holder.iv = (ImageView) convertView.findViewById(R.id.fragment_item_imageview);
            holder.tv01 = (TextView) convertView.findViewById(R.id.fragment_item_tv1);
            holder.tv02 = (TextView) convertView.findViewById(R.id.fragment_item_tv2);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv01.setText(data.get(position).getShorttitle().toString());
        holder.tv02.setText(data.get(position).getDescription().toString());
        Bitmap bitmap = BitmapFactory.decodeFile(data.get(position).getLitpic().toString());
        holder.iv.setImageBitmap(bitmap);

        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    class ViewHolder{
         ImageView iv = null;
         TextView tv01 = null;
         TextView tv02 = null;
    }
}
