package com.meizhu.a3dgame.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.meizhu.a3dgame.R;
import com.meizhu.a3dgame.adapter.GameGridViewAdapter;
import com.meizhu.a3dgame.asynctask.GameDownDataAsynctast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kun Yu on 2016/7/9.
 */
public class GameFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    GridView gridView;
    int gameType = 181;
    String urlStr ="http://www.3dmgame.com/sitemap/api.php?row=10&paging=1&page=1&typeid=";
    String urlPath ="http://www.3dmgame.com/sitemap/api.php?row=10&paging=1&page=1&typeid=181";
    GameGridViewAdapter gameGridViewAdapter;
    List<HashMap<String,Object>> mapdata;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_fragment, null);
        spinner = (Spinner) view.findViewById(R.id.game_fragment_sp);
        gridView = (GridView) view.findViewById(R.id.game_fragment_gv);

        List<Map<String,Object>> data;
        //为spinner建立数据源
        String[] typeItems = getResources().getStringArray(R.array.gametype);
        //建立适配器并绑定数据
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, typeItems);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        //girdview填充数据
        //自定义填充girdview的适配器,data,数据，布局文件
        mapdata = new ArrayList<HashMap<String,Object>>();
        gameGridViewAdapter = new GameGridViewAdapter(mapdata,getContext());
        gridView.setAdapter(gameGridViewAdapter);
        new GameDownDataAsynctast(getContext(),gameGridViewAdapter,mapdata).execute(urlPath);
        return view;
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getSelectedItemPosition()) {
            case 0:
                gameType = 181;

                break;
            case 1:
                gameType = 182;
                break;
            case 2:
                gameType = 183;
                break;
            case 3:
                gameType = 184;
                break;
            case 4:
                gameType = 185;
                break;
            case 5:
                gameType = 186;
                break;
            case 6:
                gameType = 187;
                break;
            case 7:
                gameType = 188;
                break;
            case 8:
                gameType = 189;
                break;
            case 9:
                gameType = 190;
                break;
            case 10:
                gameType = 191;
                break;
            case 11:
                gameType = 192;
                break;
        }
        mapdata.clear();
        Toast.makeText(getActivity(), "gameType="+gameType, Toast.LENGTH_SHORT).show();
        Log.i("aaa","gametype="+gameType);
        urlPath= urlStr+gameType;
        new GameDownDataAsynctast(getContext(),gameGridViewAdapter,mapdata).execute(urlPath);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
