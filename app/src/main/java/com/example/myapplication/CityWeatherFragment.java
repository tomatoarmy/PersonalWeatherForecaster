package com.example.myapplication;

import android.app.AlertDialog;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.WeatherBean;
import com.example.myapplication.db.DBManager;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CityWeatherFragment extends BaseFragment implements View.OnClickListener{

    TextView tempTv,cityTv,conditionTv,windTv,tempRangeTv, dateTv,clothIndexTv,carIndexTv,coldIndexTv,
            sportIndexTv,raysIndexTv;
    ImageView dayIv;
    LinearLayout futureLayout;

    String city;

    String url1 = "http://api.map.baidu.com/telematics/v3/weather?location=";
    String url2 = "&output=json&ak=FkPhtMBKOHTIQNh7gG4cNUttSTyrOnzo";


    private List<WeatherBean.ResultsBean.IndexBean> indexList;
    private WeatherBean.ResultsBean.IndexBean indexBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_city_weather, container, false);
        initView(view);
        //可以通过Activity传值获取到当前Fragment加载的是哪个地方的天气情况
        Bundle bundle = getArguments();
        city = bundle.getString("city");
        Log.d("555555555555555555", city);
        String url = url1 + city + url2;
        String result = "{\n" +
                "error: 0,\n" +
                "status: \"success\",\n" +
                "date: \"2013-07-17\",\n" +
                "results: \n" +
                "[\n" +
                "{\n" +
                "currentCity: \"北京市\",\n" +
                "pm25: \"166\",\n" +
                "index: [\n" +
                "{\n" +
                "title: \"穿衣\",\n" +
                "zs: \"舒适\",\n" +
                "tipt: \"穿衣指数\",\n" +
                "des: \"建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。\"\n" +
                "},\n" +
                "{\n" +
                "title: \"洗车\",\n" +
                "zs: \"不宜\",\n" +
                "tipt: \"洗车指数\",\n" +
                "des: \"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。\"\n" +
                "},\n" +
                "{\n" +
                "title: \"感冒\",\n" +
                "zs: \"较易发\",\n" +
                "tipt: \"感冒指数\",\n" +
                "des: \"相对今天出现了较大幅度降温，较易发生感冒，体质较弱的朋友请注意适当防护。\"\n" +
                "},\n" +
                "{\n" +
                "title: \"运动\",\n" +
                "zs: \"较不宜\",\n" +
                "tipt: \"运动指数\",\n" +
                "des: \"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。\"\n" +
                "},\n" +
                "{\n" +
                "title: \"紫外线强度\",\n" +
                "zs: \"弱\",\n" +
                "tipt: \"紫外线强度指数\",\n" +
                "des: \"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。\"\n" +
                "}\n" +
                "],\n" +
                "weather_data: \n" +
                "[\n" +
                "{\n" +
                "date: \"周三(今天, 实时：24℃)\",\n" +
                "dayPictureUrl: \"http://api.map.baidu.com/images/weather/day/duoyun.png\",\n" +
                "nightPictureUrl: \"http://api.map.baidu.com/images/weather/night/duoyun.png\",\n" +
                "weather: \"多云\",\n" +
                "wind: \"微风\",\n" +
                "temperature: \"23℃~ 15℃\"\n" +
                "},\n" +
                "{\n" +
                "date: \"明天（周四）\",\n" +
                "dayPictureUrl: \"http://api.map.baidu.com/images/weather/day/leizhenyu.png\",\n" +
                "nightPictureUrl: \"http://api.map.baidu.com/images/weather/night/zhongyu.png\",\n" +
                "weather: \"雷阵雨转中雨\",\n" +
                "wind: \"微风\",\n" +
                "temperature: \"29～22℃\"\n" +
                "},\n" +
                "{\n" +
                "date: \"后天（周五）\",\n" +
                "dayPictureUrl: \"http://api.map.baidu.com/images/weather/day/yin.png\",\n" +
                "nightPictureUrl: \"http://api.map.baidu.com/images/weather/night/duoyun.png\",\n" +
                "weather: \"阴转多云\",\n" +
                "wind: \"微风\",\n" +
                "temperature: \"31～23℃\"\n" +
                "},\n" +
                "{\n" +
                "date: \"大后天（周六）\",\n" +
                "dayPictureUrl: \"http://api.map.baidu.com/images/weather/day/duoyun.png\",\n" +
                "nightPictureUrl: \"http://api.map.baidu.com/images/weather/night/duoyun.png\",\n" +
                "weather: \"多云\",\n" +
                "wind: \"微风\",\n" +
                "temperature: \"31～24℃\"\n" +
                "}\n" +
                "]\n" +
                "},\n" +
                "{\n" +
                "currentCity: \"合肥市\",\n" +
                "weather_data: \n" +
                "[\n" +
                "{\n" +
                "date: \"今天（周三）\",\n" +
                "dayPictureUrl: \"http://api.map.baidu.com/images/weather/day/duoyun.png\",\n" +
                "nightPictureUrl: \"http://api.map.baidu.com/images/weather/night/duoyun.png\",\n" +
                "weather: \"多云\",\n" +
                "wind: \"东风3-4级\",\n" +
                "temperature: \"27℃\"\n" +
                "},\n" +
                "{\n" +
                "date: \"明天（周四）\",\n" +
                "dayPictureUrl: \"http://api.map.baidu.com/images/weather/day/duoyun.png\",\n" +
                "nightPictureUrl: \"http://api.map.baidu.com/images/weather/night/duoyun.png\",\n" +
                "weather: \"多云\",\n" +
                "wind: \"东北风3-4级\",\n" +
                "temperature: \"35～27℃\"\n" +
                "},\n" +
                "{\n" +
                "date: \"后天（周五）\",\n" +
                "dayPictureUrl: \"http://api.map.baidu.com/images/weather/day/duoyun.png\",\n" +
                "nightPictureUrl: \"http://api.map.baidu.com/images/weather/night/duoyun.png\",\n" +
                "weather: \"多云\",\n" +
                "wind: \"南风\",\n" +
                "temperature: \"35～27℃\"\n" +
                "},\n" +
                "{\n" +
                "date: \"大后天（周六）\",\n" +
                "dayPictureUrl: \"http://api.map.baidu.com/images/weather/day/duoyun.png\",\n" +
                "nightPictureUrl: \"http://api.map.baidu.com/images/weather/night/duoyun.png\",\n" +
                "weather: \"多云\",\n" +
                "wind: \"东风\",\n" +
                "temperature: \"34～27℃\"\n" +
                "}\n" +
                "]\n" +
                "}\n" +
                "]\n" +
                "}";
        int i = DBManager.updateInfoByCity(city, result);
        if (i<=0) {
            /*数据库更新失败，说明没有这个城市信息，增加这个城市记录*/
            DBManager.addCityInfo(city, result);
        }
