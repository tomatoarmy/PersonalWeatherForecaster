package more;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.db.DBManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

public class MoreActivity extends AppCompatActivity implements View.OnClickListener {
    TextView bgTv, cacheTv, versionTv, shareTv;
    RadioGroup exbgRg;
    ImageView backView;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        bgTv = findViewById(R.id.more_tv_exchangeBg);
        cacheTv = findViewById(R.id.more_tv_cache);
        versionTv = findViewById(R.id.more_tv_version);
        shareTv = findViewById(R.id.more_tv_share);
        exbgRg = findViewById(R.id.more_rg);
        backView = findViewById(R.id.back_icon_more);

        bgTv.setOnClickListener(this);
        cacheTv.setOnClickListener(this);
        //版本号应该可以直接显示，不需要添加点击事件
        //bgTv.setOnClickListener(this);
        shareTv.setOnClickListener(this);
        backView.setOnClickListener(this);

        String versionName = getVersionName();
        versionTv.setText("当前版本："+versionName);

        pref = getSharedPreferences("bg_pref", MODE_PRIVATE);
        setRGListener();

    }

    private void setRGListener() {
        /*设置改变背景图片的单选按钮的监听*/
        exbgRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //获取目前壁纸的标号
                int bg = pref.getInt("bg", 0);
                SharedPreferences.Editor editor = pref.edit();
                Intent intent = new Intent(MoreActivity.this, MainActivity.class);
                //点击后，清除栈中所有的Activity，创建一个新栈,然后创建一个新的MainActivity并让其切换壁纸
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

                switch (checkedId){
                    case R.id.more_rg_green:
                        if (bg == 0){
                            Toast.makeText(MoreActivity.this, "当前壁纸正在应用", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        editor.putInt("bg", 0);
                        editor.commit();
                        break;
                    case R.id.more_rg_pink:
                        if (bg == 1){
                            Toast.makeText(MoreActivity.this, "当前壁纸正在应用", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        editor.putInt("bg", 1);
                        editor.commit();
                        break;
                    case R.id.more_rg_blue:
                        if (bg == 2){
                            Toast.makeText(MoreActivity.this, "当前壁纸正在应用", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        editor.putInt("bg", 2);
                        editor.commit();
                        break;
                }
                startActivity(intent);
            }
        });
    }

    private String getVersionName() {
        /*获取应用的版本号*/
        PackageManager manager = getPackageManager();
        String versionName = null;
        try {
            PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_icon_more:
                finish();
                break;
            case R.id.more_tv_cache:
                clearCache();
                break;
            case R.id.more_tv_share:
                shareSoftware("这是一款天气预报App");
                break;
            case R.id.more_tv_exchangeBg:
                if (exbgRg.getVisibility() == View.VISIBLE) {
                    exbgRg.setVisibility(View.GONE);
                }else {
                    exbgRg.setVisibility(View.VISIBLE);
                }

                break;
        }
    }

    private void shareSoftware(String s) {
        /*分享软件的函数*/
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, s);
        startActivity(Intent.createChooser(intent, "天气预报"));

    }

    private void clearCache() {
        /*清除缓存的函数*/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示信息").setMessage("确定删除所有缓存吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBManager.deleteAllInfo();
                Toast.makeText(MoreActivity.this, "缓存已清除",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MoreActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);/*把返回栈清空，并将一个新的activity压入栈中*/
                /*指向的这个Activity如果在Manifest.xml中的声明中添加了Task affinity，系统首先会查找有没有和D的Task affinity相同的task栈存在
                ，如果有存在，将D压入那个栈，如果不存在则会新建一个D的affinity的栈将其压入。如果D的Task affinity默认没有设置，则会把其压入栈1
                ，变成：A B C D，这样就和不加FLAG_ACTIVITY_NEW_TASK标记效果是一样的了。*/
                startActivity(intent);
            }
        }).setNegativeButton("取消", null);
        builder.create().show();
    }
}