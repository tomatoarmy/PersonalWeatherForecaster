package com.example.myapplication.citymanage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.db.DBManager;
import com.example.myapplication.db.DatabaseBean;

import java.util.ArrayList;
import java.util.List;

public class CityManagerActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView addIv, backIv, deleteIv;
    ListView cityList;
    List<DatabaseBean>mDatas; //显示列表数据源
    CityManagerAdapter cityManagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manager2);

        addIv = findViewById(R.id.city_iv_add);
        backIv = findViewById(R.id.city_iv_back);
        deleteIv = findViewById(R.id.city_iv_delete);
        cityList = findViewById(R.id.listView);
        mDatas = new ArrayList<>();

        //设置点击事件
        addIv.setOnClickListener(this);
        backIv.setOnClickListener(this);
        deleteIv.setOnClickListener(this);

        //设置适配器
        cityManagerAdapter = new CityManagerAdapter(CityManagerActivity.this, mDatas);
        cityList.setAdapter(cityManagerAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.city_iv_add:
                int cityCount = DBManager.getCityCount();
                if (cityCount < 5){
                    Intent intent = new Intent(this, SearchCityActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this, "城市数量已达上限，请删除后再添加", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.city_iv_back:
                finish();
                break;
            case R.id.city_iv_delete:
                Intent intent1 = new Intent(this, DeleteCityActivity.class);
                startActivity(intent1);
                break;

        }
    }

    /*获取数据库当中真实数据源，添加到原有的数据源中，提示适配器更新*/
    @Override
    protected void onResume() {
        super.onResume();
        List<DatabaseBean> list = DBManager.queryAllInfo();
        mDatas.clear();
        mDatas.addAll(list);
        cityManagerAdapter.notifyDataSetChanged();
    }
}