//        调用父类获取数据的方法
        loadDate(url);
        return view;
    }

    @Override
    public void onSuccess(String result) {
        //解析并展示数据
/*        result = "{\n" +
                "error: 0,\n" +
                "status: \"success\",\n" +
                "date: \"2013-07-17\",\n" +
                "results: \n" +
                "[\n" +
                "{\n" +
                "currentCity: \"北京市\",\n" +
                "pm25: \"166\",\n" +
                "index: [\n" +
                "{\n" +
                "title: \"穿衣\",\n" +
                "zs: \"舒适\",\n" +
                "tipt: \"穿衣指数\",\n" +
                "des: \"建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。\"\n" +
                "},\n" +
                "{\n" +
                "title: \"洗车\",\n" +
                "zs: \"不宜\",\n" +
                "tipt: \"洗车指数\",\n" +
                "des: \"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。\"\n" +
                "},\n" +
                "{\n" +
                "title: \"感冒\",\n" +
                "zs: \"较易发\",\n" +
                "tipt: \"感冒指数\",\n" +
                "des: \"相对今天出现了较大幅度降温，较易发生感冒，体质较弱的朋友请注意适当防护。\"\n" +
                "},\n" +
                "{\n" +
                "title: \"运动\",\n" +
                "zs: \"较不宜\",\n" +
                "tipt: \"运动指数\",\n" +
                "des: \"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。\"\n" +
                "},\n" +
                "{\n" +
                "title: \"紫外线强度\",\n" +
                "zs: \"弱\",\n" +
                "tipt: \"紫外线强度指数\",\n" +
                "des: \"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。\"\n" +
                "}\n" +
                "],\n" +
                "weather_data: \n" +
                "[\n" +
                "{\n" +
                "date: \"周三(今天, 实时：24℃)\",\n" +
                "dayPictureUrl: \"http://api.map.baidu.com/images/weather/day/duoyun.png\",\n" +
                "nightPictureUrl: \"http://api.map.baidu.com/images/weather/night/duoyun.png\",\n" +
                "weather: \"多云\",\n" +
                "wind: \"微风\",\n" +
                "temperature: \"23℃~ 15℃\"\n" +
                "},\n" +
                "{\n" +
                "date: \"明天（周四）\",\n" +
                "dayPictureUrl: \"http://api.map.baidu.com/images/weather/day/leizhenyu.png\",\n" +
                "nightPictureUrl: \"http://api.map.baidu.com/images/weather/night/zhongyu.png\",\n" +
                "weather: \"雷阵雨转中雨\",\n" +
                "wind: \"微风\",\n" +
                "temperature: \"29～22℃\"\n" +
                "},\n" +
                "{\n" +
                "date: \"后天（周五）\",\n" +
                "dayPictureUrl: \"http://api.map.baidu.com/images/weather/day/yin.png\",\n" +
                "nightPictureUrl: \"http://api.map.baidu.com/images/weather/night/duoyun.png\",\n" +
                "weather: \"阴转多云\",\n" +
                "wind: \"微风\",\n" +
                "temperature: \"31～23℃\"\n" +
                "},\n" +
                "{\n" +
                "date: \"大后天（周六）\",\n" +
                "dayPictureUrl: \"http://api.map.baidu.com/images/weather/day/duoyun.png\",\n" +
                "nightPictureUrl: \"http://api.map.baidu.com/images/weather/night/duoyun.png\",\n" +
                "weather: \"多云\",\n" +
                "wind: \"微风\",\n" +
                "temperature: \"31～24℃\"\n" +
                "}\n" +
                "]\n" +
                "},\n" +
                "{\n" +
                "currentCity: \"合肥市\",\n" +
                "weather_data: \n" +
                "[\n" +
                "{\n" +
                "date: \"今天（周三）\",\n" +
                "dayPictureUrl: \"http://api.map.baidu.com/images/weather/day/duoyun.png\",\n" +
                "nightPictureUrl: \"http://api.map.baidu.com/images/weather/night/duoyun.png\",\n" +
                "weather: \"多云\",\n" +
                "wind: \"东风3-4级\",\n" +
                "temperature: \"27℃\"\n" +
                "},\n" +
                "{\n" +
                "date: \"明天（周四）\",\n" +
                "dayPictureUrl: \"http://api.map.baidu.com/images/weather/day/duoyun.png\",\n" +
                "nightPictureUrl: \"http://api.map.baidu.com/images/weather/night/duoyun.png\",\n" +
                "weather: \"多云\",\n" +
                "wind: \"东北风3-4级\",\n" +
                "temperature: \"35～27℃\"\n" +
                "},\n" +
                "{\n" +
                "date: \"后天（周五）\",\n" +
                "dayPictureUrl: \"http://api.map.baidu.com/images/weather/day/duoyun.png\",\n" +
                "nightPictureUrl: \"http://api.map.baidu.com/images/weather/night/duoyun.png\",\n" +
                "weather: \"多云\",\n" +
                "wind: \"南风\",\n" +
                "temperature: \"35～27℃\"\n" +
                "},\n" +
                "{\n" +
                "date: \"大后天（周六）\",\n" +
                "dayPictureUrl: \"http://api.map.baidu.com/images/weather/day/duoyun.png\",\n" +
                "nightPictureUrl: \"http://api.map.baidu.com/images/weather/night/duoyun.png\",\n" +
                "weather: \"多云\",\n" +
                "wind: \"东风\",\n" +
                "temperature: \"34～27℃\"\n" +
                "}\n" +
                "]\n" +
                "}\n" +
                "]\n" +
                "}";*/
       parseShowData(result);
       //更新数据
        int i = DBManager.updateInfoByCity(city, result);
        if (i<=0) {
            /*数据库更新失败，说明没有这个城市信息，增加这个城市记录*/
            DBManager.addCityInfo(city, result);
        }
    }


    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        //当数据获取失败，在数据库当中查找上一次信息显示在Fragment中
        String s = DBManager.queryInfoByCity(city);
        if (!TextUtils.isEmpty(s)) {
            parseShowData(s);
        }
    }


    private void parseShowData(String result) {
        //使用GSON解析数据
        WeatherBean weatherBean = new Gson().fromJson(result, WeatherBean.class);
        WeatherBean.ResultsBean resultsBean = weatherBean.getResults().get(0);
        //获取指数信息集合列表
        indexList = resultsBean.getIndex();
        //设置TextView
        dateTv.setText(weatherBean.getDate());
        cityTv.setText(resultsBean.getCurrentCity());
        //获取今日天气情况
        WeatherBean.ResultsBean.WeatherDataBean todayDataBean = resultsBean.getWeather_data().get(0);
        windTv.setText(todayDataBean.getWind());
        tempRangeTv.setText(todayDataBean.getTemperature());
        conditionTv.setText(todayDataBean.getWeather());
        //获取实时温度，需要处理字符串
        String[] split = todayDataBean.getDate().split("：");
        String todayTemp = split[1].replace(")", "");
        tempTv.setText(todayTemp);
        //设置显示天气情况的图片
        Picasso.with(getActivity()).load(todayDataBean.getDayPictureUrl()).into(dayIv);
        //获取未来三天的天气情况，加载到Layout当中
        List<WeatherBean.ResultsBean.WeatherDataBean> futureList = resultsBean.getWeather_data();
        futureList.remove(0);
        for (int i = 0; i < futureList.size(); i++) {
            View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.item_main_center, null);
            itemView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            futureLayout.addView(itemView);
            TextView idateTv = itemView.findViewById(R.id.item_center_tv_date);
            TextView iconTv = itemView.findViewById(R.id.item_center_tv_con);
            TextView itemprangeTv = itemView.findViewById(R.id.item_center_tv_temp);
            ImageView iIv = itemView.findViewById(R.id.item_center_iv);
            //获取对应位置的天气情况
            WeatherBean.ResultsBean.WeatherDataBean dataBean = futureList.get(i);
            idateTv.setText(dataBean.getDate());
            iconTv.setText(dataBean.getWeather());
            itemprangeTv.setText(dataBean.getTemperature());
            Picasso.with(getActivity()).load(dataBean.getDayPictureUrl()).into(iIv);
        }
    }

    private void initView(View view){
        //初始化控件操作
        tempTv = view.findViewById(R.id.frag_tv_currenttemp);
        cityTv = view.findViewById(R.id.frag_tv_city);
        conditionTv = view.findViewById(R.id.frag_tv_condition);
        windTv = view.findViewById(R.id.frag_tv_wind);
        tempRangeTv = view.findViewById(R.id.frag_tc_temprange);
        dateTv = view.findViewById(R.id.frag_tv_date);
        clothIndexTv = view.findViewById(R.id.frag_index_dress);
        carIndexTv = view.findViewById(R.id.frag_index_washcar);
        coldIndexTv = view.findViewById(R.id.frag_index_cold);
        sportIndexTv = view.findViewById(R.id.frag_index_sport);
        raysIndexTv = view.findViewById(R.id.frag_index_rays);
        dayIv = view.findViewById(R.id.frag_iv_today);
        futureLayout = view.findViewById(R.id.frag_center_layout);
        //设置点击事件的监听
        clothIndexTv.setOnClickListener(this);
        carIndexTv.setOnClickListener(this);
        coldIndexTv.setOnClickListener(this);
        sportIndexTv.setOnClickListener(this);
        raysIndexTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        switch (v.getId()) {
            case R.id.frag_index_dress:
                builder.setTitle("穿衣指数");
                indexBean = indexList.get(0);
                String msg = indexBean.getZs()+"\n"+indexBean.getDes();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.frag_index_washcar:
                builder.setTitle("洗车指数");
                indexBean = indexList.get(1);
                msg = indexBean.getZs()+"\n"+indexBean.getDes();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.frag_index_cold:
                builder.setTitle("感冒指数");
                indexBean = indexList.get(2);
                msg = indexBean.getZs()+"\n"+indexBean.getDes();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.frag_index_sport:
                builder.setTitle("运动指数");
                indexBean = indexList.get(3);
                msg = indexBean.getZs()+"\n"+indexBean.getDes();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.frag_index_rays:
                builder.setTitle("紫外线指数");
                indexBean = indexList.get(4);
                msg = indexBean.getZs()+"\n"+indexBean.getDes();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
        }
        builder.create().show();
    }
}