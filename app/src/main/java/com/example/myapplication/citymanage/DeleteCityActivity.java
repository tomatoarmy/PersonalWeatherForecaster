package com.example.myapplication.citymanage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.db.DBManager;

import java.util.ArrayList;
import java.util.List;

public class DeleteCityActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView errorIv, rightIv;
    ListView deleteLv;
    List<String> mDatas;//ListView的数据源
    List<String> deleteCities;//表示了存储了删除的城市信息
    private DeleteCityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_city);
        errorIv = findViewById(R.id.delete_iv_error);
        rightIv = findViewById(R.id.delete_iv_right);
        deleteLv = findViewById(R.id.delete_lv);
        mDatas = new ArrayList<>();
        deleteCities = new ArrayList<>();

        errorIv.setOnClickListener(this);
        rightIv.setOnClickListener(this);

        //适配器的设置
        adapter = new DeleteCityAdapter(this, mDatas, deleteCities);
        deleteLv.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<String> cityList = DBManager.queryAllCityName();
        mDatas.addAll(cityList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.delete_iv_error:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("提示信息").setMessage("你确定要舍弃更改么？").setPositiveButton("舍弃", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();//关闭当前的Activity
                                }
                            });
                    builder.setNegativeButton("取消", null);
                    builder.create().show();
                    break;
            case R.id.delete_iv_right:
                for (int i = 0; i < deleteCities.size(); i++) {
                    String city = deleteCities.get(i);
                    DBManager.deleteInfoByCity(city);
                }
                //删除成功返回上级界面
                finish();
                break;
            default:
                break;
        }
    }
}