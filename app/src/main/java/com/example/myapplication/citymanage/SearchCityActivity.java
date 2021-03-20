package com.example.myapplication.citymanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.WeatherBean;
import com.google.gson.Gson;

public class SearchCityActivity extends BaseActivity implements View.OnClickListener {
    EditText searchEt;
    ImageView submitIv;
    GridView searchGv;
    String []hotCities = {"北京", "广州","珠海","深圳","合肥","海口","石家庄","天津","武汉","长沙","南京"};

    String url1 = "http://apis.juhe.cn/simpleWeather/query?city=";
    String url2 = "&key=a32f1f312dd7e66d70549d9edd0e0548";
    String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        searchEt = findViewById(R.id.search_et);
        submitIv = findViewById(R.id.search_iv_submit);
        submitIv.setOnClickListener(this);
        searchGv = findViewById(R.id.search_gv);

        //设置适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_hotcity, hotCities);
        searchGv.setAdapter(adapter);
        setListener();
    }

    private void setListener() {
        searchGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                city = hotCities[position];
                String url = url1+city+url2;
                loadData(url);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_iv_submit:
                city = searchEt.getText().toString();
                if (!TextUtils.isEmpty(city)) {
                    //判断输入的城市是否存在
                    String url = url1+city+url2;
                    loadData(url);
                }else {
                    Toast.makeText(this, "输入内容不能为空！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onSuccess(String result) {
        WeatherBean weatherBean = new Gson().fromJson(result, WeatherBean.class);
        if (weatherBean.getError_code() == 0){
            //WeatherBean中的Error参数要是0，说明这个城市存在
            Intent intent = new Intent(this, MainActivity.class);
            //清空原来的返回栈并创建一个新的返回栈
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("city", city);
            startActivity(intent);

        }else {
            Toast.makeText(this, "未收录此城市的天气信息",Toast.LENGTH_SHORT).show();
        }
    }
}