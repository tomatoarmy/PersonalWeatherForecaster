package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.myapplication.citymanage.CityManagerActivity;
import com.example.myapplication.db.DBManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import more.MoreActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "1111111111111qweqweqwe";
    ImageView addCityIv,moreIv;
    LinearLayout pointLayout;
    ViewPager mainVp;
    //ViewPager的数据源
    List<Fragment>fragmentList;
    //选中的城市的聚合
    List<String>cityList;
    //表示ViewPager的页数指示器显示集合
    List<ImageView>imgList;
    RelativeLayout outLayout;
    private CItyFragmentPagerAdapter adapter;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addCityIv = findViewById(R.id.main_iv_add);
        moreIv = findViewById(R.id.main_iv_more);
        outLayout = findViewById(R.id.main_out_layout);

        //活动创建后首先判断所要加载的壁纸
        exchangeBg();

        addCityIv.setOnClickListener(this);
        pointLayout = findViewById(R.id.main_layout_point);
        mainVp = findViewById(R.id.main_vp);
        moreIv.setOnClickListener(this);

        fragmentList = new ArrayList<>();
        cityList = DBManager.queryAllCityName(); //获取数据库包含的城市信息列表
        imgList = new ArrayList<>();

        Log.d("12312qqq", "onCreate: "+cityList.size());

        if (cityList.size()==0){
            cityList.add("北京");
            cityList.add("上海");
            cityList.add("台北");
        }

        /*因为可能搜索界面点击跳转到此界面会传值，所以此处获取一下*/
        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
        if (!cityList.contains(city)&&!TextUtils.isEmpty(city)) {//如果cityList中不包含搜索添加的城市，那么添加进去
            cityList.add(city);
        }

        //初始化ViewPager页面的方法
        initPager();
        adapter = new CItyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        mainVp.setAdapter(adapter);
        //创建小圆点指示器
        initPoint();
        //设置最后一个城市信息
//       mainVp.setCurrentItem(fragmentList.size()-1);
        //设置ViewPager监听方法
        setPagerListener();
    }

    private void setPagerListener() {
        //设置监听事件
        mainVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < imgList.size(); i++) {
                    imgList.get(i).setImageResource(R.mipmap.a1);
                }
                imgList.get(position).setImageResource(R.mipmap.a2);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initPoint() {
        //创建小圆点指示器
        for (int i = 0; i < fragmentList.size(); i++) {
            ImageView pIv = new ImageView(this);
            pIv.setImageResource(R.mipmap.a1);
            pIv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)pIv.getLayoutParams();
            lp.setMargins(0,0,20,0);
            imgList.add(pIv);
            pointLayout.addView(pIv);
        }
        imgList.get(imgList.size()-1).setImageResource(R.mipmap.a2);
    }

    private void initPager() {
        //创建Fragment对象，添加到ViewPager数据源当中
        for (int i = 0; i < cityList.size(); i++) {
            CityWeatherFragment cFrag = new CityWeatherFragment();
            Bundle bundle = new Bundle();
            bundle.putString("city",cityList.get(i));
            cFrag.setArguments(bundle);
            fragmentList.add(cFrag);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.main_iv_add:
                intent.setClass(this, CityManagerActivity.class);
                break;
            case R.id.main_iv_more:
                intent.setClass(this, MoreActivity.class);
                break;
        }
        startActivity(intent);
    }

    /*当Activity重新加载时调用的函数,在页面重新获取焦点之前*/

    @Override
    protected void onRestart() {
        super.onRestart();
        List<String> list = DBManager.queryAllCityName();
        cityList.clear();//重新加载之前，清空原来的数据源
        if (list.size() == 0) {
            cityList.add("北京");
        }
        cityList.addAll(list);
        //剩余城市也要创建对应Fragment的页面
        fragmentList.clear();
        initPager();
        adapter.notifyDataSetChanged();
        //页面数量发生改变，指示器的数量也会发生变化，需要重新添加指示器
        imgList.clear();
        pointLayout.removeAllViews();//将布局当中所有元素移除
        initPoint();

    }

    /*换壁纸的函数*/
    public void exchangeBg(){
        pref = getSharedPreferences("bg_pref", MODE_PRIVATE);
        int bgNum = pref.getInt("bg", 0);
        switch (bgNum) {
            case 0:
                outLayout.setBackgroundResource(R.mipmap.bg);
                break;
            case 1:
                outLayout.setBackgroundResource(R.mipmap.bg2);
                break;
            case 2:
                outLayout.setBackgroundResource(R.mipmap.bg3);
                break;
            default:
                break;
        }

    }
}